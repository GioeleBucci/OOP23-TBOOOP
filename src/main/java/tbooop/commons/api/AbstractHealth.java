package tbooop.commons.api;

/**
 * Abstract Health class, it contains some essential features.
 */
public abstract class AbstractHealth implements Health {

    private final int maxHealth;
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

    /** {@inheritDoc} */
    @Override
    public void reduceHealth(final int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException();
        }
        this.currentHealth = this.currentHealth - amount;
    }
}
