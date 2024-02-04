package tbooop.model.core.api;

import tbooop.commons.api.Point2d;
import tbooop.model.player.api.Player;

/**
 * Interface for game objects.
 * <p>
 * It represents the methods that everything in the game must have.
 */
public interface GameObject extends GameObjectUnmodifiable {

    /**
     * Updates the state of this GameObject (like his position), based off the time
     * elapsed since the last frame.
     * 
     * @param deltaTime the time elapsed since the last frame
     */
    void updateState(long deltaTime);

    /**
     * Sets the position of this GameObject, expressed as a Point2d.
     * <p>
     * <i>Note</i>: Moving a GameObject will update it's collider accordingly
     * 
     * @param newPos the new position of this object.
     * @throws UnsupportedOperationException if the object is unmovable
     */
    void setPosition(Point2d newPos);

    /**
     * Gets called whenever GameObject object collides with the player.
     * <p>
     * 
     * @param player the player
     */
    void onPlayerCollision(Player player);

}
