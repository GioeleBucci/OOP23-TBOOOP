package tbooop.model.core.api.movable;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import javafx.geometry.Point2D;
import tbooop.model.core.impl.GameObject;

/**
 * A Entity is an abstraction of anything that is Damageable and Movable in the game.
 * Every object can take damage and can move must extends this abstact class.
 */

public abstract class Entity extends GameObject implements IDamageable {

    private int maxHealth;      // NOPMD suppressed as it is a false positive
    private int currentHealth;
    private Vector2D velocity;  // NOPMD suppressed as it is a false positive

    /**
     * Create a new istance of a Entity.
     * 
     * @param position starting position (as a {@link javafx.geometry.Point2D
     *                 Point2D})
     * @param maxHealth it is the maximum starting health
     * @param currentHealth it is the current health
     * @param velocity it is the Entity velocity 
     * @throws NullPointerException if any parameter passed is null
     */

    protected Entity(final Point2D position, final int maxHealth, final int currentHealth, final Vector2D velocity) {
        super(position);
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
        this.velocity = velocity;
    }

    /** {@inheritDoc} */
    @Override
    public void update(final long deltaTime) {
        //TO DO to implements.
    }

    /** {@inheritDoc} */
    @Override
    public abstract void onCollision(GameObject gameObj);

    /** {@inheritDoc} */
    @Override
    public void takeDamage(final int damage) {
        this.currentHealth = this.currentHealth - damage;
    }

    /** {@inheritDoc} */
    @Override
    public abstract void die();

    /** {@inheritDoc} */
    @Override
    public int getHealth() {
        return this.currentHealth;
    }

    /** {@inheritDoc} */
    @Override
    public int getMaxHealth() {
        return this.maxHealth;
    }

    /**
     * This method it is used to get the velocity with a safe method.
     * @return immutableVelocity
     */
    protected Vector2D getVelocity() {
        final Vector2D immutableVelocity = this.velocity;
        return immutableVelocity; // NOPMD suppressed as it is a false positive
    }
}
