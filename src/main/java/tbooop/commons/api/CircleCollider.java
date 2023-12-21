package tbooop.commons.api;

import tbooop.commons.Point2d;

/** Representation of a circular collider. */
public interface CircleCollider {

    /** @return the center of the collider. */
    Point2d getCenter();

    /** @return the radius of the collider. */
    double getRadius();

    /**
     * @param other the other collider to check.
     * @return whether the two circles are overlapping (colliding).
     */
    boolean isColliding(CircleCollider other);

}
