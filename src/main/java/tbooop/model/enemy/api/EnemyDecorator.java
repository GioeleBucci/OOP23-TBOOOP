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

    private final Enemy concretEnemy;

    /**
     * Creates an instance of an enemy decorator.
     * 
     * @param concreteEnemy the enemy stored and decorated.
     */
    protected EnemyDecorator(final Enemy concreteEnemy) {
        this.concretEnemy = Objects.requireNonNull(concreteEnemy);
    }

    /** {@inheritDoc} */
    @Override
    public void takeDamage(final int damage) {
        this.concretEnemy.takeDamage(damage);
    }

    /** {@inheritDoc} */
    @Override
    public final int getHealth() {
        return this.concretEnemy.getHealth();
    }

    /** {@inheritDoc} */
    @Override
    public final int getMaxHealth() {
        return this.concretEnemy.getMaxHealth();
    }

    /** {@inheritDoc} */
    @Override
    public void onCollision(final Player player) {
        this.concretEnemy.onCollision(player);
    }

    /** {@inheritDoc} */
    @Override
    public void updateState(final long deltaTime) {
        this.concretEnemy.updateState(deltaTime);
    }

    /** {@inheritDoc} */
    @Override
    public final void setPosition(final Point2d newPos) {
        this.concretEnemy.setPosition(newPos);
    }

    /** {@inheritDoc} */
    @Override
    public final Point2d getPosition() {
        return this.concretEnemy.getPosition();
    }

    /** {@inheritDoc} */
    @Override
    public final CircleCollider getCollider() {
        return this.concretEnemy.getCollider();
    }

    /** {@inheritDoc} */
    @Override
    public final GameTag getTag() {
        return this.concretEnemy.getTag();
    }

    /** {@inheritDoc} */
    @Override
    public final boolean isDestroyed() {
        return this.concretEnemy.isDestroyed();
    }

    /** {@inheritDoc} */
    @Override
    public final Set<Projectile> getShotProjectiles() {
        return this.concretEnemy.getShotProjectiles();
    }

    /** {@inheritDoc} */
    @Override
    public final void addProjectile(final Projectile projectile) {
        this.concretEnemy.addProjectile(projectile);
    }

}
