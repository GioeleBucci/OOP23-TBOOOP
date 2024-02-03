package tbooop.model.enemy.impl;

import java.util.Objects;

import tbooop.commons.Vector2dImpl;
import tbooop.commons.api.Vector2d;
import tbooop.model.core.api.movable.Damageable;
import tbooop.model.enemy.api.Enemy;
import tbooop.model.enemy.api.EnemyDecorator;

/**
 * a Shooter decorator allows the enemy to shoot a projectile
 * directed against the player position.
 * Each projectile will be shoot after a certain amount of time
 * has passed since the last shooting happened.
 */
public class Shooter extends EnemyDecorator {

    private static final long TIME_BETWEEN_SHOTS = 1000;
    private static final double PROJECTILE_VELOCITY = 0.1;
    private final Damageable player;
    private long timeSinceLastShoot;

    /**
     * Creates an instance of a Shooter decoration.
     * 
     * @param concreteEnemy the enemy that gets decorated
     * @param player the player towards whom the projectiles are shot
     * @throws NullPointerException if any parameter is null
     */
    protected Shooter(final Enemy concreteEnemy, final Damageable player) {
        super(Objects.requireNonNull(concreteEnemy));
        this.player = Objects.requireNonNull(player);
    }

    /** {@inheritDoc} */
    @Override
    public void updateState(final long deltaTime) {
        super.updateState(deltaTime);
        this.timeSinceLastShoot += deltaTime;
        if (this.timeSinceLastShoot >= TIME_BETWEEN_SHOTS) {
            this.timeSinceLastShoot = 0;
            super.addProjectile(new EnemyProjectile(
                this.playerDirection(), super.getPosition(), PROJECTILE_VELOCITY));
        }
    }

    /**
     * Calculates the direction pointing from the current position
     * towards the player's current position.
     * @return the direction towards the player
     */
    private Vector2d playerDirection() {
        return new Vector2dImpl(this.player.getPosition()
        .subtract(super.getPosition()).toV2d())
        .normalize();
    }
}
