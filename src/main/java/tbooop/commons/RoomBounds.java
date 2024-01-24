package tbooop.commons;

import tbooop.commons.api.Point2d;

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
     * Whether the position passed is out of room bounds.
     * 
     * @param position the position to check
     * @return {@code true} if the position is out of bounds
     */
    public static boolean outOfBounds(final Point2d position) {
        return position.getX() < 0 || position.getX() >= WIDTH
                || position.getY() < 0 || position.getY() >= HEIGHT;
    }

    /**
     * Don't let anyone instantiate this class.
     */
    private RoomBounds() {
    }

}
