package tbooop.model.enemy.api;

import tbooop.commons.Point2d;
import tbooop.commons.Vector2d;
import tbooop.commons.api.Health;
import tbooop.model.core.api.movable.Entity;

/**
 * Abstract Enemy class which contains the essential components of an enemy.
 */
public abstract class AbstractEnemy extends Entity implements Enemy {

    /**
     * creates a new istance of an Enemy.
     * 
     * @param position on the 2D map
     * @param health the enemy's health
     * @param velocity determines how fast the enemy moves
     */
    protected AbstractEnemy(
        final Point2d position,
        final Health health,
        final Vector2d velocity) {
        super(position, health, velocity);
        //TODO Auto-generated constructor stub
    }
}
