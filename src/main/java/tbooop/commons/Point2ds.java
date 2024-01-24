package tbooop.commons;

import java.util.List;

import tbooop.commons.api.Point2d;

/** Cardinal directions, expressed as Point2ds. */
public enum Point2ds {

    /** expands to {@code new Point2d(0, -1)}. */
    UP(new Point2dImpl(0, -1)),
    /** expands to {@code new Point2d(0, 1)}. */
    DOWN(new Point2dImpl(0, 1)),
    /** expands to {@code new Point2d(1, 0)}. */
    LEFT(new Point2dImpl(-1, 0)),
    /** expands to {@code new Point2d(1, 0)}. */
    RIGHT(new Point2dImpl(1, 0));

    private final Point2d dir;

    Point2ds(final Point2dImpl p2d) {
        this.dir = p2d;
    }

    /**
     * A list containing all 4 directions.
     * 
     * @return a list of all 4 axis directions.
     */
    public static List<Point2ds> getAll() {
        return List.of(UP, DOWN, LEFT, RIGHT);
    }

    /**
     * Get relative p2d.
     * 
     * @return The associated p2d
     */
    public Point2d toP2d() {
        return new Point2dImpl(this.dir);
    }
}
