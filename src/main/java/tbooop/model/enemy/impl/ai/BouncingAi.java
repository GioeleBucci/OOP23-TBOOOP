package tbooop.model.enemy.impl.ai;

import java.util.Objects;

import tbooop.commons.Point2dImpl;
import tbooop.commons.RoomBounds;
import tbooop.commons.api.Point2d;
import tbooop.model.enemy.api.ai.MovementAi;

/**
 * A BouncingAi is a movement ai that moves in a straight line. After
 * colliding with a map bound it bounces and changes direction.
 */
public class BouncingAi implements MovementAi {

    private Point2d direction;
    private final double radius;

    /**
     * Creates an instance of a BouncingAi.
     * The parameter initialDirection determines the first direction
     * that the ai should follow.
     * 
     * @param initialDirection the initial direction
     * @param radius the ai's collider radius
     * @throws NullPointerException if initialDirection is null
     * @throws IllegarArgumentException if radius is negative
     */
    public BouncingAi(final Point2d initialDirection, final double radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("radius can't be negative");
        }
        this.direction = Objects.requireNonNull(initialDirection);
        this.radius = radius;
    }

    /** {@inheritDoc} */
    @Override
    public Point2d newPosition(final Point2d initialPosition, final long deltaTime, final double velocity) {
        Point2d tempPos = nextPos(Objects.requireNonNull(initialPosition), deltaTime, velocity);
        if (tempPos.getX() - this.radius <= 0) {
            tempPos = new Point2dImpl(this.radius, tempPos.getY());
            this.invertDirectionOnX();
        } else if (tempPos.getX() + this.radius >= RoomBounds.WIDTH) {
            tempPos = new Point2dImpl(RoomBounds.WIDTH - this.radius, tempPos.getY());
            this.invertDirectionOnX();
        }
        if (tempPos.getY() - this.radius <= 0) {
            tempPos = new Point2dImpl(tempPos.getX(), this.radius);
            this.invertDirectionOnY();
        } else if (tempPos.getY() + this.radius >= RoomBounds.HEIGHT) {
            tempPos = new Point2dImpl(tempPos.getX(), RoomBounds.HEIGHT - this.radius);
            this.invertDirectionOnY();
        }
        return nextPos(tempPos, deltaTime, velocity);
    }

    private void invertDirectionOnX() {
        this.direction = new Point2dImpl(-(this.direction.getX()), this.direction.getY());
    }

    private void invertDirectionOnY() {
        this.direction = new Point2dImpl(this.direction.getX(), -(this.direction.getY()));
    }

    private Point2d nextPos(final Point2d initialPosition, final long deltaTime, final double velocity) {
        return this.direction
            .mul(velocity)
            .mul(deltaTime)
            .add(initialPosition);
    }

}
