package tbooop.model.core.api.unmovable.pickupable;

import tbooop.commons.Point2d;
import tbooop.commons.api.CircleCollider;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.unmovable.UnmovableAbs;

/**
 * Abstract class for pickupable items
 * <p>
 * Implements methods from Pickupable
 * interface (equals for every item)
 * and declare abstract methods to
 * be implemented by single different
 * classes.
 */
public abstract class PickupableAbstract extends UnmovableAbs implements Pickupable {

    /**
     * Create a new istance of a Pickup.
     * 
     * @param position       starting position (as a {@link javafx.geometry.Point2D
     *                       Point2D})
     * @param colliderRadius radius of the circle collider (hitbox).
     *                       The center of the collider will be this game object's
     *                       position
     * @param tag            the tag of this game object
     * @throws NullPointerException if any parameter passed is null
     */
    protected PickupableAbstract(final Point2d position, final double colliderRadius, final GameTag tag) {
        super(position, colliderRadius, tag);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isColliding(final CircleCollider player, final CircleCollider item) {
        return item.isColliding(player);
    }
}
