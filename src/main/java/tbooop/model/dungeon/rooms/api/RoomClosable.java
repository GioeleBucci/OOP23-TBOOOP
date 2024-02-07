package tbooop.model.dungeon.rooms.api;

/**
 * Interface for rooms which doors can opened and closed.
 */
public interface RoomClosable {

    /**
     * Closes all the doors in the room.
     */
    void closeDoors();

    /**
     * Opens all the doors in the room.
     */
    void openDoors();
}
