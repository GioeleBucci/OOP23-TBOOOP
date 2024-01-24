package tbooop.model.pickupables.pickups.api;

import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.unmovable.UnmovableAbs;

/**
 * Abstract class for pickups
 * <p>
 * Implements methods from Pickups
 * interface (equals for every Pickup)
 * and declare abstract methods to
 * be implemented by single different
 * classes.
 */
public abstract class PickupsAbs extends UnmovableAbs implements Pickups {

    /**
     * Create a new istance of a Pickup.
     * 
     * @param position       starting position (as a Point2d)
     * @param colliderRadius radius of the circle collider (hitbox).
     *                       The center of the collider will be this game object's
     *                       position
     * @param tag            the tag of this game object
     * @throws NullPointerException if any parameter passed is null
     */
    protected PickupsAbs(final Point2d position, final double colliderRadius, final GameTag tag) {
        super(position, colliderRadius, tag);
    }
}
