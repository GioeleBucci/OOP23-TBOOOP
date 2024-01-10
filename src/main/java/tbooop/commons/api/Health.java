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
     * Increase the max health by 1.
     */
    void increaseMaxHealth();

    /**
     * Increase the current health by 1.
     * @return
     * Return TRUE if the increase can be done.
     * <p> 
     * Return FALSE if the increase can't be done.
     */
    boolean recovery();

    /**
     * Set the current health equals to the max health.
     */
    void maxRecovery();
}
