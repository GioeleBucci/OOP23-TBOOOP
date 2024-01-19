package tbooop.model.enemy.api;

import tbooop.commons.Point2d;
import tbooop.commons.RoomBounds;
import tbooop.commons.api.Health;
import tbooop.model.core.api.GameObject;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.movable.Entity;
import tbooop.model.enemy.api.ai.MovementAi;
import tbooop.model.player.api.Player;

import java.util.Objects;

/**
 * Abstract Enemy class which contains the essential components of an enemy.
 */
public abstract class AbstractEnemy extends Entity implements Enemy {

    private final Player player;
    private final MovementAi ai;

    /**
     * creates a new istance of an Enemy.
     * 
     * @param position on the 2D map
     * @param health the enemy's health
     * @param velocity determines how fast the enemy moves
     * @param player the player of the game
     * @param ai the enemy's movement ai
     */
    protected AbstractEnemy(
        final Point2d position,
        final Health health,
        final double velocity,
        final Player player,
        final MovementAi ai) {
        super(position, health, velocity, GameTag.ENEMY);
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
            super.getPosition(), deltaTime);
        if (!RoomBounds.outOfBounds(newPos)) {
            super.setPosition(newPos);
        }
    }

}
