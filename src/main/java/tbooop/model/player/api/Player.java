package tbooop.model.player.api;

import tbooop.model.core.api.movable.Entity;
import tbooop.model.player.api.coin.PlayerCoin;
import tbooop.model.player.api.key.PlayerKey;
import tbooop.commons.api.CardinalDirection;
import tbooop.commons.api.Vector2d;

/**
 * A Player is a game object that can move on a 2D space,
 * a player can interact with an enemy trying to kill him or can collect
 * objects on the map.
*/
public interface Player extends Entity, UnmodifiablePlayer, PlayerKey, PlayerCoin {

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
     * Returns the velocity of the player's projectiles.
     * @return the velocity of the player's projectiles.
     */
    double getProjectileVelocity();

    /**
     * Increases the player's max health by a certain amount.
     * @param amount the amount to increase;
     */
    void increaseMaxHealth(int amount);
}
