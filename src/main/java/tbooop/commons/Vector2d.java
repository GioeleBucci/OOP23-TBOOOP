package tbooop.commons;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import tbooop.commons.api.SimpleVector;

/**
 * This class represents a 2-dimensional Vector.
 * <p>
 * Instances of this class are guaranteed to be immutable.
 */
public class Vector2d extends Point2d implements SimpleVector {

    /** Expands to {@code new Vector2d(0, 0)}*/ 
    public static final Vector2d ZERO = new Vector2d(0, 0);

    /**
     * Builds a Point from a pair of coordinates.
     * 
     * @param x abscissa
     * @param y ordinate
     */
    public Vector2d(final double x, final double y) {
        super(x, y);
    }

    /**
     * Builds a Vector from another Vector instance.
     * 
     * @param v the vector to clone
     */
    public Vector2d(final Vector2d v) {
        super(v);
    }

    /** {@inheritDoc} */
    @Override
    public SimpleVector normalize() {
        return toVector(this.getVector().normalize());
    }

    /** {@inheritDoc} */
    @Override
    public SimpleVector negate() {
        return toVector(this.getVector().negate());
    }

    /** {@inheritDoc} */
    @Override
    public SimpleVector scalarMultiply(final double a) {
        return toVector(this.getVector().scalarMultiply(a));
    }

    private Vector2d toVector(final Vector2D v) {
        return new Vector2d(v.getX(), v.getY());
    }

}
