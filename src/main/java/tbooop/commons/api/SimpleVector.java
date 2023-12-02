package tbooop.commons.api;

/**
 * Represents a 2-dimensional vector.
 * <p>
 * Instances of this class are guaranteed to be immutable.
 */
public interface SimpleVector extends Coordinate {

    /**
     * Get a normalized instance of the vector.
     * 
     * @return a new normalized vector
     */
    SimpleVector normalize();

    /**
     * Get the opposite of the instance.
     * 
     * @return a new vector which is the opposite of this one
     */
    SimpleVector negate();

    /**
     * Multiply the vector by a scalar.
     * 
     * @param a scalar
     * @return a new vector
     */
    SimpleVector scalarMultiply(double a);

}
