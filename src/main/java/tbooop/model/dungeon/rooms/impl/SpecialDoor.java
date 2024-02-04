package tbooop.model.dungeon.rooms.impl;

import tbooop.commons.api.Point2d;
import tbooop.model.dungeon.rooms.api.Room;
import tbooop.model.dungeon.rooms.api.RoomUnmodifiable;

/**
 * A special door spawns as locked and can be unlocked using a key.
 * It leads to a special room.
 */
public class SpecialDoor extends RegularDoor {

    private boolean isLocked = true;

    /**
     * Creates a new special door.
     * 
     * @param pos  the position of the door
     * @param room the room this door leads to
     */
    public SpecialDoor(final Point2d pos, final RoomUnmodifiable room) {
        super(pos, room);
    }

    /** @return Whether this door is locked or not. */
    public boolean isLocked() {
        return isLocked;
    }

    /** Unlocks this door. Cannot be undone. */
    public void unlock() {
        this.isLocked = false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isSpecial() {
        return true;
    }

}
