package tbooop.model.enemy.api.ai;

import tbooop.commons.Point2d;

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
     * @return the new position
     */
    Point2d newPosition(Point2d initialPosition, long deltaTime);
}
