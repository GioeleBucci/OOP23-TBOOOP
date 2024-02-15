package tbooop.model.pickupables.items.api;

/**
 * Interface for a pickup factory, that manages to create
 * different types of pickups.
 * Each method returns a particular configuration for the pickup.
 */
public interface ItemFactory {
    /**
     * Creates and return a glass heart item.
     * Its effect in the game is to full
     * player health to max level.
     * 
     * @return an istance of a glass heart item.
     */
    Item glassHeart();

    /**
     * Creates and return a golden apple item.
     * Its effect in the game is to increase
     * player health of two points.
     * 
     * @return an istance of a golden apple item.
     */
    Item goldenApple();

    /**
     * Creates and return a locked ring item.
     * Its effect in the game is to increase
     * player damage of a fixed value.
     * 
     * @return an istance of a locked ring item.
     */
    Item lockedRing();

    /**
     * Creates and return a spicy sauce item.
     * Its effect in the game is to increase
     * player projectiles velocity of a
     * fixed value.
     * 
     * @return an istance of a spicy sauce item.
     */
    Item spicySauce();

    /**
     * Creates and return a zap item.
     * Its effect in the game is to increase
     * player velocity of a fixed value.
     * 
     * @return an istance of a zap item.
     */
    Item zap();
}
