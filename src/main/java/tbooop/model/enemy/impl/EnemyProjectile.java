package tbooop.model.enemy.impl;

import tbooop.commons.api.Point2d;
import tbooop.commons.api.Vector2d;

import java.util.Objects;

import tbooop.commons.api.AbstractProjectile;
import tbooop.model.core.api.movable.Entity;
import tbooop.model.player.api.Player;

/**
 * An enemy projectile is generated by enemy objects and will damage the player
 * if a physical contact occurs.
 */
public class EnemyProjectile extends AbstractProjectile {

    private static final int PROJECTILE_DAMAGE = 1;
    /**
     * Creates an instance of an enemy projectile.
     * 
     * @param direction the projectile's direction
     * @param initialPoint the starting position
     * @param velocity the projectile's velocity
     */
    protected EnemyProjectile(final Vector2d direction,
    final Point2d initialPoint, final double velocity) {
        super(direction, initialPoint, velocity);
    }

    /** {@inheritDoc} */
    @Override
    public void onPlayerCollision(final Player player) {
        player.takeDamage(PROJECTILE_DAMAGE);
        super.destroy();
    }

    /** {@inheritDoc} */
    @Override
    public void onEntityCollision(final Entity entity) {
        if (Objects.requireNonNull(entity) instanceof Player) {
            entity.takeDamage(PROJECTILE_DAMAGE);
            super.destroy();
        }
    }

}
