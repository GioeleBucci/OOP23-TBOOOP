package tbooop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import tbooop.commons.api.Projectile;
import tbooop.controller.api.Controller;
import tbooop.controller.api.Event;
import tbooop.controller.api.PlayerCommand;
import tbooop.model.core.api.GameObject;
import tbooop.view.ViewImpl;
import tbooop.view.api.View;

import java.util.logging.Logger;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.application.Application;
import javafx.application.Platform;

/**
 * This class is responsible for managing the game loop and processing events.
 */
public final class ControllerImpl implements Controller {

    private final World world = new World();
    private final List<Event> eventQueue = new ArrayList<>();
    private final Logger logger = Logger.getLogger(ControllerImpl.class.getName());
    private static final int COMMAND_QUEUE_SIZE = 20;
    private final BlockingQueue<PlayerCommand> cmdQueue = new ArrayBlockingQueue<>(COMMAND_QUEUE_SIZE);
    private final View view;
    private boolean playerAdded;

    private static final int FPS = 10; // frames per second
    private static final long REFRESH_PERIOD = (long) (1.0 / FPS * 1000); // in ms

    /**
     * Constructs a new Controller.
     * 
     * @param view the view that this controller will use
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "View is needed according to MVC.")
    public ControllerImpl(final View view) {
        this.view = Objects.requireNonNull(view);
    }

    /**
     * The main game loop that processes input and updates the game state.
     */
    @Override
    public void mainLoop() {
        long prevStartTime = System.currentTimeMillis();
        while (true) {
            final long startTime = System.currentTimeMillis();
            final long elapsed = startTime - prevStartTime;
            processInput();
            updateGame(elapsed);
            updateView();
            waitForNextFrame(startTime);
            prevStartTime = startTime;
        }
        // gameOver();
    }

    private void processInput() {
        if (!cmdQueue.isEmpty()) {
            cmdQueue.poll().execute(world.getPlayer());
        }
    }

    private void updateView() {
        Platform.runLater(() -> {
            if (!playerAdded) {
                this.view.addGameObject(world.getPlayer());
                //// TEST
                // final GameObject enemy = new EnemyFactoryImpl(world.getPlayer()).shooter(Point2ds.DOWN);
                // enemy.setPosition(new Point2dImpl(RoomBounds.WIDTH / 2, RoomBounds.HEIGHT / 2));
                // world.getGameObjects().add(enemy);
                // this.view.addGameObject(enemy);
                //// END TEST
                playerAdded = true;
            }
            this.view.update();
        });
    }

    private void updateGame(final long dt) {
        world.getPlayer().updateState(dt);
        // update all projectiles
        for (final Projectile projectile : world.getProjectiles()) {
            // update all gameObjects
            projectile.updateState(dt);
        }
        for (final GameObject gameObj : world.getGameObjects()) {
            // GameObject-Player collision
            if (gameObj.getCollider().isColliding(world.getPlayer().getCollider())) {
                gameObj.onPlayerCollision(world.getPlayer());
            }
            // Projectile-Entity collision
            // if (gameObj instanceof Entity &&
            // gameObj.getCollider().isColliding(projectile.getCollider())) {
            // projectile.onEntityCollision((Entity) gameObj);
            // }
            gameObj.updateState(dt);
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

    /**
     * Starts the game.
     * 
     * @param args
     */
    public static void main(final String[] args) {
        Application.launch(ViewImpl.class, args);
    }
}
