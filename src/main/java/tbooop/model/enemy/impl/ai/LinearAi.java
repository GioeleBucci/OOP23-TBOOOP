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

    private static final double UPPER_BOUND = RoomBounds.HEIGHT * 0.2;
    private static final double LOWER_BOUND = RoomBounds.HEIGHT * 0.8;
    private static final double LEFT_BOUND = RoomBounds.WIDTH * 0.2;
    private static final double RIGHT_BOUND = RoomBounds.WIDTH * 0.8;
    private Point2ds direction;

    /**
     * Creates a new istance of a LinearAi.
     * The parameter's value determines if the movement pattern will
     * be left-right or up-down.
     * 
     * @param initialDirection the initial direction.
     */
    public LinearAi(final Point2ds initialDirection) {
        this.direction = Objects.requireNonNull(initialDirection);
    }

    /** {@inheritDoc} */
    @Override
    public Point2d newPosition(final Point2d initialPosition,
    final long deltaTime, final double velocity) {
        switch (this.direction) {
            case UP, DOWN -> this.checkPosition(initialPosition, UPPER_BOUND, LOWER_BOUND);
            case LEFT, RIGHT -> this.checkPosition(initialPosition, LEFT_BOUND, RIGHT_BOUND);
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

    private void checkPosition(final Point2d pos,
    final double firstBound, final double secondBound) {
        if (pos.getY() < firstBound || pos.getY() > secondBound) {
            this.direction = this.switchDirection();
        }
    }

}
