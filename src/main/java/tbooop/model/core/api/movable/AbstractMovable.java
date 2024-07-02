package tbooop.model.core.api.movable;

import java.util.Objects;

import tbooop.commons.api.Point2d;
import tbooop.commons.api.Vector2d;
import tbooop.commons.impl.Vector2dImpl;
import tbooop.model.core.api.GameObjectAbs;
import tbooop.model.core.api.GameTag;

/**
 * A movable entity is a game object that can move in a 2D space
 * with a certain direction and velocity.
 */
public abstract class AbstractMovable extends GameObjectAbs implements Movable {

    private Vector2d direction;
    private double velocity;

    /**
     * Creates a new istance of a movable entity.
     * 
     * @param position the starting position
     * @param tag the type of entity
     * @param velocity the entity's velocity
     * @param direction the direction where the entity moves
     * @param colliderRadius the radius of the collider (hitbox)
     * @throws NullPointerException if any parameter is null
     */
    protected AbstractMovable(final Point2d position, final GameTag tag,
    final double velocity, final Vector2d direction, final double colliderRadius) {
        super(position, colliderRadius, tag);
        this.direction = Objects.requireNonNull(direction);
        this.velocity = Objects.requireNonNull(velocity);
    }

    /**
     * Sets the Entity's direction value.
     * @param newDir the new direction.
     * @throws NullPointerException is the passed parameter is null.
     */
    protected void setDirection(final Vector2d newDir) {
        this.direction = Objects.requireNonNull(newDir);
    }

    /**
     * This method it is used to get the velocity with a safe method.
     * 
     * @return immutableDirection
     */
    public Vector2d getDirection() {
        return new Vector2dImpl(this.direction);
    }

    /**
     * Sets the Entity's velocity value.
     * @param newValue the new value.
     */
    protected void setVelocity(final double newValue) {
        this.velocity = newValue;
    }

    /**
     * Getter for the Entity's velocity.
     * @return the velocity value.
     */
    public double getVelocity() {
        return this.velocity;
    }

}
