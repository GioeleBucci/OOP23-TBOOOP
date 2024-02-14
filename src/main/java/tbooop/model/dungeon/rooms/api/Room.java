package tbooop.model.dungeon.rooms.api;

import java.util.Set;

import tbooop.commons.api.CardinalDirection;
import tbooop.model.core.api.GameObject;

/**
 * Base for all Rooms implementations.
 * 
 * @see {@link tbooop.model.dungeon.floor.BaseFloor Floor}
 */
public interface Room extends RoomUnmodifiable {

    /**
     * Adds a door to this room.
     * 
     * @param direction where the door is located
     * @param door      the door to add
     * @see CardinalDirection
     */
    void addDoor(CardinalDirection direction, DoorUnmodifiable door);

    /**
     * Get all the game objects in the room.
     * 
     * @return a set of all the game objects in the room
     */
    Set<GameObject> getGameObjects();

    /**
     * Closes all the doors in the room.
     */
    void closeDoors();

    /**
     * Opens all the doors in the room.
     */
    void openDoors();

}
