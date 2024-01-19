package tbooop.model.player.impl;

import tbooop.commons.Point2d;
import tbooop.commons.Point2ds;
import tbooop.commons.RoomBounds;
import tbooop.commons.api.Health;
import tbooop.model.core.api.GameObject;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.movable.Entity;
import tbooop.model.player.api.Player;

/**
 * A Player is a game object that can move on a 2D space,
 * a player can interact with an enemy trying to kill him or can collect
 * objects on the map.
*/
public class PlayerImpl extends Entity implements Player {

    private int damage;
    private int keys;
    private int coin;
    /**
     * Create a new istance of a Entity.
     * 
     * @param position      starting position (as a {@link javafx.geometry.Point2D
     *                      Point2D})
     * @param health the entity's health
     * @param velocity      it is the Entity velocity
     * @throws NullPointerException if any parameter passed is null
     */
    protected PlayerImpl(final Point2d position, final Health health, final double velocity) {
        super(position, health, velocity, GameTag.PLAYER);
        this.damage = 1;
        this.coin = 10;
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

    /** {@inheritDoc} */
    @Override
    public void maxRecovery() {
        increaseHealth(getMaxHealth() - getHealth());
    }

    /** {@inheritDoc} */
    @Override
    public void increaseDamage(final int amount) {
        this.damage = this.damage + amount;
    }

    /** {@inheritDoc} */
    @Override
    public void recovery() {
        increaseHealth(1);
        checkHealth();
    }

    /** {@inheritDoc} */
    @Override
    public void pickupKeys() {
        this.keys = this.keys + 1;
    }

    /** {@inheritDoc} */
    @Override
    public void move(final Point2ds direction) {
        final Point2d control = getPosition()
        .add(direction.toP2d())
        .mul(getVelocity());

        if (RoomBounds.outOfBounds(control)) {
            this.setPosition(getPosition()
            .add(direction.toP2d())
            .mul(getVelocity()));
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setCoin(final int amount) {
        this.coin = this.coin + amount;
    }

    /** {@inheritDoc} */
    @Override
    public int getCoin() {
        return this.coin;
    }

    private void checkHealth() {
        if (getHealth() > getMaxHealth()) {
            takeDamage(1);
        }
    }
}
