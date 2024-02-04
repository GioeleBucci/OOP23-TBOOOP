package tbooop.model.dungeon.rooms.api;

import tbooop.model.core.api.unmovable.Unmovable;

/** A door representation. */
public interface Door extends Unmovable {

    /** @return the room this door leads to. */
    RoomUnmodifiable getRoom();

    /** @return whether this door is special or not. */
    boolean isSpecial();

}
