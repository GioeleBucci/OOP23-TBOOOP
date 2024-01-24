package tbooop.commons.api;

/**
 * Represents a simple 2-dimensional point.
 */
public interface Point2d {

    /**
     * Get the abscissa of this point.
     * 
     * @return the abscissa
     */
    double getX();

    /**
     * Get the ordinate of this point.
     * 
     * @return the ordinate
     */
    double getY();

    /**
     * The distance of this point from another one.
     * 
     * @param c the Point to measure the distance from
     * @return the distance between the two points
     */
    double distance(Point2d c);

    /**
     * Add a value to this element.
     * 
     * @param point the Point to add
     * @return a new Point
     */
    Point2d add(Point2d point);

    /**
     * Subtract a value to this element.
     * 
     * @param point the Point to subtract
     * @return a new Point
     */
    Point2d subtract(Point2d point);

    /**
     * Multiply this element by a scalar.
     * 
     * @param scalar the scalar to multiply by
     * @return a new Point
     */
    Point2d mul(double scalar);

    /**
     * Get a Vector2d from this instance.
     * 
     * @return a new Point2d
     */
    Vector2d toV2d();

}
