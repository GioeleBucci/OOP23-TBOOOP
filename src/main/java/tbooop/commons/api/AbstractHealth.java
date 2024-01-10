package tbooop.commons.api;

/**
 * Abstract Health class, it contains some essential features.
 */
public abstract class AbstractHealth implements Health {

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
    protected AbstractHealth(final int maxHealth) {
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

    /**
     * Change the current health value.
     * 
     * @param value the value set as current health.
     * @throws IllegalArgumentException if value is a negative number.
     */
    protected void setCurrentHealth(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException();
        }
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
