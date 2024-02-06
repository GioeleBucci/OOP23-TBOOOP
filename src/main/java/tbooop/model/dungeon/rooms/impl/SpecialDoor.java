package tbooop.model.dungeon.rooms.impl;

import tbooop.commons.api.Point2d;
import tbooop.model.dungeon.rooms.api.DoorAbstract;
import tbooop.model.dungeon.rooms.api.DoorLockable;
import tbooop.model.dungeon.rooms.api.RoomUnmodifiable;

/**
 * A special door spawns as locked and can be unlocked using a key.
 * It leads to a special room.
 */
public class SpecialDoor extends DoorAbstract implements DoorLockable {

    private boolean isLocked = true;

    /**
     * Creates a new special door.
     * 
     * @param pos  the position of the door
     * @param room the room this door leads to
     */
    public SpecialDoor(final Point2d pos, final RoomUnmodifiable room) {
        super(pos, room);
        setOpen(false);
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
            this.setOpen(true);
        }
    }
}
