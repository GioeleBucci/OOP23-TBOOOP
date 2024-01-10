package tbooop.model.player.impl;

import tbooop.commons.api.AbstractHealth;

/**
 * Represents a Health of an Object.
 */
public class PlayerHealth extends AbstractHealth {

    /**
    * Create a new istance of a Health.
    * 
    * @param maxHealth     it is the maximum starting health
    *
    * @throws NullPointerException if any parameter passed is null
    */
    public PlayerHealth(final int maxHealth) {
        super(maxHealth);
    }

    /**
     * Increase the max health by 1.
     */
    public void increaseMaxHealth() {
        setMaxHealth(getMaxHealth() + 1);
    }

    /**
     * Increase the current health by 1.
     * @throws IllegalArgumentException if the input parameter is
     * a negative number.
     */
    public void recovery() {
        setCurrentHealth(getCurrenthHealth() + 1);
        checkHealth();
    }

    private void checkHealth() {
        if (getCurrenthHealth() > getMaxHealth()) {
            setCurrentHealth(getCurrenthHealth() - 1);
        }
    }

    /**
     * Set the current health equals to the max health.
     */
    public void maxRecovery() {
        setCurrentHealth(getMaxHealth()); 
    }

    /** 
     * Reduce the current Health. 
     * @param amount the value to remove from current health.
     * @throws IllegalArgumentException if the input parameter is
     * a negative number.
     */
    public void reduceHealth(final int amount) {
       setCurrentHealth(getCurrenthHealth() - 1);
    }
}
