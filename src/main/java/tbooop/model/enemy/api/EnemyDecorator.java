package tbooop.model.enemy.api;

import java.util.Objects;
import java.util.Set;

import tbooop.commons.api.Point2d;
import tbooop.commons.api.Projectile;
import tbooop.commons.api.CircleCollider;
import tbooop.model.core.api.GameTag;
import tbooop.model.player.api.Player;

/**
 * An enemy decorator is an object that stores either a concrete
 * enemy object or another decorator. It's purpose is to eventually
 * add some sort of added behaviour in any method that allows it.
 * All concrete enemy decorators objects are supposed to extend this abstract
 * class.
 */
public abstract class EnemyDecorator implements Enemy {

    private final Enemy concreteEnemy;

    /**
     * Creates an instance of an enemy decorator.
     * 
     * @param concreteEnemy the enemy stored and decorated.
     */
    protected EnemyDecorator(final Enemy concreteEnemy) {
        this.concreteEnemy = Objects.requireNonNull(concreteEnemy);
    }

    /** {@inheritDoc} */
    @Override
    public void takeDamage(final int damage) {
        this.concreteEnemy.takeDamage(damage);
    }

    /** {@inheritDoc} */
    @Override
    public final int getHealth() {
        return this.concreteEnemy.getHealth();
    }

    /** {@inheritDoc} */
    @Override
    public final int getMaxHealth() {
        return this.concreteEnemy.getMaxHealth();
    }

    /** {@inheritDoc} */
    @Override
    public void onPlayerCollision(final Player player) {
        this.concreteEnemy.onPlayerCollision(player);
    }

    /** {@inheritDoc} */
    @Override
    public void updateState(final long deltaTime) {
        this.concreteEnemy.updateState(deltaTime);
    }

    /** {@inheritDoc} */
    @Override
    public final void setPosition(final Point2d newPos) {
        this.concreteEnemy.setPosition(newPos);
    }

    /** {@inheritDoc} */
    @Override
    public final Point2d getPosition() {
        return this.concreteEnemy.getPosition();
    }

    /** {@inheritDoc} */
    @Override
    public final CircleCollider getCollider() {
        return this.concreteEnemy.getCollider();
    }

    /** {@inheritDoc} */
    @Override
    public final GameTag getTag() {
        return this.concreteEnemy.getTag();
    }

    /** {@inheritDoc} */
    @Override
    public final boolean isDestroyed() {
        return this.concreteEnemy.isDestroyed();
    }

    /** {@inheritDoc} */
    @Override
    public final Set<Projectile> getShotProjectiles() {
        return this.concreteEnemy.getShotProjectiles();
    }

    /** {@inheritDoc} */
    @Override
    public final void addProjectile(final Projectile projectile) {
        this.concreteEnemy.addProjectile(projectile);
    }

    /** {@inheritDoc} */
    @Override
    public final EnemyType getEnemyType() {
        return this.concreteEnemy.getEnemyType();
    }

}
