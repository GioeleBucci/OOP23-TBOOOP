package tbooop.model.dungeon.rooms.api;

import tbooop.commons.Point2ds;

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
    void addDoor(Point2ds direction, Door door);

    /** Flags the room as explored. Cannot be undone. */
    void setExplored();

}
