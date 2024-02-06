package tbooop.controller;

import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.application.Platform;

import tbooop.commons.HealthImpl;
import tbooop.commons.api.Point2d;
import tbooop.commons.api.Projectile;
import tbooop.controller.api.FloorManager;
import tbooop.controller.api.World;
import tbooop.commons.Point2dImpl;
import tbooop.commons.RoomBounds;
import tbooop.model.core.api.GameObject;
import tbooop.model.dungeon.floor.api.Floor;
import tbooop.model.dungeon.rooms.api.DoorUnmodifiable;
import tbooop.model.player.api.Player;
import tbooop.model.player.impl.PlayerImpl;
import tbooop.view.api.View;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * The World class represents the game world and contains information about the
 * game objects,
 * the player, the current floor, and the current room.
 */
@SuppressFBWarnings(value = {
        "EI_EXPOSE_REP" // may expose internal representation by returning reference to mutable object
}, justification = "This class is a container of game objects, that the controller needs check and modify"
        + "and for that reason exposing the internal representation is necessary.")
public final class WorldImpl implements World {

    private static final int PLAYER_INITIAL_HEALTH = 100;
    private static final double PLAYER_INITIAL_SPEED = .1;
    private final View view;
    private final FloorManager floorManager;

    private volatile Set<GameObject> gameObjects = new CopyOnWriteArraySet<>();
    private volatile Set<Projectile> projectiles = new CopyOnWriteArraySet<>();
    private volatile Player player = new PlayerImpl(new Point2dImpl(RoomBounds.WIDTH / 2,
            RoomBounds.HEIGHT / 2), new HealthImpl(PLAYER_INITIAL_HEALTH), PLAYER_INITIAL_SPEED);

    /**
     * Constructs a new World with the specified view.
     *
     * @param view the view that this world will update
     */
    public WorldImpl(final View view) {
        this.view = view;
        this.floorManager = new FloorManagerImpl(this, view);
        // System.out.println(floor);
    }

    /** {@inheritDoc} */
    @Override
    public void init() {
        floorManager.init();
    }

    /** {@inheritDoc} */
    @Override
    public void update() {
        gameObjects.removeIf(GameObject::isDestroyed);
        projectiles.removeIf(Projectile::isDestroyed);
    }

    /** {@inheritDoc} */
    @Override
    public Set<GameObject> getGameObjects() {
        return gameObjects;
    }

    /** {@inheritDoc} */
    @Override
    public Set<Projectile> getProjectiles() {
        return projectiles;
    }

    /** {@inheritDoc} */
    @Override
    public Player getPlayer() {
        return player;
    }

    /** {@inheritDoc} */
    @Override
    public void onDoorCollision(final DoorUnmodifiable door) {
        floorManager.onDoorCollision(door);
    }

    // private void addGameObject(final GameObject gameObject) {
    // gameObjects.add(gameObject);
    // Platform.runLater(() -> {
    // view.addGameObject(gameObject);
    // });
    // }

    /**
     * Returns the current floor.
     *
     * @return the current floor
     */
    public Floor getFloor() {
        return null;
        // return floor;
    }

    /**
     * Returns the current room.
     *
     * @return the current room
     */
    public Point2d getCurrentRoom() {
        // return currentRoom;
        return null;
    }

    /**
     * Sets the current room.
     *
     * @param currentRoom the new current room to set
     */
    void setCurrentRoom(final Point2d currentRoom) {
        // this.currentRoom = currentRoom;
    }

    /** {@inheritDoc} */
    @Override
    public synchronized void clearAll() {
        gameObjects.forEach(gameObject -> {
            Platform.runLater(() -> {
                view.removeGameObject(gameObject);
            });
        });
        gameObjects.clear();
        projectiles.forEach(projectile -> {
            Platform.runLater(() -> {
                view.removeGameObject(projectile);
            });
        });
        projectiles.clear();
    }
}
