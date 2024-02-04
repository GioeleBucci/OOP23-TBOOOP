package tbooop.model.core.api.movable;

import java.util.Objects;

import tbooop.commons.Vector2dImpl;
import tbooop.commons.api.Health;
import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameTag;

/**
 * A damageable entity is a game object that has a health status and can be damaged.
 */
public abstract class AbstractDamageable extends AbstractMovable implements Damageable {

    private final Health health;

    private static final double COLLIDER_RADIUS = 15;

    /**
     * Create a new istance of a DamageableEntity.
     * 
     * @param position      starting position
     * @param health      the entity's health
     * @param velocity it is the Entity velocity
     * @param tag specifies the type of entity
     * @throws NullPointerException if any parameter is null
     */
    protected AbstractDamageable(
    final Point2d position, final Health health, final double velocity,
    final GameTag tag) {
        super(position, tag, velocity, new Vector2dImpl(0.0, 0.0), COLLIDER_RADIUS);
        this.health = Objects.requireNonNull(health);
    }

    /** {@inheritDoc} */
    @Override
    public void takeDamage(final int damage) {
        this.health.decreaseHealth(damage);
    }

    /** {@inheritDoc} */
    @Override
    public int getHealth() {
        return this.health.getCurrenthHealth();
    }

    /** {@inheritDoc} */
    @Override
    public int getMaxHealth() {
        return this.health.getMaxHealth();
    }

    /**
     * Increases the current healt value.
     * @param amount to add to the max health.
     */
    public void increaseMaxHealth(final int amount) {
        this.health.increaseMaxHealth(amount);
        this.health.increaseHealth(amount);
    }

    /**
     * Increases the current healt value.
     * @param amount to add to the current health.
     */
    public void increaseHealth(final int amount) {
        this.health.increaseHealth(amount);
    }

}
