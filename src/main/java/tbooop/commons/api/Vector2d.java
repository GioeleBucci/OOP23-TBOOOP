package tbooop.commons.api;

/**
 * Represents a 2-dimensional vector.
 */
public interface Vector2d {

    /**
     * Get the abscissa of this vector.
     * 
     * @return the abscissa
     */
    double getX();

    /**
     * Get the ordinate of this vector.
     * 
     * @return the ordinate
     */
    double getY();

    /**
     * Get the lenght of this vector.
     * 
     * @return the Euclidean norm of the vector
     */
    double getLenght();

    /**
     * Get a normalized instance of the vector.
     * <p>
     * A normalized vector mantains it's original direction but his lenght = 1.
     * 
     * @return the normalized vector of this instance
     */
    Vector2d normalize();

    /**
     * Get the opposite of the instance.
     * 
     * @return a new vector which is the opposite of this one
     */
    Vector2d negate();

    /**
     * Multiply the vector by a scalar.
     * 
     * @param a scalar
     * @return a new vector
     */
    Vector2d scalarMultiply(double a);

    /**
     * Get a Point2d from this istance.
     * 
     * @return a new vector
     */
    Point2d toP2d();

}
