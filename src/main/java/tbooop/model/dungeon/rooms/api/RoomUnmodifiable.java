package tbooop.model.dungeon.rooms.api;

import java.util.Map;

import tbooop.commons.Point2ds;

/**
 * An unmodifiable room representation.
 */
public interface RoomUnmodifiable {
    /**
     * The doors that this room has.
     * 
     * @return a map with an entry for each direction in which a door is present.
     * @see Point2ds
     */
    Map<Point2ds, DoorUnmodifiable> getDoorMap();

    /**
     * Whether this room is special or not.
     * 
     * @return whether the room is special
     */
    boolean isSpecial();

    /**
     * Whether this room is the first room or not.
     * Special criteria may apply to the first room.
     * 
     * @return whether the room is the first room of the floor
     */
    boolean isFirstRoom();

}
