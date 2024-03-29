package tbooop.model.dungeon.doors.api;

import tbooop.model.core.api.unmovable.Unmovable;
import tbooop.model.dungeon.rooms.api.RoomUnmodifiable;

/** A door representation. */
public interface DoorUnmodifiable extends Unmovable {

    /** @return the room this door leads to. */
    RoomUnmodifiable getRoom();

    /** @return whether this door is special or not. */
    boolean isSpecial();

    /** @return Whether this door is closed or not. */
    boolean isOpen();

}
