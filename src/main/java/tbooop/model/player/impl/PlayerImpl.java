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

    /**
     * Create a new istance of a Entity.
     * 
     * @param position      starting position (as a {@link javafx.geometry.Point2D
     *                      Point2D})
     * @param health the entity's health
     * @param velocity      it is the Entity velocity
     * @throws NullPointerException if any parameter passed is null
     */
    protected PlayerImpl(final Point2d position, final Health health, final Vector2d velocity) {
        super(position, health, velocity);
    }

    /** {@inheritDoc} */
    @Override
    public void onCollision(final GameObject gameObj) {
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

    /** {@inheritDoc} */
    @Override
    public void updateState(final long deltaTime) {
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
