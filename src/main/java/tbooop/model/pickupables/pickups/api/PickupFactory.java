package tbooop.model.pickupables.pickups.api;

/**
 * Interface for a pickup factory, that manages to create
 * different types of pickups.
 * Each method returns a particular configuration for the pickup.
 */
public interface PickupFactory {
    /**
     * Creates and returns a bill pickup.
     * In the game this pickup will add 10 coins
     * to the player.
     * 
     * @return an istance of a bill pickup.
     */
    Pickup bill();

    /**
     * Creates and returns a coin pickup.
     * In the game this pickup will add 1 coin
     * to the player.
     * 
     * @return an istance of a coin pickup.
     */
    Pickup coin();

    /**
     * Creates and returns an heart pickup.
     * In the game this pickup will add 1 heart
     * to the player health.
     * 
     * @return an istance of an heart pickup.
     */
    Pickup heart();

    /**
     * Creates and returns a key pickup.
     * In the game this pickup will add 1 key
     * to the player.
     * 
     * @return an istance of a key pickup.
     */
    Pickup key();
}
