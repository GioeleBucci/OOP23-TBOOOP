package tbooop.commons;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import tbooop.commons.api.Coordinate;

/**
 * This class represents a 2-dimensional point.
 * <p>
 * Instances of this class are guaranteed to be immutable.
 */
public class Point2d implements Coordinate {

    /** Expands to {@code new Point2d(0, 0)} */
    public static final Point2d ZERO = new Point2d(0, 0);

    private final Vector2D vector;

    /**
     * Builds a Point from a pair of coordinates.
     * 
     * @param x abscissa
     * @param y ordinate
     */
    public Point2d(final double x, final double y) {
        vector = new Vector2D(x, y);
    }

    /**
     * Builds a Point from another Point instance.
     * 
     * @param point the point to clone
     */
    public Point2d(final Point2d point) {
        this(point.getX(), point.getY());
    }

    /** {@inheritDoc} */
    @Override
    public double getX() {
        return vector.getX();
    }

    /** {@inheritDoc} */
    @Override
    public double getY() {
        return vector.getY();
    }

    /** {@inheritDoc} */
    @Override
    public Coordinate add(final Coordinate coord) {
        return toP2d(this.vector.add(toV2d(coord)));
    }

    /** {@inheritDoc} */
    @Override
    public Coordinate subtract(final Coordinate coord) {
        return toP2d(this.vector.subtract(toV2d(coord)));
    }

    /** {@inheritDoc} */
    @Override
    public double distance(final Coordinate coord) {
        return this.vector.distance(toV2d(coord));
    }

    /**
     * Two elements of this class are considered equal if they have the same
     * coordinates.
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Coordinate) {
            return this.vector.equals(toV2d((Coordinate) obj));
        }
        return false;
    }

    /** Returns an hash code value. */
    @Override
    public int hashCode() {
        return this.vector.hashCode();
    }

    /** Returns a string representation. */
    @Override
    public String toString() {
        return this.vector.toString();
    }

    /**
     * Returns the hidden Vector2D.
     * 
     * @return a Vector2D (apache math commons)
     * @see Vector2D
     */
    protected Vector2D getVector() {
        return this.vector;
    }

    /**
     * Converts to a Vector2d.
     * 
     * @param p Vector/Point to convert
     * @return a Vector2D
     * @see Vector2D
     */
    protected static Vector2D toV2d(final Coordinate p) {
        return new Vector2D(p.getX(), p.getY());
    }

    private static Coordinate toP2d(final Vector2D v) {
        return new Point2d(v.getX(), v.getY());
    }

}
