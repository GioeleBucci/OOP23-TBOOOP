package tbooop.model.player.impl;

import tbooop.commons.api.Point2d;
import tbooop.commons.api.Vector2d;
import tbooop.commons.Point2dImpl;
import tbooop.commons.Point2ds;
import tbooop.commons.RoomBounds;
import tbooop.commons.api.Health;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.movable.AbstractEntity;
import tbooop.model.player.api.Player;
import tbooop.model.player.api.PlayerProjectile;
import java.util.Optional;

/**
 * A Player is a game object that can move on a 2D space,
 * a player can interact with an enemy trying to kill him or can collect
 * objects on the map.
*/
public class PlayerImpl extends AbstractEntity implements Player {

    private static final int PLAYER_COLLIDER_RADIUS = 15;
    private static final int PROJECTILE_VELOCITY_INCREMENT = 10;
    private static final long TIME_BETWEEN_SHOTS = 200;
    private int damage;
    private int keys;
    private int coin;
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
     * @throws NullPointerException if any parameter passed is null
     */
    public PlayerImpl(final Point2d position, final Health health, final double velocity) {
        super(position, health, velocity, GameTag.PLAYER, PLAYER_COLLIDER_RADIUS);
        this.damage = 1;
        this.coin = 10;
        this.projectileVelocity = velocity * 2;
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

    private void shootProjectile() {
        this.timeSinceLastShoot = 0;
        final PlayerProjectile shooted = new PlayerProjectileImpl(this.projDir, getPosition(), this.projectileVelocity);
        shooted.setDamage(damage);
        addProjectile(shooted);
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

        setDirection(direction.toP2d().toV2d());

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
        this.canShoot = true;
        this.projDir = direction;
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
    public Optional<Point2ds> getPoint2ds() {
        for (final var point2d : Point2ds.getAll()) {
            if (getDirection().toP2d().equals(point2d.toP2d())) {
                return Optional.of(point2d);
            }
        }

        if (!getDirection().toP2d().equals(Point2dImpl.ZERO)) {
            throw new IllegalArgumentException();
        }
        return Optional.empty();
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasKey() {
        return this.keys > 0;
    }

    /** {@inheritDoc} */
    @Override
    public void useKey() {
        if (hasKey()) {
            this.keys = this.keys - 1;
        }
    }
}
