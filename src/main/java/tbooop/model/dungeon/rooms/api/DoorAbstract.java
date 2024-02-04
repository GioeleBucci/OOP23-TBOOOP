package tbooop.model.dungeon.rooms.api;

import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.unmovable.UnmovableAbs;

/**
 * Abstract base class for doors.
 */
public abstract class DoorAbstract extends UnmovableAbs implements Door {

    private static final double COLLIDER_RADIUS = 2;

    private final RoomUnmodifiable room;

    /**
     * Constructs a DoorAbstract object with the specified position and room.
     * @param position the position of the door
     * @param room the room that the door takes to
     */
    protected DoorAbstract(Point2d position, RoomUnmodifiable room) {
        super(position, COLLIDER_RADIUS, GameTag.DOOR);
        this.room = room;
    }

    /**
     * Gets the room that the door belongs to.
     * @return the room that the door belongs to
     */
    public RoomUnmodifiable getRoom() {
        return this.room;
    }

    @Override
    public void updateState(long deltaTime) {
    }

}
