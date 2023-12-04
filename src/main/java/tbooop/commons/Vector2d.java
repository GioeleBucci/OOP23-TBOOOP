package tbooop.commons;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import tbooop.commons.api.SimpleVector;

/**
 * This class represents a 2-dimensional Vector.
 * <p>
 * Instances of this class are guaranteed to be immutable.
 */
public final class Vector2d implements SimpleVector {

    /** Expands to {@code new Vector2d(0, 0)}. */
    public static final Vector2d ZERO = new Vector2d(0, 0);

    private final Vector2D vector;

    /**
     * Builds a Vector from a pair of coordinates.
     * 
     * @param x abscissa
     * @param y ordinate
     */
    public Vector2d(final double x, final double y) {
        vector = new Vector2D(x, y);
    }

    /**
     * Builds a Vector from another Point instance.
     * 
     * @param vector the vector to clone
     */
    public Vector2d(final Vector2d vector) {
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
    public SimpleVector normalize() {
        return toVector(this.vector.normalize());
    }

    /** {@inheritDoc} */
    @Override
    public SimpleVector negate() {
        return toVector(this.vector.negate());
    }

    /** {@inheritDoc} */
    @Override
    public SimpleVector scalarMultiply(final double a) {
        return toVector(this.vector.scalarMultiply(a));
    }

    /**
     * Two Vectors are considered equal if they have the same
     * coordinates.
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof SimpleVector) {
            return this.vector.equals(toV2d((SimpleVector) obj));
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

    private Vector2d toVector(final Vector2D v) {
        return new Vector2d(v.getX(), v.getY());
    }

    private static Vector2D toV2d(final SimpleVector p) {
        return new Vector2D(p.getX(), p.getY());
    }

}
