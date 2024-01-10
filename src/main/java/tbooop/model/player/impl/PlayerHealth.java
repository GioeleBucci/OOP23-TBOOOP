package tbooop.model.player.impl;

import tbooop.commons.api.Health;

/**
 * Represents a Health of an Object.
 */
public class PlayerHealth implements Health {

    private int maxHealth;
    private int currentHealth;

    /**
    * Create a new istance of a Health.
    * 
    * @param maxHealth     it is the maximum starting health
    *
    * @throws NullPointerException if any parameter passed is null
    */
    public PlayerHealth(final int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    /** {@inheritDoc} */
    @Override
    public int getCurrenthHealth() {
        return this.currentHealth;
    }

    /** {@inheritDoc} */
    @Override
    public int getMaxHealth() {
       return this.maxHealth;
    }

    /**
     * Increase the max health by 1.
     */
    public void increaseMaxHealth() {
        this.maxHealth = this.maxHealth + 1;
    }

    /**
     * Increase the current health by 1.
     * @return
     * Return TRUE if the increase can be done.
     * <p> 
     * Return FALSE if the increase can't be done.
     */
    public boolean recovery() {
        if (this.currentHealth < this.maxHealth) {
            this.currentHealth = this.currentHealth + 1;
            return true;
        }
        return false;
    }

    /**
     * Set the current health equals to the max health.
     */
    public void maxRecovery() {
        this.currentHealth = this.maxHealth;
    }
}
