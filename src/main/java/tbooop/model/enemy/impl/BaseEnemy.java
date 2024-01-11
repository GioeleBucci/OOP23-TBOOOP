package tbooop.model.enemy.impl;

import tbooop.commons.Point2d;
import tbooop.commons.Vector2d;
import tbooop.commons.api.Health;
import tbooop.model.core.api.GameObject;
import tbooop.model.enemy.api.AbstractEnemy;

/**
 * basic enemy class, it can only move and be damaged/killed, but it does not
 * attack the player.
 */
public class BaseEnemy extends AbstractEnemy {

    /**
     * Creates a new istance of a basic enemy.
     * 
     * @param position
     * @param health the enemy's health
     * @param velocity
     */
    protected BaseEnemy(
        final Point2d position,
        final Health health,
        final double velocity) {
        super(position, health, velocity);
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
    public void updateState(final long deltaTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateState'");
    }

}
