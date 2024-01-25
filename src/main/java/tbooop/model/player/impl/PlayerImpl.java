package tbooop.model.player.impl;

import tbooop.commons.api.Point2d;
import tbooop.commons.api.Vector2d;
import tbooop.commons.Point2ds;
import tbooop.commons.RoomBounds;
import tbooop.commons.api.Health;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.movable.Entity;
import tbooop.model.player.api.Player;

/**
 * A Player is a game object that can move on a 2D space,
 * a player can interact with an enemy trying to kill him or can collect
 * objects on the map.
*/
public class PlayerImpl extends Entity implements Player {

    private static final long TIME_BETWEEN_SHOTS = 500;
    private int damage;
    private int keys;
    private int coin;
    private long deltaTime;
    private long timeSinceLastShoot;
    /**
     * Create a new istance of a Entity.
     * 
     * @param position      starting position
     * @param health        the entity's health
     * @param velocity      it is the Entity velocity
     * @throws NullPointerException if any parameter passed is null
     */
    public PlayerImpl(final Point2d position, final Health health, final double velocity) {
        super(position, health, velocity, GameTag.PLAYER);
        this.damage = 1;
        this.coin = 10;
    }

    /** {@inheritDoc} */
    @Override
    public void updateState(final long deltaTime) {
        this.deltaTime = deltaTime;
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
        final Point2d nextPosition = getPosition()
        .add(direction.toP2d().mul(getVelocity() * deltaTime));

        if (!RoomBounds.outOfBounds(nextPosition)) {
            this.setPosition(nextPosition);
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

    /** {@inheritDoc} */
    @Override
    public void shoot(final Vector2d direction) {
        this.timeSinceLastShoot += this.deltaTime;
        if (this.timeSinceLastShoot >= TIME_BETWEEN_SHOTS) {
            this.timeSinceLastShoot = 0;
            //PlayerProjectile shooted = new PlayerProjectile(direction, getPosition(), getVelocity()*2);
        }
    }
}
