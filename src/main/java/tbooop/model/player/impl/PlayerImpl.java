package tbooop.model.player.impl;

import tbooop.commons.api.CardinalDirection;
import tbooop.commons.api.Point2d;
import tbooop.commons.api.RoomBounds;
import tbooop.commons.api.Vector2d;
import tbooop.commons.impl.HealthImpl;
import tbooop.commons.impl.Point2dImpl;
import tbooop.model.player.api.AbstractPlayer;
import tbooop.model.player.api.projectile.PlayerProjectile;
import tbooop.view.sound_manager.Sound;
import tbooop.view.sound_manager.SoundManager;

/**
 * A Player is a game object that can move on a 2D space,
 * a player can interact with an enemy trying to kill him or can collect
 * objects on the map.
 */
public class PlayerImpl extends AbstractPlayer {

    private static final int TOLERANCE = 5;
    private static final double PROJECTILE_VELOCITY_INCREMENT = 0.02;
    private static final double PROJECTILE_BASE_VELOCITY = 0.2;
    private static final long TIME_BETWEEN_SHOTS = 350;
    private static final long TIME_BETWEEN_DAMAGE = 800;
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private static final double PLAYER_INITIAL_SPEED = 0.11;
    private double projectileVelocity;
    private long deltaTime;
    private long timeSinceLastShoot;
    private long timeSinceLastDamage;

    private boolean canShoot;
    private Vector2d projDir;

    /**
     * Create a new istance of a Entity.
     *
     * @param position starting position
     * @throws NullPointerException if any parameter passed is null
     */
    public PlayerImpl(final Point2d position) {
        super(position, new HealthImpl(PLAYER_INITIAL_HEALTH), PLAYER_INITIAL_SPEED, new BasePlayerKeyImpl());
        this.projectileVelocity = PROJECTILE_BASE_VELOCITY;
    }

    /** {@inheritDoc} */
    @Override
    public void updateState(final long deltaTime) {
        this.deltaTime = deltaTime;
        removeProjectiles();

        this.timeSinceLastShoot += this.deltaTime;
        this.timeSinceLastDamage += this.deltaTime;

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
        increaseHealth((int) (getMaxHealth() - getHealth()));
    }

    /** {@inheritDoc} */
    @Override
    public void recovery() {
        if (getHealth() < getMaxHealth()) {
            increaseHealth(1);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void move(final CardinalDirection direction) {
        final Point2d nextPosition = getPosition()
                .add(direction.toP2d().mul(getVelocity() * deltaTime));

        setDirection(direction.toP2d().toV2d());

        if (!checkMovement(nextPosition, direction)) {
            this.setPosition(nextPosition);
        }
    }

    private boolean checkMovement(final Point2d nextPosition, final CardinalDirection direction) {
        return RoomBounds.outOfBounds(nextPosition)
                || direction.equals(CardinalDirection.DOWN)
                        && RoomBounds.outOfBounds(
                                nextPosition.add(new Point2dImpl(0, getCollider().getRadius() - TOLERANCE)));
    }

    /** {@inheritDoc} */
    @Override
    public void increaseVelocity(final double amount) {
        setVelocity(getVelocity() + amount);
    }

    /** {@inheritDoc} */
    @Override
    public double getProjectileVelocity() {
        return this.projectileVelocity;
    }

    /** {@inheritDoc} */
    @Override
    public void increaseProjectileVelocity() {
        this.projectileVelocity = this.projectileVelocity + PROJECTILE_VELOCITY_INCREMENT;
    }

    /** {@inheritDoc} */
    @Override
    public void increaseDamage(final float amount) {
        setDamage(getDamage() + amount);
    }

    /** {@inheritDoc} */
    @Override
    public void takeDamage(final float amount) {
        if (this.timeSinceLastDamage > TIME_BETWEEN_DAMAGE) {
            this.timeSinceLastDamage = 0;
            super.takeDamage(amount);
            if (SoundManager.getInstance() != null) {
                SoundManager.getInstance().playSound(Sound.HURT);
            }
        }
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
}
