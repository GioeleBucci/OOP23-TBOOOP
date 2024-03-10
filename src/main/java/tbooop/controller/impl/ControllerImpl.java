package tbooop.controller.impl;

import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import tbooop.commons.api.Projectile;
import tbooop.controller.api.Controller;
import tbooop.controller.api.PlayerCommand;
import tbooop.controller.api.World;
import tbooop.model.core.api.GameObject;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.movable.Entity;
import tbooop.model.dungeon.doors.api.DoorUnmodifiable;
import tbooop.view.api.View;
import java.util.logging.Logger;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.application.Platform;

/**
 * This class is responsible for managing the game loop and processing events.
 */
public final class ControllerImpl implements Controller {

    private static final int COMMAND_QUEUE_SIZE = 200;
    private final Logger logger = Logger.getLogger(ControllerImpl.class.getName());
    private final BlockingQueue<PlayerCommand> moveQueue = new ArrayBlockingQueue<>(COMMAND_QUEUE_SIZE);
    private final BlockingQueue<PlayerCommand> shootQueue = new ArrayBlockingQueue<>(COMMAND_QUEUE_SIZE);
    private final View view;
    private final World world;

    private static final int FPS = 60; // frames per second
    private static final long REFRESH_PERIOD = (long) (1.0 / FPS * 1000); // in ms

    /**
     * Constructs a new Controller.
     * 
     * @param view the view that this controller will use
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "View is needed according to MVC.")
    public ControllerImpl(final View view) {
        this.view = Objects.requireNonNull(view);
        this.world = new WorldImpl(view);
    }

    /** {@inheritDoc} */
    @Override
    public void notifyCommand(final PlayerCommand cmd) {
        if (cmd instanceof MoveCommand) {
            this.moveQueue.add(cmd);
        } else {
            this.shootQueue.add(cmd);
        }
    }

    /**
     * The main game loop that processes input and updates the game state.
     */
    @Override
    public void mainLoop() {
        world.init();
        Platform.runLater(() -> view.addPlayer(world.getPlayer()));
        long prevStartTime = System.currentTimeMillis();
        while (!isGameOver()) {
            synchronized (this) {
                final long startTime = System.currentTimeMillis();
                final long elapsed = startTime - prevStartTime;
                processInput();
                updateGame(elapsed);
                updateView();
                waitForNextFrame(startTime);
                prevStartTime = startTime;
            }
        }
        gameOver();
    }

    private boolean isGameOver() {
        return world.getPlayer().getHealth() <= 0;
    }

    private void gameOver() {
        Platform.runLater(() -> view.showDeathScreen());
    }

    private synchronized void processInput() {
        if (!moveQueue.isEmpty()) {
            moveQueue.poll().execute(world.getPlayer());
        }
        if (!shootQueue.isEmpty()) {
            shootQueue.poll().execute(world.getPlayer());
        }
    }

    private synchronized void updateView() {
        Platform.runLater(() -> view.update());
    }

    private void updateGame(final long dt) {
        world.getPlayer().updateState(dt);
        world.collectProjectiles((Entity) world.getPlayer());
        final Iterator<GameObject> gameObjIterator = world.getGameObjects().iterator();

        while (gameObjIterator.hasNext()) {
            final GameObject gameObj = gameObjIterator.next();
            gameObj.updateState(dt);
            world.update();
            if (gameObj instanceof Entity) {
                world.collectProjectiles((Entity) gameObj);
            }
            // GameObject-Player collision
            if (gameObj.getCollider().isColliding(world.getPlayer().getCollider())) {
                checkDoorCollision(gameObj);
                gameObj.onPlayerCollision(world.getPlayer());
            }
            // update all projectiles
            final Iterator<Projectile> projectileIterator = world.getProjectiles().iterator();
            while (projectileIterator.hasNext()) {
                final Projectile projectile = projectileIterator.next();
                // Projectile-Entity collision
                if (gameObj instanceof Entity && gameObj.getCollider().isColliding(projectile.getCollider())) {
                    projectile.onEntityCollision((Entity) gameObj);
                }
                // Projectile-Player collision
                if (projectile.getCollider().isColliding(world.getPlayer().getCollider())) {
                    projectile.onPlayerCollision(world.getPlayer());
                }
            }
        }
        world.getProjectiles().iterator().forEachRemaining(p -> p.updateState(dt));
    }

    private void checkDoorCollision(final GameObject gameObj) {
        if (gameObj instanceof DoorUnmodifiable) {
            world.onDoorCollision((DoorUnmodifiable) gameObj);
        } else if (gameObj.getTag().equals(GameTag.TRAPDOOR)) {
            world.changeFloor();
        }
    }

    private void waitForNextFrame(final long startTime) {
        final long dt = System.currentTimeMillis() - startTime;
        if (dt < REFRESH_PERIOD) {
            try {
                Thread.sleep(REFRESH_PERIOD - dt);
            } catch (InterruptedException ex) {
                logger.fine("InterruptedException occurred while waiting for next frame: " + ex.getMessage());
            }
        }
    }
}
