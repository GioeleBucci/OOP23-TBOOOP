package tbooop.model.core.api.movable;

import tbooop.commons.Point2d;
import tbooop.commons.Vector2d;

/**
 * Abstract Enemy class which contains the essential components of an enemy.
 */
public abstract class AbstractEnemy extends Entity implements Enemy {

    /**
     * creates a new istance of an Enemy.
     * 
     * @param position on the 2D map
     * @param maxHealth
     * @param currentHealth
     * @param velocity determines how fast the enemy moves
     */
    protected AbstractEnemy(
        final Point2d position,
        final int maxHealth,
        final int currentHealth,
        final Vector2d velocity) {
        super(position, maxHealth, currentHealth, velocity);
        //TODO Auto-generated constructor stub
    }
}
