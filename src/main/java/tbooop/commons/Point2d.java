package tbooop.commons;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import tbooop.commons.api.SimplePoint;

/**
 * This class represents a 2-dimensional point.
 * <p>
 * Instances of this class are guaranteed to be immutable.
 */
public final class Point2d implements SimplePoint {

    /** Expands to {@code new Point2d(0, 0)}. */
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
    public Point2d add(final SimplePoint point) {
        return toP2d(this.vector.add(toV2d(point)));
    }

    /** {@inheritDoc} */
    @Override
    public Point2d subtract(final SimplePoint point) {
        return toP2d(this.vector.subtract(toV2d(point)));
    }

    /** {@inheritDoc} */
    @Override
    public Point2d mul(final double scalar) {
        return toP2d(this.vector.scalarMultiply(scalar));
    }

    /** {@inheritDoc} */
    @Override
    public double distance(final SimplePoint coord) {
        return this.vector.distance(toV2d(coord));
    }

    /**
     * Two vectors are considered equal if they have the same
     * coordinates.
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof SimplePoint) {
            return this.vector.equals(toV2d((SimplePoint) obj));
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

    private static Vector2D toV2d(final SimplePoint p) {
        return new Vector2D(p.getX(), p.getY());
    }

    private static Point2d toP2d(final Vector2D v) {
        return new Point2d(v.getX(), v.getY());
    }

}
