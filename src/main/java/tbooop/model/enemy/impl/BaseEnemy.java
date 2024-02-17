package tbooop.model.enemy.impl;

import java.util.Objects;

import tbooop.commons.api.Point2d;
import tbooop.commons.api.Health;
import tbooop.model.enemy.api.AbstractEnemy;
import tbooop.model.enemy.api.EnemyType;
import tbooop.model.enemy.api.ai.MovementAi;
import tbooop.model.player.api.Player;

/**
 * basic enemy class, it can only move and be damaged/killed, but it does not
 * attack the player.
 */
public class BaseEnemy extends AbstractEnemy {

    /**
     * creates a new istance of a basic enemy.
     * 
     * @param position on the 2D map
     * @param health the enemy's health
     * @param velocity determines how fast the enemy moves
     * @param ai the enemy's movement ai
     * @param enemyType the enemy's type
     * @param colliderRadius the enemy's collider radius
     * @throws NullPointerException if either ai or enemyType are null
     */
    protected BaseEnemy(
        final Point2d position,
        final Health health,
        final double velocity,
        final MovementAi ai,
        final EnemyType enemyType,
        final double colliderRadius) {
        super(position, health, velocity, ai, enemyType, colliderRadius);
    }

    /** {@inheritDoc}
     * @throws NullPointerException if gameObj is null
     * <p>
     * In BaseEnemy this method by itself will not perform any action,
     * however it can be overridden by, for example, a decorator in order to
     * define a behaviour for it.
    */
    @Override
    public void onPlayerCollision(final Player player) {
        Objects.requireNonNull(player);
    }

    /** {@inheritDoc} */
    @Override
    public void updateState(final long deltaTime) {
        super.removeProjectiles();
        if (super.getHealth() <= 0) {
            super.destroy();
        }
        super.move(deltaTime);
    }

}
