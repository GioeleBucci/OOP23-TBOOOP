package tbooop.commons;

import tbooop.commons.api.Health;

/**
 * Health class, it contains some essential features.
 */
public class HealthImpl implements Health {

    private int maxHealth;
    private int currentHealth;

    /**
     * Creates a new instance of Health, the initial health value
     * equals maxHealth.
     * 
     * @param maxHealth the maximum health value.
     * @throws IllegalArgumentException if the input parameter is
     * a negative number.
     */
    public HealthImpl(final int maxHealth) {
        if (maxHealth < 0) {
            throw new IllegalArgumentException();
        }
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
    public void decreaseHealth(final int amount) {
        this.setCurrentHealth(this.currentHealth - amount);
    }

    /** {@inheritDoc} */
    @Override
    public void increaseHealth(final int amount) {
        this.setCurrentHealth(this.currentHealth + amount);
    }

    /** {@inheritDoc} */
    @Override
    public void increaseMaxHealth(final int amount) {
        this.setMaxHealth(this.maxHealth + amount);
    }

    /**
     * Change the current health value.
     * 
     * @param value the value set as current health.
     * @throws IllegalArgumentException if value is a negative number.
     */
    protected void setCurrentHealth(final int value) {
        this.currentHealth = value;
    }

    /**
     * Change the current health value.
     * 
     * @param value the value to set as max Health.
     * @throws IllegalArgumentException if value is a negative number.
     */
    protected void setMaxHealth(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException();
        }
        this.maxHealth = value;
    }
}
