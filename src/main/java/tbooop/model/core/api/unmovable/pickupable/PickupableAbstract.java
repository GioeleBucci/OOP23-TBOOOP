package tbooop.model.core.api.unmovable.pickupable;

import tbooop.commons.Point2d;
import tbooop.commons.api.CircleCollider;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.unmovable.UnmovableAbs;
import tbooop.model.core.impl.GameObjectImpl;

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

    protected PickupableAbstract(final Point2d position, final double colliderRadius, final GameTag tag) {
        super(position, colliderRadius, tag);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isColliding(final CircleCollider player, final CircleCollider item) {
        return item.isColliding(player);
    }

    /** {@inheritDoc} */
    @Override
    public abstract void onPickup();
}
