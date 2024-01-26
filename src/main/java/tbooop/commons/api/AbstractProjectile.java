package tbooop.commons.api;

import tbooop.commons.Point2dImpl;
import tbooop.commons.RoomBounds;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.movable.AbstractMovable;

/**
 * This class contains the essential implementation for all projectiles.
 */
public abstract class AbstractProjectile extends AbstractMovable implements Projectile {

    private static final double PROJECTILE_RADIUS = 0.1;

    /**
     * Creates a new istance of a projectile.
     * 
     * @param direction  the projectile's direction
     * @param initialPoint the starting position
     * @param velocity the projectile's velocity
     */
    protected AbstractProjectile(final Vector2d direction,
    final Point2d initialPoint, final double velocity) {
        super(initialPoint, GameTag.PROJECTILE, velocity, direction, PROJECTILE_RADIUS);
    }

    /** {@inheritDoc} */
    @Override
    public void updateState(final long deltaTime) {
        this.move(deltaTime);
    }

    /**
     * Updates the projectile's position by making it advance
     * on the same straight direction.
     * 
     * @param deltaTime the amount of time passed since the last movement
     */
    protected void move(final long deltaTime) {
        final Point2d nextPosition = getPosition()
            .add(new Point2dImpl(super.getDirection().getX(), super.getDirection().getY())
            .mul(super.getVelocity() * deltaTime));
        if (!RoomBounds.outOfBounds(nextPosition)) {
            this.setPosition(nextPosition);
        } else {
            super.destroy();
        }
    }

}
