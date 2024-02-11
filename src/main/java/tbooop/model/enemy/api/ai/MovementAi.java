package tbooop.model.enemy.api.ai;

import tbooop.commons.api.Point2d;

/**
 * A movement ai is a class that contains an algorithm which tells an
 * enemy class where it should move on each update.
 */
public interface MovementAi {

    /**
     * Calculates the next position in which an enemy should move to.
     * 
     * @param initialPosition the current position where to move from
     * @param deltaTime the time passed since the previous position update
     * @param velocity the enemy's velocity
     * @return the new position
     * @throws NullPointerException if initialPosition is null
     * @throws IllegalArgument exception if deltaTime is negative
     */
    Point2d newPosition(Point2d initialPosition, long deltaTime, double velocity);

}
