package tbooop.controller.api;

import java.util.Set;
import tbooop.commons.api.Projectile;
import tbooop.model.core.api.GameObject;
import tbooop.model.core.api.movable.Entity;
import tbooop.model.dungeon.doors.api.DoorUnmodifiable;
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
     * Returns the list of projectiles in the world.
     *
     * @return the list of projectiles
     */
    Set<Projectile> getProjectiles();

    /**
     * Returns the player in the world.
     *
     * @return the player
     */
    Player getPlayer();

    /**
     * Collects projectiles for the given entity.
     *
     * @param entity the entity to collect projectiles for
     */
    void collectProjectiles(Entity entity);

    /**
     * Adds a game object to the world.
     *
     * @param gameObject the game object to add
     */
    void addGameObject(GameObject gameObject);

    /**
     * Removes all game objects and projectiles from the world.
     */
    void clearAll();

    /**
     * Handles player/door collisions.
     *
     * @param door the door that the player collided with
     */
    void onDoorCollision(DoorUnmodifiable door);

    /**
     * Changes the floor.
     */
    void changeFloor();

}
