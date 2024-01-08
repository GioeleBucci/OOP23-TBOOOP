package tbooop.model.core.api.movable;

import tbooop.model.core.api.GameObject;

/**
 * Interface for movable game object.
 * <p>
 * It represents the methods that everything can move in the game must have.
 */

public interface Movable extends GameObject {

    /**
     * It is used when a GameObject collides with another GameObject and trigger an
     * event.
     * <p>
     * 
     * @param gameObj it is a GameObject
     */
    void onCollision(GameObject gameObj);
}
