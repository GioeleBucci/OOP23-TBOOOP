package tbooop.model.player.api;

import tbooop.model.core.api.movable.Damageable;
import tbooop.commons.Point2ds;
import tbooop.commons.api.Vector2d;

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

    /**
    * This method increases the amount of damage the Player can do.
    * @param amount it's the amount of damege to increase.
    */
    void increaseDamage(int amount);

    /**
    * This method increases the number of keys in the player's possession.
    */
    void pickupKeys();

    /**
    * This method change the number of coin in the player's  possession.
    * @param amount it's the amount of coin to change.
    */
    void setCoin(int amount);

    /**
     * This method return the amount of coin in the player's possession.
     * @return the amount of coin.
     */
    int getCoin();

    /**
     * This method it's used for moving the player in the four direction.
     * @param direction can be UP,DOWN,LEFT,RIGHT.
     */
    void move(Point2ds direction);

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
     * Increases the projectile's speed by 10.
     */
    void increaseProjectileVelocity();
}
