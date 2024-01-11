package tbooop.model.player.api;

import tbooop.model.core.api.movable.Damageable;

/**
 * A Player is a game object that can move on a 2D space,
 * a player can interact with an enemy trying to kill him or can collect
 * objects on the map.
*/
public interface Player extends Damageable {

    /**
     * Set the current health equals to the max health.
     */
    void maxRecovery();

    /**
     * Increase the current health by 1.
     * @throws IllegalArgumentException if the input parameter is
     * a negative number.
     */
    void recovery();
}
