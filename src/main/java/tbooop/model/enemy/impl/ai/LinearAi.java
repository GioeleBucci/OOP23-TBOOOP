package tbooop.model.enemy.impl.ai;

import java.util.Objects;

import tbooop.commons.api.Direction;
import tbooop.commons.api.Point2d;
import tbooop.commons.api.RoomBounds;
import tbooop.model.enemy.api.ai.AbstractAi;

/**
 * A LinearAi is a MovementAi that moves either up and down or left and right.
 */
public class LinearAi extends AbstractAi {

    private static final double UPPER_BOUND = RoomBounds.HEIGHT * 0.1;
    private static final double LOWER_BOUND = RoomBounds.HEIGHT * 0.8;
    private static final double LEFT_BOUND = RoomBounds.WIDTH * 0.1;
    private static final double RIGHT_BOUND = RoomBounds.WIDTH * 0.9;
    private final double radius;
    private Direction cardinalDirection;

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
    public LinearAi(final Direction initialDirection, final double radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("radius can't be negative");
        }
        this.cardinalDirection = Objects.requireNonNull(initialDirection);
        this.radius = radius;
    }

    /** {@inheritDoc} */
    @Override
    public Point2d newPosition(final Point2d initialPosition, final long deltaTime, final double velocity) {
        super.checkParameters(initialPosition, deltaTime);
        switch (this.cardinalDirection) {
            case UP, DOWN -> this.checkPosition(initialPosition.getY(),
                UPPER_BOUND - radius, LOWER_BOUND + radius);
            case LEFT, RIGHT -> this.checkPosition(initialPosition.getX(),
                LEFT_BOUND - radius, RIGHT_BOUND + radius);
            default -> { }
        }
        super.setDirection(this.cardinalDirection.toP2d());
        return super.nextPos(initialPosition, deltaTime, velocity);
    }

    private Direction switchDirection() {
        return switch (this.cardinalDirection) {
            case UP -> Direction.DOWN;
            case DOWN -> Direction.UP;
            case RIGHT -> Direction.LEFT;
            case LEFT -> Direction.RIGHT;
        };
    }

    private void checkPosition(final double coordValue,
    final double firstBound, final double secondBound) {
        if (coordValue < firstBound || coordValue > secondBound) {
            this.cardinalDirection = this.switchDirection();
        }
    }

}
