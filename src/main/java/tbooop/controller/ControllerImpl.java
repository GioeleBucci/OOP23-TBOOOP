package tbooop.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import tbooop.commons.Point2dImpl;
import tbooop.commons.Point2ds;
import tbooop.commons.api.Projectile;
import tbooop.controller.api.Controller;
import tbooop.controller.api.Event;
import tbooop.controller.api.PlayerCommand;
import tbooop.model.core.api.GameObject;
import tbooop.model.core.api.movable.Entity;
import tbooop.model.dungeon.rooms.api.DoorUnmodifiable;
import tbooop.model.enemy.impl.EnemyFactoryImpl;
import tbooop.view.ViewImpl;
import tbooop.view.api.View;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.application.Application;
import javafx.application.Platform;

/**
 * This class is responsible for managing the game loop and processing events.
 */
public final class ControllerImpl implements Controller {

    private final List<Event> eventQueue = new ArrayList<>();
    private final Logger logger = Logger.getLogger(ControllerImpl.class.getName());
    private static final int COMMAND_QUEUE_SIZE = 40;
    private final BlockingQueue<PlayerCommand> cmdQueue = new ArrayBlockingQueue<>(COMMAND_QUEUE_SIZE);
    private final View view;
    private final World world;
    private boolean playerAdded;

    private static final int FPS = 30; // frames per second
    private static final long REFRESH_PERIOD = (long) (1.0 / FPS * 1000); // in ms

    /**
     * Constructs a new Controller.
     * 
     * @param view the view that this controller will use
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "View is needed according to MVC.")
    public ControllerImpl(final View view) {
        this.view = Objects.requireNonNull(view);
        this.world = new World(view);
    }

    /**
     * The main game loop that processes input and updates the game state.
     */
    @Override
    public void mainLoop() {
        long prevStartTime = System.currentTimeMillis();
        world.init();
        while (true) {
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
        // gameOver();
    }

    private synchronized void processInput() {
        if (!cmdQueue.isEmpty()) {
            cmdQueue.poll().execute(world.getPlayer());
        }
    }

    private synchronized void updateView() {
        Platform.runLater(() -> {
            if (!playerAdded) {
                this.view.addPlayer(world.getPlayer());
                //// TEST
                final GameObject enemy = new EnemyFactoryImpl(world.getPlayer()).shooter(Point2ds.UP);
                enemy.setPosition(new Point2dImpl(100, 100));
                world.getGameObjects().add(enemy);
                this.view.addGameObject(enemy);
                //// END TEST
                playerAdded = true;
            }
            this.view.update();
        });
    }

    private synchronized void collectProjectiles(final Entity entity) {
        final Set<Projectile> projectiles = entity.getShotProjectiles();
        // FIXME this sucks and its only for testing
        for (final Projectile projectile : projectiles) {
            world.getProjectiles().add(projectile);
            Platform.runLater(() -> {
                view.addGameObject(projectile);
            });
        }
    }

    private void updateGame(final long dt) {
        world.getPlayer().updateState(dt);
        collectProjectiles((Entity) world.getPlayer());

        final Iterator<GameObject> gameObjIterator = world.getGameObjects().iterator();

        Optional<DoorUnmodifiable> door = Optional.empty();

        while (gameObjIterator.hasNext()) {
            final GameObject gameObj = gameObjIterator.next();
            gameObj.updateState(dt);
            world.update();

            if (gameObj instanceof Entity) {
                collectProjectiles((Entity) gameObj);
            }

            // GameObject-Player collision
            if (gameObj.getCollider().isColliding(world.getPlayer().getCollider())) {
                if (gameObj instanceof DoorUnmodifiable) {
                    door = Optional.of((DoorUnmodifiable) gameObj);
                }
                gameObj.onPlayerCollision(world.getPlayer());
            }

            // update all projectiles
            final Iterator<Projectile> projectileIterator = world.getProjectiles().iterator();
            while (projectileIterator.hasNext()) {
                final Projectile projectile = projectileIterator.next();
                projectile.updateState(dt);
                world.update();

                // Projectile-Entity collision
                if (gameObj instanceof Entity && gameObj.getCollider().isColliding(projectile.getCollider())) {
                    projectile.onEntityCollision((Entity) gameObj);
                }

                // Projectile-Player collision
                if (projectile.getCollider().isColliding(world.getPlayer().getCollider())) {
                    logger.info("Player health:" + world.getPlayer().getHealth());
                    projectile.onPlayerCollision(world.getPlayer());
                    // projectile.onEntityCollision((Entity) world.getPlayer());
                }
            }
        }
        // at last check if the room needs to be changed
        if (door.isPresent()) {
            world.onDoorCollision(door.get());
        }
    }

    @Override
    public void notifyEvent(final Event event) {
        // logger.info("new event recieved.");
        eventQueue.add(event);
    }

    /** {@inheritDoc} */
    @Override
    public void notifyCommand(final PlayerCommand cmd) {
        this.cmdQueue.add(Objects.requireNonNull(cmd));
    }

    private synchronized void waitForNextFrame(final long startTime) {
        final long dt = System.currentTimeMillis() - startTime;
        if (dt < REFRESH_PERIOD) {
            try {
                Thread.sleep(REFRESH_PERIOD - dt);
            } catch (InterruptedException ex) {
                logger.fine("InterruptedException occurred while waiting for next frame: " + ex.getMessage());
            }
        }
    }

    /**
     * Starts the game.
     * 
     * @param args
     */
    public static void main(final String[] args) {
        Application.launch(ViewImpl.class, args);
    }
}