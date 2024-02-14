package tbooop.model.pickupables.api;

import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.unmovable.UnmovableAbs;

/**
 * Abstract class for general pickupable
 * objects.
 * Contains methods to be shared among
 * all items and pickups.
 */
public abstract class PickupableAbs extends UnmovableAbs implements Pickupable {

    private boolean consumed;

    /**
     *Create a new istance of a general Pickupable.
     * 
     * @param position       starting position (as a Point2d)
     * @param colliderRadius radius of the circle collider (hitbox).
     *                       The center of the collider will be this game object's
     *                       position
     * @param tag            the tag of this game object
     * @throws NullPointerException if any parameter passed is null
     */
    protected PickupableAbs(final Point2d position, final double colliderRadius, final GameTag tag) {
        super(position, colliderRadius, tag);
    }

    /**
     *Check if pickupable object is consumed.
     * 
     * @return true if pickupable object is consumed.
     */
    protected boolean isConsumed() {
        return this.consumed;
    }

    /**
     * Change pickupable status as consumed.
     */
    protected void consume() {
        this.consumed = true;
    }
}
