package tbooop.commons.api;

import tbooop.commons.Point2d;

/**
 * Represents a simple 2-dimensional point.
 */
public interface SimplePoint {

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
    double distance(SimplePoint c);

    /**
     * Add a value to this element.
     * 
     * @param point the Point to add
     * @return a new Point
     */
    Point2d add(SimplePoint point);

    /**
     * Subtract a value to this element.
     * 
     * @param point the Point to subtract
     * @return a new Point
     */
    Point2d subtract(SimplePoint point);

}
