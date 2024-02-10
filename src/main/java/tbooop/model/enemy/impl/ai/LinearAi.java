package tbooop.model.enemy.impl.ai;

import java.util.Objects;

import tbooop.commons.Point2ds;
import tbooop.commons.RoomBounds;
import tbooop.commons.api.Point2d;
import tbooop.model.enemy.api.ai.MovementAi;

/**
 * A LinearAi is a MovementAi that moves either up and down or left and right.
 */
public class LinearAi implements MovementAi {

    private static final double UPPER_BOUND = RoomBounds.HEIGHT * 0.1;
    private static final double LOWER_BOUND = RoomBounds.HEIGHT * 0.9;
    private static final double LEFT_BOUND = RoomBounds.WIDTH * 0.1;
    private static final double RIGHT_BOUND = RoomBounds.WIDTH * 0.9;
    private final double radius;
    private Point2ds direction;

    /**
     * Creates a new istance of a LinearAi.
     * The parameter's value determines if the movement pattern will
     * be left-right or up-down.
     * 
     * @param initialDirection the initial direction.
     * @param radius the ai's collider radius
     * @throws NullPointerException if initialDirection is null
     * @throws IllegalArgumentException if radius is negative
     */
    public LinearAi(final Point2ds initialDirection, final double radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("radius can't be negative");
        }
        this.direction = Objects.requireNonNull(initialDirection);
        this.radius = radius;
    }

    /** {@inheritDoc} */
    @Override
    public Point2d newPosition(final Point2d initialPosition,
    final long deltaTime, final double velocity) {
        if (deltaTime < 0) {
            throw new IllegalArgumentException("deltaTime can't be negative");
        }
        switch (this.direction) {
            case UP, DOWN -> this.checkPosition(initialPosition.getY(),
                UPPER_BOUND - radius, LOWER_BOUND + radius);
            case LEFT, RIGHT -> this.checkPosition(initialPosition.getX(),
                LEFT_BOUND - radius, RIGHT_BOUND + radius);
            default -> { }
        }
        return this.direction.toP2d()
            .mul(velocity)
            .mul(deltaTime)
            .add(initialPosition);
    }

    private Point2ds switchDirection() {
        return switch (this.direction) {
            case UP -> Point2ds.DOWN;
            case DOWN -> Point2ds.UP;
            case RIGHT -> Point2ds.LEFT;
            case LEFT -> Point2ds.RIGHT;
        };
    }

    private void checkPosition(final double coordValue,
    final double firstBound, final double secondBound) {
        if (coordValue < firstBound || coordValue > secondBound) {
            this.direction = this.switchDirection();
        }
    }

}
