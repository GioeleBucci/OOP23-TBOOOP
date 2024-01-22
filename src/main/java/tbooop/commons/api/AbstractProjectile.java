package tbooop.commons.api;

import tbooop.commons.Point2d;
import tbooop.commons.RoomBounds;
import tbooop.commons.Vector2d;

/**
 * Represents a Projectile.
 */
public abstract class AbstractProjectile implements Projectile{

    private Vector2d direction;
    private final double velocity;
    /**
     * Create a new istance of a Entity.
     * 
     * @param velocity      it is the Entity velocity
     * @throws NullPointerException if any parameter passed is null
     */
    public AbstractProjectile(
        final Vector2d direction, 
        final Point2d initialPoint, 
        final double velocity) {

        this.direction = direction;
        setPosition(initialPoint);
        this.velocity = velocity;
    }

    public void move(final long deltaTime) {
        
        final Point2d nextPosition = getPosition().add(new Point2d(direction.getX(), direction.getY()).mul(velocity * deltaTime));
        
        if (!RoomBounds.outOfBounds(nextPosition)) {
            this.setPosition(nextPosition);
        }
    }
}
