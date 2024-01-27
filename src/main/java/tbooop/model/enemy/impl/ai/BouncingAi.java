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

    /**
     * Creates an instance of a BouncingAi.
     * The parameter initialDirection determines the first direction
     * that the ai should follow.
     * 
     * @param initialDirection the initial direction
     * @throws NullPointerException if the parameter is null
     */
    public BouncingAi(final Point2d initialDirection) {
        this.direction = Objects.requireNonNull(initialDirection);
    }

    /** {@inheritDoc} */
    @Override
    public Point2d newPosition(final Point2d initialPosition, final long deltaTime, final double velocity) {
        final Point2d tempPos = nextPos(Objects.requireNonNull(initialPosition), deltaTime, velocity);
        if (tempPos.getX() <= 0 || tempPos.getX() >= RoomBounds.WIDTH) {
            this.invertDirectionOnX();
        } else if (tempPos.getY() <= 0 || tempPos.getY() >= RoomBounds.HEIGHT) {
            this.invertDirectionOnY();
        }
        return nextPos(initialPosition, deltaTime, velocity);
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
