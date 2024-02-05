package tbooop.model.dungeon.rooms.api;

import tbooop.commons.Point2dImpl;
import tbooop.commons.Point2ds;
import tbooop.commons.RoomBounds;
import tbooop.commons.api.Point2d;

/**
 * An enumeration representing the positions of doors in a dungeon floor.
 */
public enum DoorPositions {

    /** Top door's position. */
    TOP(new Point2dImpl(RoomBounds.WIDTH / 2, 0)),
    /** Bottom door's position. */
    BOTTOM(new Point2dImpl(RoomBounds.WIDTH / 2,
            RoomBounds.HEIGHT)),
    /** Left door's position. */
    LEFT(new Point2dImpl(0, RoomBounds.HEIGHT / 2)),
    /** Right door's position. */
    RIGHT(new Point2dImpl(RoomBounds.WIDTH, RoomBounds.HEIGHT / 2));

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
    public static Point2d getDoorPosition(final Point2ds direction) {
        return switch (direction) {
            case UP -> TOP.getPosition();
            case DOWN -> BOTTOM.getPosition();
            case LEFT -> LEFT.getPosition();
            case RIGHT -> RIGHT.getPosition();
            default -> throw new IllegalArgumentException("Invalid direction: " + direction);
        };
    }
}
