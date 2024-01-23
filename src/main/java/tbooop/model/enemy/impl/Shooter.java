package tbooop.model.enemy.impl;

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
    private long timeSinceLastShoot;

    /**
     * Creates an instance of a Shooter decoration.
     * 
     * @param concreteEnemy the enemy that gets decorated
     */
    protected Shooter(final Enemy concreteEnemy) {
        super(concreteEnemy);
    }

    /** {@inheritDoc} */
    @Override
    public void updateState(final long deltaTime) {
        super.updateState(deltaTime);
        this.timeSinceLastShoot += deltaTime;
        if (this.timeSinceLastShoot >= TIME_BETWEEN_SHOTS) {
            this.timeSinceLastShoot = 0;
            // new EnemyProjectile
        }
    }

}
