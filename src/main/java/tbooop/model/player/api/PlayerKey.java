package tbooop.model.player.api;

/**
 * Represents the key of a player.
 */
public interface PlayerKey {

    /**
     * Checks if the player has a key.
     * 
     * @return true if the player has a key, false otherwise.
     */
    boolean hasKey();

    /**
     * Uses a key.
     */
    void useKey();

    /**
    * This method increases the number of keys in the player's possession.
    */
    void pickupKey();

    /**
     * Returns the number of keys in the player's possession.
     * 
     * @return the number of keys in the player's possession.
     */
    int getKey();
}
