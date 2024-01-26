package tbooop.model.pickupables.items.impl;

import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.pickupables.items.api.ItemsAbs;

/**
 * Class rapresenting a static object
 * that will be used to contain and show
 * items price in the shop room.
 */
public class ItemPriceLabel extends ItemsAbs {
    /**
     * Create a new istance of a Coin Label.
     * 
     * @param position       spawn position (as a Point2d)
     * @param colliderRadius radius of the circle collider (hitbox).
     *                       The center of the collider will be this game object's
     *                       position
     * @param tag            the tag of this game object
     * @throws NullPointerException if any parameter passed is null
     */
    protected ItemPriceLabel(final Point2d position, final double colliderRadius, final GameTag tag) {
        super(position, colliderRadius, tag);
    }

    /** {@inheritDoc} */
    @Override
    public void updateState(final long deltaTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateState'");
    }

    /** {@inheritDoc} */
    @Override
    public void setInShop() {
    }

    /** {@inheritDoc} */
    @Override
    public int getPrice() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPrice'");
    }
}
