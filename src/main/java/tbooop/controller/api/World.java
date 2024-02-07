package tbooop.controller.api;

import java.util.Set;
import tbooop.commons.api.Projectile;
import tbooop.model.core.api.GameObject;
import tbooop.model.dungeon.rooms.api.DoorUnmodifiable;
import tbooop.model.player.api.Player;

/**
 * The World class represents the game world and contains information about the
 * game objects, the player and the current floor.
 */
public interface World extends ControllerComponent {

    /**
     * Returns the list of game objects in the world.
     *
     * @return the list of game objects
     */
    Set<GameObject> getGameObjects();

    /**
     * Returns the list of game objects in the world.
     *
     * @return the list of game objects
     */
    Set<Projectile> getProjectiles();

    /**
     * Returns the player in the world.
     *
     * @return the player
     */
    Player getPlayer();

    /**
     * Adds a game object to the world (and the view).
     * 
     * @param gameObject the game object to add
     */
    void addGameObject(GameObject gameObject);

    /** Removes all game objects and projectiles from the world. */
    void clearAll();

    /**
     * Handles player/door collisions.
     * 
     * @param door the door that the player collided with
     */
    void onDoorCollision(DoorUnmodifiable door);

}
