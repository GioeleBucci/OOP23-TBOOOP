package tbooop.model.dungeon.rooms.api;

import java.util.Set;

import tbooop.commons.api.Direction;
import tbooop.model.core.api.GameObject;
import tbooop.model.dungeon.doors.api.DoorUnmodifiable;

/**
 * Base for all Rooms implementations.
 * 
 * @see {@link tbooop.model.dungeon.floor.api.BaseFloor Floor}
 */
public interface Room extends RoomUnmodifiable {

    /**
     * Initializes the room.
     * <p>
     * <b>Note</b>: This method should be called every time a new room is created.
     */
    void init();

    /**
     * Adds a door to this room.
     * 
     * @param direction where the door is located
     * @param door      the door to add
     * @see Direction
     */
    void addDoor(Direction direction, DoorUnmodifiable door);

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
