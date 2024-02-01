package tbooop.view.api;

import tbooop.model.core.api.GameObject;

/**
 * Interface for the main game view.
 */
public interface View extends ViewElements {

    /**
     * Adds a game object to the view.
     * 
     * @param gameObject the game object to add
     */
    void addGameObject(GameObject gameObject);

    /**
     * Updates the view.
     * <br>
     * </br>
     * This method should be called each frame.
     */
    void update();

}
