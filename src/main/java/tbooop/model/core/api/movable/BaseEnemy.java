package tbooop.model.core.api.movable;

import tbooop.commons.Point2d;
import tbooop.commons.Vector2d;
import tbooop.model.core.api.GameObject;

/**
 * basic enemy class, it can only move and be damaged/killed, but it does not
 * attack the player.
 */
public class BaseEnemy extends AbstractEnemy {

    /**
     * Creates a new istance of a basic enemy.
     * 
     * @param position
     * @param maxHealth
     * @param currentHealth
     * @param velocity
     */
    protected BaseEnemy(
        final Point2d position,
        final int maxHealth,
        final int currentHealth,
        final Vector2d velocity) {
        super(position, maxHealth, currentHealth, velocity);
        //TODO Auto-generated constructor stub
    }

    /** {@inheritDoc} */
    @Override
    public void onCollision(final GameObject gameObj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onCollision'");
    }

    /** {@inheritDoc} */
    @Override
    public void die() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'die'");
    }

}
