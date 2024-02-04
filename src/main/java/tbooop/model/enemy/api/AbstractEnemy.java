package tbooop.model.enemy.api;

import tbooop.commons.api.Point2d;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import tbooop.commons.api.Health;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.movable.AbstractEntity;

/**
 * Abstract Enemy class which contains the essential components of an enemy.
 */
public abstract class AbstractEnemy extends AbstractEntity implements Enemy {

    private final Set<EnemyType> enemyTypes;

    /**
     * creates a new istance of an AbstractEnemy.
     * 
     * @param position on the 2D map
     * @param health the enemy's health
     * @param velocity determines how fast the enemy moves
     * @param enemyTypes the enemy's types
     * @throws NullPointerException if enemyTypes is null
     */
    protected AbstractEnemy(
        final Point2d position,
        final Health health,
        final double velocity,
        final Set<EnemyType> enemyTypes) {
        super(position, health, velocity, GameTag.ENEMY);
        this.enemyTypes = new HashSet<>(Objects.requireNonNull(enemyTypes));
    }

    /** {@inheritDoc} */
    @Override
    public Set<EnemyType> getEnemyTypes() {
        return new HashSet<>(this.enemyTypes);
    }

}
