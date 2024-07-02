package tbooop.commons.api;

import java.util.List;

import tbooop.commons.impl.Point2dImpl;

/** Directions, expressed as Point2ds. */
public enum Direction {

    /** expands to {@code new Point2d(0, -1)}. */
    UP(new Point2dImpl(0, -1)),
    /** expands to {@code new Point2d(0, 1)}. */
    DOWN(new Point2dImpl(0, 1)),
    /** expands to {@code new Point2d(-1, 0)}. */
    LEFT(new Point2dImpl(-1, 0)),
    /** expands to {@code new Point2d(1, 0)}. */
    RIGHT(new Point2dImpl(1, 0));

    private final Point2d dir;

    Direction(final Point2dImpl p2d) {
        this.dir = p2d;
    }

    /**
     * A list containing all 4 directions.
     * 
     * @return a list of all 4 axis directions.
     */
    public static List<Direction> getAll() {
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
