package tbooop.model.player.impl;

import tbooop.commons.Point2d;
import tbooop.commons.Vector2d;
import tbooop.commons.api.Health;
import tbooop.model.core.api.GameObject;
import tbooop.model.core.api.movable.Entity;
import tbooop.model.player.api.Player;

/**
 * A Player is a game object that can move on a 2D space,
 * a player can interact with an enemy trying to kill him or can collect
 * objects on the map.
*/
public class PlayerImpl extends Entity implements Player {

    protected PlayerImpl(Point2d position, Health health, Vector2d velocity) {
        super(position, health , velocity);
    }

    /** {@inheritDoc} */
    @Override
    public void onCollision(GameObject gameObj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onCollision'");
    }

    /** {@inheritDoc} */
    @Override
    public void maxRecovery() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'maxRecovery'");
    }

    /** {@inheritDoc} */
    @Override
    public void recovery() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recovery'");
    }

    @Override
    public void updateState(long deltaTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateState'");
    }
    
    /* 
    private void checkHealth() {
        if (getCurrenthHealth() > getMaxHealth()) {
            setCurrentHealth(getCurrenthHealth() - 1);
        }
    }
    */
}
