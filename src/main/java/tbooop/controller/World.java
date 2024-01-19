package tbooop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import tbooop.commons.HealthImpl;
import tbooop.commons.Point2d;
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
public class World {
    private final List<GameObject> gameObjects = new ArrayList<>();
    private final Player player = new PlayerImpl(Point2d.ZERO, new HealthImpl(1), 1.0);
    private Floor floor = new LevelFloor(1);
    private Point2d currentRoom = Point2d.ZERO;

    /*
     * All the following methods are protected because they shouldn't be used
     * outside of the controller
     */

    /**
     * Returns the list of game objects in the world.
     *
     * @return the list of game objects
     */
    protected List<GameObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * Returns the player in the world.
     *
     * @return the player
     */
    protected Player getPlayer() {
        return player;
    }

    /**
     * Returns the current floor.
     *
     * @return the current floor
     */
    protected Floor getFloor() {
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
    protected Point2d getCurrentRoom() {
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
