package tbooop.model.pickupables.impl;

import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.pickupables.api.PickupableAbstract;
import tbooop.model.player.api.Player;

/**
 * Class rapresenting a static object
 * that will be used to contain and show
 * items price in the shop room.
 */
public class ShopCoinLabel extends PickupableAbstract {
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
    protected ShopCoinLabel(final Point2d position, final double colliderRadius, final GameTag tag) {
        super(position, colliderRadius, tag);
    }

    /** {@inheritDoc} */
    @Override
    public void onPickup(final Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onPickup'");
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
}
