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
     * Sets the number of keys in the player's possession.
     * @param keys the number of keys to set.
     */
    void setKeys(int keys);

    /**
    * This method increases the number of keys in the player's possession.
    */
    void pickupKeys();

    /**
     * Returns the number of keys in the player's possession.
     * 
     * @return the number of keys in the player's possession.
     */
    int getKey();
}
