package tbooop.model.enemy.api.ai;

import tbooop.commons.api.Point2d;
import tbooop.commons.impl.Point2dImpl;

import java.util.Objects;

/**
 * AbstractAi contains some features that all concrete movement ai classes have in common.
 */
public abstract class AbstractAi implements MovementAi {

    private Point2d direction = new Point2dImpl(0, 0);

    /**
     * Setter for the direction field.
     * @param newDir the new direction
     * @throws NullPointerException if newDir is null
     */
    protected void setDirection(final Point2d newDir) {
        this.direction = Objects.requireNonNull(newDir);
    }

    /**
     * Getter for the direction field.
     * @return the ai's direction
     */
    protected Point2d getDirection() {
        return this.direction;
    }

    /**
     * calculates the next position.
     * @param initialPosition the initial position from where the next position is calculated
     * @param deltaTime the deltaTime
     * @param velocity the velocity amount
     * @return the next position
     */
    protected Point2d nextPos(final Point2d initialPosition, final long deltaTime, final double velocity) {
        return this.direction
            .mul(velocity)
            .mul(deltaTime)
            .add(initialPosition);
    }

    /**
     * Checks if the passed parameters are valid, if not an exception will be thrown.
     * @param pos the position
     * @param deltaTime the deltaTime
     * @throws NullPointerException if pos is null
     * @throws IllegalArgumentException if deltaTime is negative
     */
    protected void checkParameters(final Point2d pos, final long deltaTime) {
        Objects.requireNonNull(pos);
        if (deltaTime < 0) {
            throw new IllegalArgumentException("deltaTime can't be negative");
        }
    }

}
