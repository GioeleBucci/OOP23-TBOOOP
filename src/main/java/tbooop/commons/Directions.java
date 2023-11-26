package tbooop.commons;

import javafx.geometry.Point2D;
import java.util.List;

/**
 * 4 axis directional costants, expressed as Point2Ds.
 */
public final class Directions {

    private Directions() {
        throw new IllegalStateException("Utility class");
    }

    /** expands to {@code new Point2D(0, -1)}. */
    public static final Point2D UP = new Point2D(0, -1);
    /** expands to {@code new Point2D(0, 1)}. */
    public static final Point2D DOWN = new Point2D(0, 1);
    /** expands to {@code new Point2D(1, 0)}. */
    public static final Point2D LEFT = new Point2D(-1, 0);
    /** expands to {@code new Point2D(1, 0)}. */
    public static final Point2D RIGHT = new Point2D(1, 0);
    /** A list containing all 4 directions. */
    public static final List<Point2D> ALL_DIRECTIONS = List.of(UP, DOWN, LEFT, RIGHT);
}
