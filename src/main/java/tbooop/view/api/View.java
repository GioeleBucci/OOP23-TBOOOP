package tbooop.view.api;

import tbooop.model.core.api.GameObject;
import tbooop.model.core.api.GameObjectUnmodifiable;
import tbooop.model.dungeon.rooms.api.RoomUnmodifiable;
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
     * Adds a player to the view.
     * 
     * @param player the player to add
     */
    void addPlayer(UnmodifiablePlayer player);

    /**
     * Refresh the room the player is in.
     * 
     * @param room the new room
     */
    void refreshRoom(RoomUnmodifiable room);

    /**
     * Changes the floor the player is in.
     */
    void changeFloor();

    /**
     * Removes a GameObject from the view.
     * 
     * @param gameObject the GameObject to remove
     */
    void removeGameObject(GameObject gameObject);

    /**
     * Shows the death screen.
     */
    void showDeathScreen();

}
