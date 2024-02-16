package tbooop.model.player.api.key;

/**
 * The Key interface represents a key in a game.
 * It provides methods to check if the key is available, use the key, pick up the key, and get the key value.
 */
public interface Key {

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

    /**
     * Returns the number of keys in the collection.
     *
     * @return the number of keys
     */
    int getKey();
}
