package tbooop.commons.api;

/**
 * A Coordinate represents a pair of values in a 2-dimensional space.
 * <p>
 * It can be interpreted both as a Point and as a Vector.
 * 
 * @see {@link tbooop.commons.Point2d Point2d}
 * @see {@link tbooop.commons.Vector2d Vector2d}
 */
public interface Coordinate {

    /**
     * Get the abscissa of this element.
     * 
     * @return the abscissa
     */
    double getX();

    /**
     * Get the ordinate of this element.
     * 
     * @return the ordinate
     */
    double getY();

    /**
     * Add a value to this element.
     * 
     * @param c the Point/Vector to add
     * @return a new Point/Vector
     */
    Coordinate add(Coordinate c);

    /**
     * Subtract a value to this element.
     * 
     * @param c the Point/Vector to subtract
     * @return a new Point/Vector
     */
    Coordinate subtract(Coordinate c);

    /**
     * The distance of this element from a Point/Vector.
     * 
     * @param c the Point/Vector to measure the distance from
     * @return a new Point/Vector
     */
    double distance(Coordinate c);
}
