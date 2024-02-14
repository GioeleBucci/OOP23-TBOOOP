package tbooop.model.dungeon.rooms.api;

import tbooop.commons.api.CardinalDirection;
import tbooop.commons.api.Point2d;
import tbooop.commons.api.RoomBounds;
import tbooop.commons.impl.Point2dImpl;

/**
 * An enumeration representing the positions of doors in a dungeon floor.
 */
public enum DoorPositions {

    /** Top door's position. */
    TOP(new Point2dImpl(RoomBounds.WIDTH / 2, -DoorPositions.OFFSET)),
    /** Bottom door's position. */
    BOTTOM(new Point2dImpl(RoomBounds.WIDTH / 2,
            RoomBounds.HEIGHT + DoorPositions.OFFSET)),
    /** Left door's position. */
    LEFT(new Point2dImpl(-DoorPositions.OFFSET, RoomBounds.HEIGHT / 2)),
    /** Right door's position. */
    RIGHT(new Point2dImpl(RoomBounds.WIDTH + DoorPositions.OFFSET, RoomBounds.HEIGHT / 2));

    private static final double OFFSET = 20; // offset from the room's bounds
    private final Point2d position;

    DoorPositions(final Point2dImpl position) {
        this.position = position;
    }

    /**
     * Returns the position of the door.
     * 
     * @return the position of the door
     */
    public Point2d getPosition() {
        return position;
    }

    /**
     * Returns the position of the door.
     * 
     * @param direction where the door will be placed
     * @return the position of the door
     */
    public static Point2d getDoorPosition(final CardinalDirection direction) {
        return switch (direction) {
            case UP -> TOP.getPosition();
            case DOWN -> BOTTOM.getPosition();
            case LEFT -> LEFT.getPosition();
            case RIGHT -> RIGHT.getPosition();
            default -> throw new IllegalArgumentException("Invalid direction: " + direction);
        };
    }
}
