package tbooop.model.player.impl;

import tbooop.commons.api.Point2d;
import tbooop.commons.api.Vector2d;
import tbooop.commons.HealthImpl;
import tbooop.commons.Point2dImpl;
import tbooop.commons.Point2ds;
import tbooop.commons.RoomBounds;
import tbooop.model.player.api.AbstractPlayer;
import tbooop.model.player.api.PlayerProjectile;

/**
 * A Player is a game object that can move on a 2D space,
 * a player can interact with an enemy trying to kill him or can collect
 * objects on the map.
*/
public class PlayerImpl extends AbstractPlayer {

    private static final int TOLERANCE = 5;
    private static final double PROJECTILE_VELOCITY_INCREMENT = 0.005;
    private static final double PROJECTILE_BASE_VELOCITY = 0.11;
    private static final long TIME_BETWEEN_SHOTS = 300;
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private static final double PLAYER_INITIAL_SPEED = 0.11;
    private double projectileVelocity;
    private long deltaTime;
    private long timeSinceLastShoot;

    private boolean canShoot;
    private Vector2d projDir;

    /**
     * Create a new istance of a Entity.
     *
     * @param position      starting position
     * @throws NullPointerException if any parameter passed is null
     */
    public PlayerImpl(final Point2d position) {
        super(position, new HealthImpl(PLAYER_INITIAL_HEALTH), PLAYER_INITIAL_SPEED, new PlayerKeyImpl());
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
    public void move(final Point2ds direction) {
        final Point2d nextPosition = getPosition()
        .add(direction.toP2d().mul(getVelocity() * deltaTime));

        setDirection(direction.toP2d().toV2d());

        if (!checkMovement(nextPosition, direction)) {
            this.setPosition(nextPosition);
        }
    }

    private boolean checkMovement(Point2d nextPosition, Point2ds direction) {
        return RoomBounds.outOfBounds(nextPosition) ||
            (direction.equals(Point2ds.DOWN) && 
            RoomBounds.outOfBounds(nextPosition.add(new Point2dImpl(0, getCollider().getRadius() - TOLERANCE))));
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
