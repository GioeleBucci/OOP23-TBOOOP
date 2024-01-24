package tbooop.commons.api;

import tbooop.commons.Point2dImpl;
import tbooop.commons.RoomBounds;
import tbooop.model.core.api.GameObjectAbs;
import tbooop.model.core.api.GameTag;

/**
 * This class contains the essential implementation for all projectiles.
 */
public abstract class AbstractProjectile extends GameObjectAbs implements Projectile {

    private static final double PROJECTILE_RADIUS = 0.1;
    private final Vector2d direction;
    private final double velocity;

    /**
     * Creates an instance of a projectile.
     * 
     * @param direction    the projectile's direction
     * @param initialPoint the starting position
     * @param velocity     the projectile's velocity
     */
    protected AbstractProjectile(
            final Vector2d direction,
            final Point2d initialPoint,
            final double velocity) {
        super(initialPoint, PROJECTILE_RADIUS, GameTag.PROJECTILE);
        this.direction = direction;
        this.velocity = velocity;
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
                .add(new Point2dImpl(direction.getX(), direction.getY()).mul(velocity * deltaTime));
        if (!RoomBounds.outOfBounds(nextPosition)) {
            this.setPosition(nextPosition);
        } else {
            disappear();
        }
    }

}
