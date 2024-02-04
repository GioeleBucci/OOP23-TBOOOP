package tbooop.model.enemy.api;

import tbooop.commons.api.Point2d;

import java.util.Objects;

import tbooop.commons.api.Health;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.movable.AbstractEntity;

/**
 * Abstract Enemy class which contains the essential components of an enemy.
 */
public abstract class AbstractEnemy extends AbstractEntity implements Enemy {

    private final EnemyType enemyType;

    /**
     * creates a new istance of an AbstractEnemy.
     * 
     * @param position on the 2D map
     * @param health the enemy's health
     * @param velocity determines how fast the enemy moves
     * @param enemyType the enemy's types
     * @throws NullPointerException if enemyTypes is null
     */
    protected AbstractEnemy(
        final Point2d position,
        final Health health,
        final double velocity,
        final EnemyType enemyType) {
        super(position, health, velocity, GameTag.ENEMY);
        this.enemyType = Objects.requireNonNull(enemyType);
    }

    /** {@inheritDoc} */
    @Override
    public EnemyType getEnemyType() {
        return this.enemyType;
    }

}
