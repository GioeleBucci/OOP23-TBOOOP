package tbooop.commons.api;

/**
 * Represents a Health of an Object.
 */
public interface Health {
    /**
     * Getter for the curruent health. 
     * @return current health.
     */
    int getCurrenthHealth();

    /**
     * Getter for the max health. 
     * @return max health.
     */
    int getMaxHealth();

    /**
     * Decreases the current healt value.
     * @param amount to subtract from the current health.
     */
    void decreaseHealth(int amount);

    /**
     * Increases the current healt value.
     * @param amount to add to the current health.
     */
    void increaseHealth(int amount);

    /**
     * Increases the current healt value.
     * @param amount to add to the max health.
     */
    void increaseMaxHealth(int amount);
}
