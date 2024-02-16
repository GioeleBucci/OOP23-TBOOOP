package tbooop.model.player.api.projectile;

import tbooop.commons.api.Projectile;

/**
 * A Player projectile is movable GameObject that travels in a straight line.
 * It's not damageable, however it can damage Enemies.
 * It disappears when colliding either into a map border or an Enemies.
 */
public interface PlayerProjectile extends Projectile {

    /**
    * This method set the amount of damage the Player can do.
    * @param amount the amount of damage to set.
    */
    void setDamage(int amount);
}
