package tbooop.commons.impl;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import tbooop.commons.api.Point2d;
import tbooop.commons.api.Vector2d;

/**
 * This class represents a 2-dimensional point.
 * <p>
 * Instances of this class are guaranteed to be immutable.
 */
public final class Point2dImpl implements Point2d {

    /** Expands to {@code new Point2d(0, 0)}. */
    public static final Point2dImpl ZERO = new Point2dImpl(0, 0);

    private final Vector2D vector;

    /**
     * Builds a Point from a pair of coordinates.
     * 
     * @param x abscissa
     * @param y ordinate
     */
    public Point2dImpl(final double x, final double y) {
        vector = new Vector2D(x, y);
    }

    /**
     * Builds a Point from another Point instance.
     * 
     * @param point the point to clone
     */
    public Point2dImpl(final Point2d point) {
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
    public Point2d add(final Point2d point) {
        return toP2d(this.vector.add(toV2D(point)));
    }

    /** {@inheritDoc} */
    @Override
    public Point2d subtract(final Point2d point) {
        return toP2d(this.vector.subtract(toV2D(point)));
    }

    /** {@inheritDoc} */
    @Override
    public Point2d mul(final double scalar) {
        return toP2d(this.vector.scalarMultiply(scalar));
    }

    /** {@inheritDoc} */
    @Override
    public double distance(final Point2d coord) {
        return this.vector.distance(toV2D(coord));
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Point2d) {
            return this.vector.equals(toV2D((Point2d) obj));
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return this.vector.hashCode();
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return this.vector.toString();
    }

    /** {@inheritDoc} */
    @Override
    public Vector2d toV2d() {
        return new Vector2dImpl(getX(), getY());
    }

    private Point2d toP2d(final Vector2D v) {
        return new Point2dImpl(v.getX(), v.getY());
    }

    private Vector2D toV2D(final Point2d p) {
        return new Vector2D(p.getX(), p.getY());
    }

}
