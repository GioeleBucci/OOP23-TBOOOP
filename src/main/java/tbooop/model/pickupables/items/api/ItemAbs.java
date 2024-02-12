package tbooop.model.pickupables.items.api;

import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.pickupables.PickupableAbs;

/**
 * Abstract class for pickupable items
 * <p>
 * Implements methods from Item
 * interface (equals for every item)
 * and declare abstract methods to
 * be implemented by single different
 * classes.
 */
public abstract class ItemAbs extends PickupableAbs implements Item {

    /**
     * Create a new istance of an Item.
     * 
     * @param position       starting position (as a Point2d)
     * @param colliderRadius radius of the circle collider (hitbox).
     *                       The center of the collider will be this game object's
     *                       position
     * @param tag            the tag of this game object
     * @throws NullPointerException if any parameter passed is null
     */
    protected ItemAbs(final Point2d position, final double colliderRadius, final GameTag tag) {
        super(position, colliderRadius, tag);
    }
}
