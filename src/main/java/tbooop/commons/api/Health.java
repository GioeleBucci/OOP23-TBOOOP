package tbooop.commons.api;

/**
 * Represents a Health of an Object.
 */
public interface Health {
    /**
     * Getter for the curruent health. 
     * @return current health.
     */
    float getCurrenthHealth();

    /**
     * Getter for the max health. 
     * @return max health.
     */
    float getMaxHealth();

    /**
     * Decreases the current healt value.
     * @param amount to subtract from the current health.
     */
    void decreaseHealth(float amount);

    /**
     * Increases the current healt value.
     * @param amount to add to the current health.
     */
    void increaseHealth(float amount);

    /**
     * Increases the current healt value.
     * @param amount to add to the max health.
     */
    void increaseMaxHealth(float amount);
}
