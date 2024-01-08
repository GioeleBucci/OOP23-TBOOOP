package tbooop.model.core.api.movable;

import tbooop.commons.Vector2d;

import tbooop.commons.Point2d;
import tbooop.model.core.api.GameObject;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.impl.GameObjectAbs;

/**
 * A Entity is an abstraction of anything that is Damageable and Movable in the
 * game.
 * Every object can take damage and can move must extends this abstact class.
 */

public abstract class Entity extends GameObjectAbs implements Damageable {

    private int maxHealth; // NOPMD suppressed as it is a false positive
    private int currentHealth;
    private Vector2d velocity; // NOPMD suppressed as it is a false positive

    /**
     * Create a new istance of a Entity.
     * 
     * @param position      starting position (as a {@link javafx.geometry.Point2D
     *                      Point2D})
     * @param maxHealth     it is the maximum starting health
     * @param currentHealth it is the current health
     * @param velocity      it is the Entity velocity
     * @throws NullPointerException if any parameter passed is null
     */

    protected Entity(final Point2d position, final int maxHealth, final int currentHealth, final Vector2d velocity) {
        // TODO ho messo un tag a caso e un uno come raggio del circle collider
        super(position, 1, GameTag.ENEMY);
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
        this.velocity = velocity;
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
     * 
     * @return immutableVelocity
     */
    protected Vector2d getVelocity() {
        final Vector2d immutableVelocity = this.velocity;
        return immutableVelocity; // NOPMD suppressed as it is a false positive
    }

    @Override
    public void updateState(final long deltaTime) {
        // TODO Auto-generated method stub

    }

}
