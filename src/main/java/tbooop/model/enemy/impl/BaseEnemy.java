package tbooop.model.enemy.impl;

import java.util.Objects;

import tbooop.commons.Point2d;
import tbooop.commons.RoomBounds;
import tbooop.commons.api.Health;
import tbooop.model.core.api.GameObject;
import tbooop.model.core.api.GameTag;
import tbooop.model.enemy.api.AbstractEnemy;
import tbooop.model.enemy.api.ai.MovementAi;
import tbooop.model.player.api.Player;

/**
 * basic enemy class, it can only move and be damaged/killed, but it does not
 * attack the player.
 */
public class BaseEnemy extends AbstractEnemy {

    private final Player player;
    private final MovementAi ai;

    /**
     * creates a new istance of a basic enemy.
     * 
     * @param position on the 2D map
     * @param health the enemy's health
     * @param velocity determines how fast the enemy moves
     * @param player the game's player
     * @param ai the enemy's movement ai
     */
    protected BaseEnemy(
        final Point2d position,
        final Health health,
        final double velocity,
        final Player player,
        final MovementAi ai) {
        super(position, health, velocity);
        this.player = Objects.requireNonNull(player);
        this.ai = Objects.requireNonNull(ai);
    }

    /** {@inheritDoc} */
    @Override
    public void onCollision(final GameObject gameObj) {
        if (gameObj.getTag().equals(GameTag.PLAYER)) {
            this.player.takeDamage(1);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void updateState(final long deltaTime) {
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
