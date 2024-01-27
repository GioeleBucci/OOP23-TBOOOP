package tbooop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import tbooop.commons.api.Projectile;
import tbooop.controller.api.CommandListener;
import tbooop.controller.api.Event;
import tbooop.controller.api.EventListener;
import tbooop.controller.api.PlayerCommand;
import tbooop.model.core.api.GameObject;
import tbooop.model.core.api.movable.Entity;

import java.util.logging.Logger;

/**
 * The GameEngine class is responsible for managing the game loop and processing
 * events.
 */
public final class GameEngine implements EventListener, CommandListener {

    private final List<Event> eventQueue = new ArrayList<>();
    private final World world = new World();
    private final Logger logger = Logger.getLogger(GameEngine.class.getName());
    private final BlockingQueue<PlayerCommand> cmdQueue = new ArrayBlockingQueue<>(100);

    private static final int FPS = 60;
    private static final long REFRESH_PERIOD = 1L / FPS * 1000; // in ms

    /**
     * The main game loop that processes input and updates the game state.
     */
    public void mainLoop() {
        long prevStartTime = System.currentTimeMillis();
        while (true) {
            final long startTime = System.currentTimeMillis();
            final long elapsed = startTime - prevStartTime;
            // processInput();
            updateGame(elapsed);
            // render();
            waitForNextFrame(startTime);
            prevStartTime = startTime;
        }
        // gameOver();
    }

    private void updateGame(final long dt) {
        world.getPlayer().updateState(dt);
        // update all projectiles
        for (final Projectile projectile : world.getProjectiles()) {
            // update all gameObjects
            for (final GameObject gameObj : world.getGameObjects()) {
                // GameObject-Player collision
                if (gameObj.getCollider().isColliding(world.getPlayer().getCollider())) {
                    gameObj.onPlayerCollision(world.getPlayer());
                }
                // Projectile-Entity collision
                if (gameObj instanceof Entity && gameObj.getCollider().isColliding(projectile.getCollider())) {
                    projectile.onEntityCollision((Entity) gameObj);
                }
                gameObj.updateState(dt);
            }
            projectile.updateState(dt);
        }
    }

    @Override
    public void notifyEvent(final Event event) {
        logger.info("new event recieved.");
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
}
