package tbooop.controller;

import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.application.Platform;

import java.util.HashSet;
import java.util.Objects;

import tbooop.commons.HealthImpl;
import tbooop.commons.api.Point2d;
import tbooop.commons.api.Projectile;
import tbooop.controller.api.ControllerComponent;
import tbooop.commons.Point2dImpl;
import tbooop.commons.RoomBounds;
import tbooop.model.core.api.GameObject;
import tbooop.model.dungeon.floor.LevelFloor;
import tbooop.model.dungeon.floor.api.Floor;
import tbooop.model.dungeon.rooms.api.DoorUnmodifiable;
import tbooop.model.dungeon.rooms.api.DoorLockable;
import tbooop.model.dungeon.rooms.api.DoorPositions;
import tbooop.model.dungeon.rooms.api.Room;
import tbooop.model.player.api.Player;
import tbooop.model.player.impl.PlayerImpl;
import tbooop.view.api.View;

/**
 * The World class represents the game world and contains information about the
 * game objects,
 * the player, the current floor, and the current room.
 */
@SuppressFBWarnings(value = {
        "EI_EXPOSE_REP" // may expose internal representation by returning reference to mutable object
}, justification = "This class is a container of game objects, that the controller needs check and modify"
        + "and for that reason exposing the internal representation is necessary.")
public final class World implements ControllerComponent {
    private volatile Set<GameObject> gameObjects = new HashSet<>();
    private volatile Set<Projectile> projectiles = new HashSet<>();

    private final View view;
    private final Player player = new PlayerImpl(new Point2dImpl(RoomBounds.WIDTH / 2,
            RoomBounds.HEIGHT / 2), new HealthImpl(6), .1);
    private Floor floor = new LevelFloor(1);
    // the starting room is the one at position (0,0)
    private Room currentRoom = floor.getStaringRoom();

    /**
     * Constructs a new World with the specified view.
     *
     * @param view the view that this world will update
     */
    public World(final View view) {
        this.view = view;
        this.player.pickupKeys();
        System.out.println(floor);
    }

    /** Handles player/door collisions. */
    public void onDoorCollision(final DoorUnmodifiable door) {
        if (!door.isOpen()) {
            if (!player.hasKey()) {
                return;
            }
            player.useKey();
            ((DoorLockable) door).unlock();
        }
        synchronized (this) {
            player.setPosition(newPlayerPosition(door));
            for (final GameObject gameObject : gameObjects) {
                Platform.runLater(() -> {
                    view.removeGameObject(gameObject);
                });
            }
            gameObjects.clear();
            for (final GameObject projectile : projectiles) {
                Platform.runLater(() -> {
                    view.removeGameObject(projectile);
                });
            }
            projectiles.clear();
            // change room
            changeRoom((Room) door.getRoom());
        }
    }

    private Point2d newPlayerPosition(final DoorUnmodifiable door) {
        final double offset = door.getCollider().getRadius() + player.getCollider().getRadius() + 10;
        if (door.getPosition().equals(DoorPositions.TOP.getPosition())) {
            return new Point2dImpl(DoorPositions.BOTTOM.getPosition().add(new Point2dImpl(0, -offset)));
        }
        if (door.getPosition().equals(DoorPositions.BOTTOM.getPosition())) {
            return new Point2dImpl(DoorPositions.TOP.getPosition().add(new Point2dImpl(0, offset)));
        }
        if (door.getPosition().equals(DoorPositions.LEFT.getPosition())) {
            return new Point2dImpl(DoorPositions.RIGHT.getPosition().add(new Point2dImpl(-offset, 0)));
        }
        if (door.getPosition().equals(DoorPositions.RIGHT.getPosition())) {
            return new Point2dImpl(DoorPositions.LEFT.getPosition().add(new Point2dImpl(offset, 0)));
        }
        throw new IllegalArgumentException("Invalid door position " + door.getPosition());
    }

    private void changeRoom(final Room newRoom) {
        for (final GameObject gameObject : newRoom.getDoorMap().values()) {
            addGameObject(gameObject);
        }
        currentRoom = newRoom;
        currentRoom.setExplored();
        view.changeRoom(currentRoom);
    }

    @Override
    public void init() {
        gameObjects.addAll(currentRoom.getDoorMap().values());
        view.changeRoom(currentRoom);
    }

    @Override
    public void update() {
        gameObjects.removeIf(GameObject::isDestroyed);
        projectiles.removeIf(Projectile::isDestroyed);
    }

    private void addGameObject(final GameObject gameObject) {
        gameObjects.add(gameObject);
        Platform.runLater(() -> {
            view.addGameObject(gameObject);
        });
    }

    /**
     * Returns the list of game objects in the world.
     *
     * @return the list of game objects
     */
    public Set<GameObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * Returns the list of game objects in the world.
     *
     * @return the list of game objects
     */
    public Set<Projectile> getProjectiles() {
        return projectiles;
    }

    /**
     * Returns the player in the world.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the current floor.
     *
     * @return the current floor
     */
    public Floor getFloor() {
        return floor;
    }

    /**
     * Sets the current floor.
     *
     * @param newFloor the new floor to set
     */
    protected void setFloor(final Floor newFloor) {
        this.floor = Objects.requireNonNull(newFloor);
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
    protected void setCurrentRoom(final Point2d currentRoom) {
        // this.currentRoom = currentRoom;
    }
}
