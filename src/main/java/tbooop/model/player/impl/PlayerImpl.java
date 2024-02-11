package tbooop.model.player.impl;

import tbooop.commons.api.Point2d;
import tbooop.commons.api.Vector2d;
import tbooop.commons.Point2ds;
import tbooop.commons.RoomBounds;
import tbooop.commons.api.Health;
import tbooop.model.player.api.AbstractPlayer;
import tbooop.model.player.api.PlayerKey;
import tbooop.model.player.api.PlayerProjectile;

/**
 * A Player is a game object that can move on a 2D space,
 * a player can interact with an enemy trying to kill him or can collect
 * objects on the map.
*/
public class PlayerImpl extends AbstractPlayer {

    private static final double PROJECTILE_VELOCITY_INCREMENT = 0.005;
    private static final double PROJECTILE_BASE_VELOCITY = 0.1;
    private static final long TIME_BETWEEN_SHOTS = 200;
    private double projectileVelocity;
    private long deltaTime;
    private long timeSinceLastShoot;

    private boolean canShoot;
    private Vector2d projDir;

    /**
     * Create a new istance of a Entity.
     *
     * @param position      starting position
     * @param health        the entity's health
     * @param velocity      it is the Entity velocity
     * @param keys          the player's keys
     * @throws NullPointerException if any parameter passed is null
     */
    public PlayerImpl(final Point2d position, final Health health, final double velocity, final PlayerKey keys) {
        super(position, health, velocity, keys);
        this.projectileVelocity = PROJECTILE_BASE_VELOCITY;
    }

    /** {@inheritDoc} */
    @Override
    public void updateState(final long deltaTime) {
        this.deltaTime = deltaTime;
        removeProjectiles();
        this.timeSinceLastShoot += this.deltaTime;

        if (this.canShoot) {
            this.canShoot = false;
            if (this.timeSinceLastShoot >= TIME_BETWEEN_SHOTS) {
                shootProjectile();
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void maxRecovery() {
        increaseHealth(getMaxHealth() - getHealth());
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
        setKeys(getKey() + 1);
    }

    /** {@inheritDoc} */
    @Override
    public void useKey() {
        if (hasKey()) {
            setKeys(getKey() - 1);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void move(final Point2ds direction) {
        final Point2d nextPosition = getPosition()
        .add(direction.toP2d().mul(getVelocity() * deltaTime));

        setDirection(direction.toP2d().toV2d());

        if (!RoomBounds.outOfBounds(nextPosition)) {
            this.setPosition(nextPosition);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void increaseVelocity(final double amount) {
        setVelocity(getVelocity() + amount);
    }

    /** {@inheritDoc} */
    @Override
    public void increaseProjectileVelocity() {
        this.projectileVelocity = this.projectileVelocity + PROJECTILE_VELOCITY_INCREMENT;
    }

    /** {@inheritDoc} */
    @Override
    public void increaseDamage(final int amount) {
        setDamage(getDamage() + amount);
    }

    /** {@inheritDoc} */
    @Override
    public void shoot(final Vector2d direction) {
        this.canShoot = true;
        this.projDir = direction;
    }

    private void shootProjectile() {
        this.timeSinceLastShoot = 0;
        final PlayerProjectile shooted = new PlayerProjectileImpl(this.projDir, getPosition(), this.projectileVelocity);
        shooted.setDamage(getDamage());
        addProjectile(shooted);
    }

    private void checkHealth() {
        if (getHealth() > getMaxHealth()) {
            takeDamage(1);
        }
    }
}
