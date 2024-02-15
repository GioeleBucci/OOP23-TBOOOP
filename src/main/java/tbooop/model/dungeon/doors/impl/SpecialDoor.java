package tbooop.model.dungeon.doors.impl;

import tbooop.commons.api.Point2d;
import tbooop.model.dungeon.doors.api.AbstractDoor;
import tbooop.model.dungeon.doors.api.DoorLockable;
import tbooop.model.dungeon.rooms.api.RoomUnmodifiable;

/**
 * A special door spawns as locked and can be unlocked using a key.
 * It leads to a special room.
 */
public class SpecialDoor extends AbstractDoor implements DoorLockable {

    private boolean isLocked = true;

    /**
     * Creates a new special door.
     * 
     * @param pos  the position of the door
     * @param room the room this door leads to
     */
    public SpecialDoor(final Point2d pos, final RoomUnmodifiable room) {
        super(pos, room);
        super.close(); // special doors are closed by default
    }

    /** {@inheritDoc} */
    @Override
    public boolean isSpecial() {
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isLocked() {
        return isLocked;
    }

    /** {@inheritDoc} */
    @Override
    public void unlock() {
        this.isLocked = false;
        this.open();
        // unlock the door on the other side as well
        final SpecialDoor other = (SpecialDoor) getRoom().getDoorMap().values().stream()
                .filter(d -> d instanceof DoorLockable)
                .findAny()
                .get();
        other.setLocked(false);
        other.open();
    }

    private void setLocked(final boolean isLocked) {
        this.isLocked = isLocked;
    }

    /** {@inheritDoc} */
    @Override
    public void open() {
        if (!isLocked) {
            super.open();
        }
    }
}
