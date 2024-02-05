package tbooop.model.enemy.impl;

import java.util.Objects;

import tbooop.commons.api.Point2d;
import tbooop.commons.RoomBounds;
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

    private final MovementAi ai;

    /**
     * creates a new istance of a basic enemy.
     * 
     * @param position on the 2D map
     * @param health the enemy's health
     * @param velocity determines how fast the enemy moves
     * @param ai the enemy's movement ai
     * @param enemyType the enemy's type
     */
    protected BaseEnemy(
        final Point2d position,
        final Health health,
        final double velocity,
        final MovementAi ai,
        final EnemyType enemyType) {
        super(position, health, velocity, enemyType);
        this.ai = Objects.requireNonNull(ai);
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
        this.move(deltaTime);
    }

    /**
     * Updates the enemy's position.
     * 
     * @param deltaTime the time passed since the previous position update
     * @throws IllegalArgumentException if deltaTime is negative
     */
    protected void move(final long deltaTime) {
        // DA DISCUTERE!
        if (deltaTime < 0) {
            throw new IllegalArgumentException(
                "deltaTime can't be negative.");
        }
        final Point2d newPos = this.ai.newPosition(
            super.getPosition(), deltaTime, super.getVelocity());
        if (!RoomBounds.outOfBounds(newPos)) {
            super.setPosition(newPos);
        }
    }

}
