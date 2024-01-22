package tbooop.model.core.api;

import tbooop.commons.Point2d;
import tbooop.commons.api.CircleCollider;

/**
 * Interface for game objects.
 * <p>
 * It represents the methods that everything in the game must have.
 */
public interface GameObject {

    /**
     * Updates the state of this GameObject (like his position), based off the time
     * elapsed since the last frame.
     * 
     * @param deltaTime the time elapsed since the last frame
     */
    void updateState(long deltaTime);

    /**
     * Sets the position of this GameObject, expressed as a Point2D.
     * <p>
     * <i>Note</i>: Moving a GameObject will update it's collider accordingly
     * 
     * @param newPos the new position of this object.
     * @throws UnsupportedOperationException if the object is unmovable
     */
    void setPosition(Point2d newPos);

    /**
     * The position of this GameObject, expressed as a Point2D.
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
     * Destroy this GameObject.
     */
    void destroy();

    /**
     * Returns true if this GameObject is destroyed.
     * 
     * @return true if this GameObject is destroyed.
     */
    boolean isDestroyed();

}
