package tbooop.commons.impl;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import tbooop.commons.api.Point2d;
import tbooop.commons.api.Vector2d;

/**
 * This class represents a 2-dimensional Vector.
 * <p>
 * Instances of this class are guaranteed to be immutable.
 */
public final class Vector2dImpl implements Vector2d {

    /** Expands to {@code new Vector2d(0, 0)}. */
    public static final Vector2d ZERO = new Vector2dImpl(0, 0);

    private final Vector2D vector;

    /**
     * Builds a Vector from a pair of coordinates.
     * 
     * @param x abscissa
     * @param y ordinate
     */
    public Vector2dImpl(final double x, final double y) {
        vector = new Vector2D(x, y);
    }

    /**
     * Builds a Vector from another Point instance.
     * 
     * @param vector the vector to clone
     */
    public Vector2dImpl(final Vector2d vector) {
        this(vector.getX(), vector.getY());
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
    public double getLenght() {
        return vector.getNorm();
    }

    /** {@inheritDoc} */
    @Override
    public Vector2d normalize() {
        return toVector(this.vector.normalize());
    }

    /** {@inheritDoc} */
    @Override
    public Vector2d negate() {
        return toVector(this.vector.negate());
    }

    /** {@inheritDoc} */
    @Override
    public Vector2d scalarMultiply(final double a) {
        return toVector(this.vector.scalarMultiply(a));
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Vector2d && this.vector.equals(toV2D((Vector2d) obj));
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
    public Point2d toP2d() {
        return new Point2dImpl(getX(), getY());
    }

    private Vector2d toVector(final Vector2D v) {
        return new Vector2dImpl(v.getX(), v.getY());
    }

    private Vector2D toV2D(final Vector2d p) {
        return new Vector2D(p.getX(), p.getY());
    }

}
