package tbooop.model.enemy.impl;

import tbooop.model.enemy.api.Enemy;
import tbooop.model.enemy.api.EnemyDecorator;
import tbooop.model.enemy.attacks.Attack;

import java.util.Objects;

/**
 * An Explosive decorator makes the enemy "explode" by shooting projectiles
 * in every direction after it dies.
 */
public class Explosive extends EnemyDecorator {

    private static final double PROJECTILE_SPEED = 0.1;
    private final int projectileAmount;
    private boolean exploded;

    /**
     * Creates an instance of an Explosive decorator.
     * 
     * @param concreteEnemy    the enemy that gets decorated
     * @param projectileAmount the amount of projectile fired on death
     * @throws NullPointerException     if concreteEnemy is null
     * @throws IllegalArgumentException if projectileAmount is negative
     */
    protected Explosive(final Enemy concreteEnemy, final int projectileAmount) {
        super(Objects.requireNonNull(concreteEnemy));
        if (projectileAmount < 0) {
            throw new IllegalArgumentException("projectile amount can't be negative");
        }
        this.projectileAmount = projectileAmount;
    }

    /** {@inheritDoc} */
    @Override
    public void updateState(final long deltaTime) {
        super.updateState(deltaTime);
        if (super.isDestroyed() && !this.exploded) {
            this.explode();
        }
    }

    private void explode() {
        this.exploded = true;
        Attack.ring(this, PROJECTILE_SPEED, projectileAmount);
    }

}
