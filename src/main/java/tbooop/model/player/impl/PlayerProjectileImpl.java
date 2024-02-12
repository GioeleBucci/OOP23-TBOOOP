package tbooop.model.player.impl;

import tbooop.commons.api.Point2d;
import tbooop.commons.api.Vector2d;
import tbooop.model.core.api.movable.Entity;
import tbooop.model.enemy.api.Enemy;
import tbooop.model.player.api.PlayerProjectile;
import tbooop.commons.Point2dImpl;
import tbooop.commons.api.AbstractProjectile;

/**
 * A Player projectile is movable GameObject that travels in a straight line.
 * It's not damageable, however it can damage Enemies.
 * It disappears when colliding either into a map border or an Enemies.
 */
public class PlayerProjectileImpl extends AbstractProjectile implements PlayerProjectile {
    /** Make the projectile spawn near the player's eyes. */
    private static final Point2dImpl Y_OFFSET = new Point2dImpl(0, -13);
    private int damage;
    /**
    * Create a new istance of a Player Projectile.
    * 
    * @param direction it is the direction of the Projectile 
    * @param initialPoint it is the initial point of the Projectile
    * @param velocity      it is the Projectile velocity
    * @throws NullPointerException if any parameter passed is null
    */
    protected PlayerProjectileImpl(
        final Vector2d direction, 
        final Point2d initialPoint, 
        final double velocity) {
        super(direction, initialPoint.add(Y_OFFSET), velocity);
    }

    /** {@inheritDoc} */
    @Override
    public void onEntityCollision(final Entity entity) {
        if (entity instanceof Enemy) {
            entity.takeDamage(damage);
            this.destroy();
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setDamage(final int amount) {
        this.damage = amount;
    }
}
