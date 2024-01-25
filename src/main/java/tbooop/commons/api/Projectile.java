package tbooop.commons.api;

import javax.swing.text.html.parser.Entity;

import tbooop.model.core.api.movable.Movable;

/**
 * A projectile is movable GameObject that travels in a straight line.
 * It's not damageable, however it can damage certain categories of entities.
 * It disappears when colliding either into a
 * map border or an Entity that can be damaged by such projectile.
 */
public interface Projectile extends Movable {

    /**
    * Removes the projectile from the game.
    */ 
    void disappear();

    /**
     * Manages the collisions of the projectile.
     * @param entity should be an Enemy or Player
     */
    void projectileCollision(Entity entity);
}
