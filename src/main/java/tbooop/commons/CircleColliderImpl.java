package tbooop.commons;

import tbooop.commons.api.CircleCollider;
import java.util.Objects;

/** A simple circle collider. */
public final class CircleColliderImpl implements CircleCollider {

    private final Point2d center;

    private final double radius;

    /**
     * Creates a new circle collider.
     * 
     * @param center the center of the circle
     * @param radius the radius of the circle
     * @throws NullPointerException if center is null
     */
    public CircleColliderImpl(final Point2d center, final double radius) {
        this.center = Objects.requireNonNull(center);
        this.radius = radius;
    }

    /** {@inheritDoc} */
    @Override
    public Point2d getCenter() {
        return center;
    }

    /** {@inheritDoc} */
    @Override
    public double getRadius() {
        return radius;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isColliding(final CircleCollider other) {
        return center.distance(other.getCenter()) <= (radius + other.getRadius());
    }
}
