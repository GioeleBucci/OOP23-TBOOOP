package tbooop.model.dungeon.doors.api;

import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.unmovable.UnmovableAbs;
import tbooop.model.dungeon.rooms.api.RoomUnmodifiable;

/**
 * Abstract base class for doors.
 */
public abstract class AbstractDoor extends UnmovableAbs implements Door {

    private static final double COLLIDER_RADIUS = 20;

    private final RoomUnmodifiable room;

    private boolean isOpen = true;

    /**
     * Constructs a DoorAbstract object with the specified position and room.
     * 
     * @param position the position of the door
     * @param room     the room that the door takes to
     */
    protected AbstractDoor(final Point2d position, final RoomUnmodifiable room) {
        super(position, COLLIDER_RADIUS, GameTag.DOOR);
        this.room = room;
    }

    /** {@inheritDoc} */
    @Override
    public RoomUnmodifiable getRoom() {
        return this.room;
    }

    /** {@inheritDoc} */
    @Override
    public void close() {
        this.isOpen = false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isOpen() {
        return this.isOpen;
    }

    /**
     * Sets the door open or closed.
     * 
     * @param isOpen the new state of the door
     */
    protected void setOpen(final boolean isOpen) {
        this.isOpen = isOpen;
    }

}
