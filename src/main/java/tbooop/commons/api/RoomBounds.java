package tbooop.commons.api;

import tbooop.commons.impl.Point2dImpl;

/**
 * Dimensions for the rooms.
 * <br>
 * These informations can be used for movement calculations.
 */
public final class RoomBounds {

    /**
     * The height of a room (not necessairly in pixels).
     */
    public static final double HEIGHT = 207;
    /**
     * The width of a room (not necessairly in pixels).
     */
    public static final double WIDTH = 364;

    /**
     * The center of the room.
     * Expands to: 
     * <br></br>
     * {@code new Point2dImpl(RoomBounds.WIDTH / 2, RoomBounds.HEIGHT / 2)}
     */
    public static final Point2d CENTER = new Point2dImpl(WIDTH / 2, HEIGHT / 2);

    /**
     * Whether the position passed is out of room bounds.
     * 
     * @param position the position to check
     * @return {@code true} if the position is out of bounds
     */
    public static boolean outOfBounds(final Point2d position) {
        return nearOutOfBounds(position, 0);
    }

    /**
     * Whether a circle with center in the passed position goes out of room bounds.
     * 
     * @param position the position to check (center of the circle)
     * @param radius   the radius of the circle to check
     * @return {@code true} if the position is out of bounds
     */
    public static boolean nearOutOfBounds(final Point2d position, double radius) {
        return position.getX() < 0 + radius || position.getX() >= (WIDTH - radius)
                || position.getY() < 0 + radius || position.getY() >= (HEIGHT - radius);
    }

    /**
     * Don't let anyone instantiate this class.
     */
    private RoomBounds() {
    }

}
