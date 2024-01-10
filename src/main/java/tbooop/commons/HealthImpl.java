package tbooop.commons;

import tbooop.commons.api.Health;

/**
 * Represents a Health of an Object.
 */
public class HealthImpl implements Health {

    private int maxHealth;
    private int currentHealth;

    /**
    * Create a new istance of a Health.
    * 
    * @param maxHealth     it is the maximum starting health
    *
    * @throws NullPointerException if any parameter passed is null
    */
    public HealthImpl(final int maxHealth) {
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

    /** {@inheritDoc} */
    @Override
    public void increaseMaxHealth() {
        this.maxHealth = this.maxHealth + 1;
    }

    /** {@inheritDoc} */
    @Override
    public boolean recovery() {
        if (this.currentHealth < this.maxHealth) {
            this.currentHealth = this.currentHealth + 1;
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void maxRecovery() {
        this.currentHealth = this.maxHealth;
    }
}
