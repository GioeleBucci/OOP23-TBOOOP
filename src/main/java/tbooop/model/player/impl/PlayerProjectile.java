package tbooop.model.player.impl;

import tbooop.commons.api.Point2d;
import tbooop.commons.api.Vector2d;
import tbooop.commons.api.AbstractProjectile;
import tbooop.model.core.api.GameObject;

/**
 * A Player projectile is movable GameObject that travels in a straight line.
 * It's not damageable, however it can damage Enemies.
 * It disappears when colliding either into a map border or an Enemies.
 */
public class PlayerProjectile extends AbstractProjectile {

    /**
    * Create a new istance of a Player Projectile.
    * 
    * @param direction it is the direction of the Projectile 
    * @param initialPoint it is the initial point of the Projectile
    * @param velocity      it is the Projectile velocity
    * @throws NullPointerException if any parameter passed is null
    */
    protected PlayerProjectile(
        final Vector2d direction, 
        final Point2d initialPoint, 
        final double velocity) {
        super(direction, initialPoint, velocity);
    }

    /** {@inheritDoc} */
    @Override
    public void disappear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'disappear'");
    }

    /** {@inheritDoc} */
    @Override
    public void onCollision(final GameObject gameObj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onCollision'");
    }

    /** {@inheritDoc} */
    @Override
    public void updateState(final long deltaTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateState'");
    }
}
