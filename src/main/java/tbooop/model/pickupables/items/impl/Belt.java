package tbooop.model.pickupables.items.impl;

import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.unmovable.UnmovableName;
import tbooop.model.pickupables.items.api.ItemAbs;
import tbooop.model.pickupables.items.api.PickupablePrices;
import tbooop.model.pickupables.items.api.PickupableStatus;
import tbooop.model.player.api.Player;
/**
 * Class rapresenting "the Belt" item in the
 * game. If picked up by the player, it
 * will increase its velocity of a
 * determined amount.
 */
public class Belt extends ItemAbs {
    private final int itemCost = PickupablePrices.BELT_PRICE.getItemPrice();
    private PickupableStatus itemTag = PickupableStatus.NORMAL;
    private static final double VELOCITY_TO_INCREASE = 0.3;
    /**
     * Create a new istance of the Belt item.
     * 
     * @param position       spawn position (as a Point2d)
     * @param colliderRadius radius of the circle collider (hitbox).
     *                       The center of the collider will be this game object's
     *                       position
     * @param tag            the tag of this game object
     * @throws NullPointerException if any parameter passed is null
     */
    protected Belt(final Point2d position, final double colliderRadius, final GameTag tag) {
        super(position, colliderRadius, tag);
    }

    /** {@inheritDoc} 
     * 
     * @param player
    */
    @Override
    public void onPlayerCollision(final Player player) {
        onPickup(player);
    }

    /**
     * When the Belt is picked up
     * by the player, its velocity will
     * be increased.
     * 
     * @param player
    */
    private void onPickup(final Player player) {
        if (this.itemTag.equals(PickupableStatus.SPECIAL)) {
            if (player.getCoin() >= this.itemCost) {
                player.increaseVelocity(VELOCITY_TO_INCREASE);
                player.setCoin(-itemCost);
            }
        } else {
            player.increaseVelocity(VELOCITY_TO_INCREASE);
        }
        destroy();
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
        this.itemTag = PickupableStatus.SPECIAL;
    }

    /** {@inheritDoc} */
    @Override
    public int getPrice() {
        return this.itemCost;
    }

    /** {@inheritDoc} */
    @Override
    public UnmovableName getObjectName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getObjectName'");
    }
}
