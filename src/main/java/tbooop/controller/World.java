package tbooop.controller;

import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.HashSet;
import java.util.Objects;

import tbooop.commons.HealthImpl;
import tbooop.commons.api.Point2d;
import tbooop.commons.api.Projectile;
import tbooop.commons.Point2dImpl;
import tbooop.commons.RoomBounds;
import tbooop.model.core.api.GameObject;
import tbooop.model.dungeon.floor.LevelFloor;
import tbooop.model.dungeon.floor.api.Floor;
import tbooop.model.player.api.Player;
import tbooop.model.player.impl.PlayerImpl;

/**
 * The World class represents the game world and contains information about the
 * game objects,
 * the player, the current floor, and the current room.
 */
@SuppressFBWarnings(value = {
        "EI_EXPOSE_REP" // may expose internal representation by returning reference to mutable object
}, justification = "This class is a container of game objects, that the controller needs check and modify"
        + "and for that reason exposing the internal representation is necessary.")
public class World {
    private final Set<GameObject> gameObjects = new HashSet<>();
    private final Set<Projectile> projectiles = new HashSet<>();

    private final Player player = new PlayerImpl(new Point2dImpl(RoomBounds.WIDTH / 2,
            RoomBounds.HEIGHT / 2), new HealthImpl(1), .1);
    private Floor floor = new LevelFloor(1);
    private Point2d currentRoom = Point2dImpl.ZERO;

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
        return currentRoom;
    }

    /**
     * Sets the current room.
     *
     * @param currentRoom the new current room to set
     */
    protected void setCurrentRoom(final Point2d currentRoom) {
        this.currentRoom = currentRoom;
    }

}
