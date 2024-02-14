package tbooop.model.enemy.impl.ai;

import java.util.Objects;

import tbooop.commons.api.Point2d;
import tbooop.commons.api.RoomBounds;
import tbooop.commons.impl.Point2dImpl;
import tbooop.model.enemy.api.ai.AbstractAi;

/**
 * A BouncingAi is a movement ai that moves in a straight line. After
 * colliding with a map bound it bounces and changes direction.
 */
public class BouncingAi extends AbstractAi {

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
        super.setDirection(Objects.requireNonNull(initialDirection));
        this.radius = radius;
    }

    /** {@inheritDoc} */
    @Override
    public Point2d newPosition(final Point2d initialPosition, final long deltaTime, final double velocity) {
        super.checkParameters(initialPosition, deltaTime);
        Point2d tempPos = super.nextPos(initialPosition, deltaTime, velocity);
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
        return super.nextPos(tempPos, deltaTime, velocity);
    }

    private void invertDirectionOnX() {
        super.setDirection(new Point2dImpl(-(super.getDirection().getX()), super.getDirection().getY()));
    }

    private void invertDirectionOnY() {
        super.setDirection(new Point2dImpl(super.getDirection().getX(), -(super.getDirection().getY())));
    }

}
