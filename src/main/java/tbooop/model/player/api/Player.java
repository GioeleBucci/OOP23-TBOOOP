package tbooop.model.player.api;

import tbooop.model.core.api.movable.Entity;
import tbooop.commons.api.CardinalDirection;
import tbooop.commons.api.Vector2d;

/**
 * A Player is a game object that can move on a 2D space,
 * a player can interact with an enemy trying to kill him or can collect
 * objects on the map.
*/
public interface Player extends Entity, UnmodifiablePlayer {

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

    /**
    * This method increases the amount of damage the Player can do.
    * @param amount it's the amount of damege to increase.
    */
    void increaseDamage(int amount);

    /**
     * This method it's used for moving the player in the four direction.
     * @param direction can be UP,DOWN,LEFT,RIGHT.
     */
    void move(CardinalDirection direction);

    /**
     * This method it's used for shooting in the four direction.
     * @param direction can be UP,DOWN,LEFT,RIGHT.
     */
    void shoot(Vector2d direction);

    /**
     * Increases the player's speed by a certain amount.
     * @param amount the amount to increase;
     */
    void increaseVelocity(double amount);

    /**
     * Increases the velocity of the player's projectiles.
     */
    void increaseProjectileVelocity();

    /**
     * Increases the player's max health by a certain amount.
     * @param amount the amount to increase;
     */
    void increaseMaxHealth(int amount);

    /**
     * Adds the specified number of coins to the collection.
     *
     * @param coins the number of coins to add
     */
    void addCoins(int coins);

    /**
     * Consumes the specified number of coins from the collection.
     *
     * @param coins the number of coins to consume
     */
    void consumeCoins(int coins);

    /**
     * Indicates whether the collection has a key.
     * 
     * @return true if the collection has a key, otherwise false
    */
    boolean hasKey();

    /**
     * Decrement the number of key to the collection by 1.
     */
    void useKey();

    /**
     * Increment the number of key to the collection by 1.
     */
    void pickupKey();
}
