package tbooop.model.enemy.impl;

import tbooop.commons.Vector2dImpl;
import tbooop.model.enemy.api.Enemy;
import tbooop.model.enemy.api.EnemyDecorator;

import java.util.Objects;

/**
 * An Explosive decorator makes the enemy "explode" by shooting projectiles
 * in every direction after it dies.
 */
public class Explosive extends EnemyDecorator {

    private static final double PROJECTILE_VELOCITY = 0.1;
    private boolean exploded;

    /**
     * Creates an instance of an Explosive decorator.
     * @param concreteEnemy the enemy that gets decorated
     * @throws NullPointerException if concreteEnemy is null
     */
    protected Explosive(final Enemy concreteEnemy) {
        super(Objects.requireNonNull(concreteEnemy));
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
        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                super.addProjectile(new EnemyProjectile(
                    new Vector2dImpl(x, y), super.getPosition(), PROJECTILE_VELOCITY));
            }
        }
    }

}
