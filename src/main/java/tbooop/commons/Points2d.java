package tbooop.commons;

import java.util.List;

/** Cardinal directions, expressed as Point2ds. */
public enum Points2d {

    /** expands to {@code new Point2d(0, -1)}. */
    UP(new Point2d(0, -1)),
    /** expands to {@code new Point2d(0, 1)}. */
    DOWN(new Point2d(0, 1)),
    /** expands to {@code new Point2d(1, 0)}. */
    LEFT(new Point2d(-1, 0)),
    /** expands to {@code new Point2d(1, 0)}. */
    RIGHT(new Point2d(1, 0));

    private final Point2d dir;

    Points2d(final Point2d p2d) {
        this.dir = p2d;
    }

    /**
     * A list containing all 4 directions.
     * 
     * @return a list of all 4 axis directions.
     */
    public static List<Points2d> getAll() {
        return List.of(UP, DOWN, LEFT, RIGHT);
    }

    /**
     * Get relative p2d.
     * 
     * @return The associated p2d
     */
    public Point2d toP2d() {
        return this.dir;
    }
}
