package tbooop.model.dungeon.rooms.impl;

import tbooop.commons.api.Point2d;
import tbooop.model.core.api.unmovable.UnmovableName;
import tbooop.model.dungeon.rooms.api.DoorAbstract;
import tbooop.model.dungeon.rooms.api.RoomUnmodifiable;

/**
 * A regular door has no locks and takes to a regular room.
 */
public class RegularDoor extends DoorAbstract {

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

    @Override
    public UnmovableName getObjectName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getObjectName'");
    }

}
