package tbooop.model.pickupables.items.impl;

import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.pickupables.UnmovableName;
import tbooop.model.pickupables.items.api.ItemAbs;
import tbooop.model.pickupables.items.api.PickupablePrices;
import tbooop.model.player.api.Player;

/**
 * Class rapresenting "GoldenApple" item in the
 * game. If picked up by the player, it
 * will increase its health to max level.
 */
public class GoldenApple extends ItemAbs {

    private final int itemCost = PickupablePrices.GOLDENAPPLE_PRICE.getItemPrice();
    //private PickupableStatus itemTag = PickupableStatus.NORMAL;
    private final UnmovableName pickupTag = UnmovableName.GOLDEN_APPLE;
    /**
     * Create a new istance of a GoldenApple.
     * 
     * @param position       spawn position (as a Point2d)
     * @param colliderRadius radius of the circle collider (hitbox).
     *                       The center of the collider will be this game object's
     *                       position
     * @param tag            the tag of this game object
     * @throws NullPointerException if any parameter passed is null
     */
    protected GoldenApple(final Point2d position, final double colliderRadius, final GameTag tag) {
        super(position, colliderRadius, tag);
    }

    /** {@inheritDoc} 
    */
    @Override
    public void onPlayerCollision(final Player player) {
        onPickup();
    }

    /**
     * When the GoldenApple is picked up
     * by the player, its max-health will
     * be increased by one.
    */
    private void onPickup() {
        destroy();
    }

    /** {@inheritDoc} */
    @Override
    public void setInShop() {
        //this.itemTag = PickupableStatus.SPECIAL;
    }

    /** {@inheritDoc} */
    @Override
    public int getPrice() {
        return this.itemCost;
    }

    /** {@inheritDoc} */
    @Override
    public UnmovableName getObjectName() {
        return this.pickupTag;
    }
}
