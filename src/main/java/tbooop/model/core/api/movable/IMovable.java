package tbooop.model.core.api.movable;

import tbooop.model.core.api.IGameObject;
import tbooop.model.core.impl.GameObject;

/**
 * Interface for movable game object.
 * <p>
 * It represents the methods that everything can move in the game must have.
 */

public interface IMovable extends IGameObject {

    /**
     * This method it's used to update the status of a GameObject.
     * <p>
     * 
     * @param deltaTime is the time needed for each update
     */
    void update(long deltaTime);

    /**
     * It is used when a GameObject collides with another GameObject and trigger an
     * event.
     * <p>
     * 
     * @param gameObj it is a GameObject
     */
    void onCollision(GameObject gameObj);
}
