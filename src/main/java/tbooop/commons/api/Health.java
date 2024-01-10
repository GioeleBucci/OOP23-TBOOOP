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
     * Reduces the current health value.
     * 
     * @param amount gets subtracted from the current health value.
     * @throws IllegalArgumentException if amount is a negative number.
     */
    void reduceHealth(int amount);
}
