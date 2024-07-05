package tbooop.model.enemy.api;

import tbooop.commons.api.Point2d;
import tbooop.commons.api.RoomBounds;

import java.util.Objects;

import tbooop.commons.api.Health;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.movable.AbstractEntity;
import tbooop.model.enemy.api.ai.MovementAi;

/**
 * Abstract Enemy class which contains the essential components of an enemy.
 */
public abstract class AbstractEnemy extends AbstractEntity implements Enemy {

    private final EnemyType enemyType;
    private final MovementAi ai;

    /**
     * creates a new istance of an AbstractEnemy.
     * 
     * @param position on the 2D map
     * @param health the enemy's health
     * @param velocity determines how fast the enemy moves
     * @param ai the enemy's movement ai
     * @param enemyType the enemy's type
     * @param colliderRadius the enemy's collider radius
     * @throws NullPointerException if either ai or enemyType are null
     */
    protected AbstractEnemy(
        final Point2d position,
        final Health health,
        final double velocity,
        final MovementAi ai,
        final EnemyType enemyType,
        final double colliderRadius) {
        super(position, health, velocity, GameTag.ENEMY, colliderRadius);
        this.enemyType = Objects.requireNonNull(enemyType);
        this.ai = Objects.requireNonNull(ai);
    }

    /** {@inheritDoc} */
    @Override
    public EnemyType getEnemyType() {
        return this.enemyType;
    }

    /**
     * Updates the enemy's position.
     * 
     * @param deltaTime the time passed since the previous position update
     * @throws IllegalArgumentException if deltaTime is negative
     */
    protected void move(final long deltaTime) {
        if (deltaTime < 0) {
            throw new IllegalArgumentException("deltaTime can't be negative.");
        }
        final Point2d newPos = this.ai.newPosition(
            super.getPosition(), deltaTime, super.getVelocity());
            super.setPosition(newPos);
    }

}
