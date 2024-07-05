package tbooop.model.core.api;

import tbooop.commons.api.Point2d;
import tbooop.commons.api.RoomBounds;
import tbooop.commons.impl.CircleColliderImpl;
import tbooop.model.player.api.Player;
import tbooop.commons.api.CircleCollider;

import java.util.Objects;

/**
 * A GameObject is an abstraction of anything that is present in the game.
 * Every class must estend it, directly or not.
 */
public abstract class GameObjectAbs implements GameObject {

    private Point2d position;
    private CircleCollider collider;
    private final double colliderRadius;
    private final GameTag tag;
    private boolean isDestroyed;

    /**
     * Create a new istance of a GameObject.
     * 
     * @param position       starting position
     * @param colliderRadius radius of the circle collider (hitbox).
     *                       The center of the collider will be this game object's
     *                       position
     * @param tag            the tag of this game object
     * @throws NullPointerException if any parameter passed is null
     */
    protected GameObjectAbs(final Point2d position, final double colliderRadius, final GameTag tag) {
        this.position = Objects.requireNonNull(position);
        this.colliderRadius = colliderRadius;
        this.collider = new CircleColliderImpl(position, colliderRadius);
        this.tag = Objects.requireNonNull(tag);
    }

    /** {@inheritDoc} */
    @Override
    public Point2d getPosition() {
        return this.position;
    }

    /** {@inheritDoc} */
    @Override
    public void setPosition(final Point2d newPos) {
        if (RoomBounds.outOfBounds(newPos)) {
            return;
        }
        this.position = Objects.requireNonNull(newPos);
        this.collider = new CircleColliderImpl(newPos, colliderRadius);
    }

    /** {@inheritDoc} */
    @Override
    public CircleCollider getCollider() {
        return this.collider;
    }

    /** {@inheritDoc} */
    @Override
    public GameTag getTag() {
        return this.tag;
    }

    /**
     * Destroy this GameObject.
     */
    protected void destroy() {
        this.isDestroyed = true;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isDestroyed() {
        return this.isDestroyed;
    }

    /** {@inheritDoc} */
    @Override
    public void onPlayerCollision(final Player player) {
    }

    /** {@inheritDoc} */
    @Override
    public void updateState(final long deltaTime) {
    }
}
