package tbooop.model.dungeon.rooms.api;

import java.util.Map;

import tbooop.commons.api.CardinalDirection;
import tbooop.model.dungeon.doors.api.DoorUnmodifiable;

/**
 * An unmodifiable room representation.
 */
public interface RoomUnmodifiable {
    /**
     * The doors that this room has.
     * 
     * @return a map with an entry for each direction in which a door is present.
     * @see CardinalDirection
     */
    Map<CardinalDirection, DoorUnmodifiable> getDoorMap();

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

    /**
     * Whether this room is the boss room or not.
     * Special criteria may apply to the boss room.
     * 
     * @return whether the room is the boss room of the floor
     */
    boolean isBossRoom();

}
