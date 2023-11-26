package tbooop.commons;

import java.util.List;

import javafx.geometry.Point2D;

/** Cardinal directions, expressed as Point2Ds. */
public enum Directions {

    /** expands to {@code new Point2D(0, -1)}. */
    UP(new Point2D(0, -1)),
    /** expands to {@code new Point2D(0, 1)}. */
    DOWN(new Point2D(0, 1)),
    /** expands to {@code new Point2D(1, 0)}. */
    LEFT(new Point2D(-1, 0)),
    /** expands to {@code new Point2D(1, 0)}. */
    RIGHT(new Point2D(1, 0));

    private final Point2D dir;

    Directions(final Point2D p2d) {
        this.dir = p2d;
    }

    /**
     * A list containing all 4 directions.
     * 
     * @return a list of all 4 axis directions.
     */
    public static List<Directions> getAll() {
        return List.of(UP, DOWN, LEFT, RIGHT);
    }

    /**
     * Get relative p2d.
     * 
     * @return The associated p2d
     */
    public Point2D toP2d() {
        return this.dir;
    }
}
