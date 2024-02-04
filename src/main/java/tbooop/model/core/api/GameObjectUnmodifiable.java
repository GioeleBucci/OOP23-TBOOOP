package tbooop.model.core.api;

import tbooop.commons.api.CircleCollider;
import tbooop.commons.api.Point2d;

/**
 * Represents an unmodifiable GameObject.
 */
public interface GameObjectUnmodifiable {
    /**
     * The position of this GameObject, expressed as a Point2d.
     * 
     * @return the position of this object.
     */
    Point2d getPosition();

    /**
     * The collider (or hitbox) of this GameObject.
     * 
     * @return the collider of this object.
     */
    CircleCollider getCollider();

    /**
     * The tag of this GameObject.
     * 
     * @return the tag of this object.
     */
    GameTag getTag();

    /**
     * Returns true if this GameObject is destroyed.
     * 
     * @return true if this GameObject is destroyed.
     */
    boolean isDestroyed();

}
