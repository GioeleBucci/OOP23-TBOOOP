package tbooop.model.dungeon.rooms.api;

import java.util.Set;

import tbooop.commons.Point2ds;
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
     * @see Point2ds
     */
    void addDoor(Point2ds direction, DoorUnmodifiable door);

    /**
     * Get all the game objects in the room.
     * 
     * @return a set of all the game objects in the room
     */
    Set<GameObject> getGameObjects();

    /** Flags the room as explored. Cannot be undone. */
    void setExplored();

    /**
     * Closes all the doors in the room.
     */
    void closeDoors();

    /**
     * Opens all the doors in the room.
     */
    void openDoors();

}
