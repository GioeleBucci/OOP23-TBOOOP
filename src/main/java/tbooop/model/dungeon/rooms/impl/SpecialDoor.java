package tbooop.model.dungeon.rooms.impl;

import tbooop.model.dungeon.rooms.api.Room;

/**
 * A special door spawns as locked and can be unlocked using a key.
 * It leads to a special room.
 */
public class SpecialDoor extends RegularDoor {

    private boolean isLocked = true;

    /**
     * Creates a special door.
     * 
     * @param room the room this door leads to
     */
    public SpecialDoor(final Room room) {
        super(room);
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
    public String toString() {
        return "SpecialDoor";
    }

    /** {@inheritDoc} */
    @Override
    public boolean isSpecial() {
        return true;
    }

}
