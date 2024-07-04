package tbooop.model.player.api;

import tbooop.commons.api.Direction;
import tbooop.model.core.api.GameObjectUnmodifiable;
import java.util.Optional;

/**
 * Unmodifiable Player.
 */
public interface UnmodifiablePlayer extends GameObjectUnmodifiable {

    /**
     * Get one of four direction (UP,DOWN,LEFT,RIGHT).
     * @return Optional<CardinalDirection> one of four diection or Optional.empty()
     */
    Optional<Direction> getCardinalDirection();

    /**
     * Get the max health.
     * @return int the max health
     */
    float getMaxHealth();

    /**
     * Get the current health.
     * @return int the current health
     */
    float getHealth();

    /**
     * This method return the amount of coin in the player's possession.
     * @return the amount of coin.
     */
    int getCoins();

    /**
     * Returns the key associated with this player.
     *
     * @return the key of the player
     */
    int getKey();

    /**
     * Returns the velocity of the player's projectiles.
     * @return the velocity of the player's projectiles.
     */
    double getProjectileVelocity();
}
