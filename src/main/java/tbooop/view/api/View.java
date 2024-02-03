package tbooop.view.api;

import tbooop.model.core.api.GameObjectUnmodifiable;
import tbooop.model.player.api.UnmodifiablePlayer;

/**
 * Interface for the main game view.
 */
public interface View extends ViewElements {

    /**
     * Adds a game object to the view.
     * 
     * @param gameObject the game object to add
     */
    void addGameObject(GameObjectUnmodifiable gameObject);

    /**
     * Updates the view.
     * <br>
     * </br>
     * This method should be called each frame.
     */
    void update();

    /**
     * it's used to add player in the view.
     * @param player the player to add in the game.
     */
    void addPlayer(UnmodifiablePlayer player);
}
