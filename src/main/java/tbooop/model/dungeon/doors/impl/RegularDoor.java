package tbooop.model.dungeon.doors.impl;

import tbooop.commons.api.Point2d;
import tbooop.model.dungeon.doors.api.AbstractDoor;
import tbooop.model.dungeon.rooms.api.RoomUnmodifiable;

/**
 * A regular door has no locks and takes to a regular room.
 */
public class RegularDoor extends AbstractDoor {

    /**
     * Creates a new regular door.
     * 
     * @param pos  the position of the door
     * @param room the room this door leads to
     */
    public RegularDoor(final Point2d pos, final RoomUnmodifiable room) {
        super(pos, room);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isSpecial() {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void open() {
        this.setOpen(true);
    }

}
