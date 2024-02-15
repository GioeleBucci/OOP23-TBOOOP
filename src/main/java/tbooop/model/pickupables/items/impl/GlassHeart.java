package tbooop.model.pickupables.items.impl;

import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.pickupables.api.PickupableName;
import tbooop.model.pickupables.items.api.ItemAbs;
import tbooop.model.pickupables.items.api.ItemPrice;
import tbooop.model.pickupables.items.api.PickupableStatus;
import tbooop.model.player.api.Player;

/**
 * Class rapresenting "GlassHeart" item in the
 * game. If picked up by the player, it
 * will increase its health to max level.
 */
public class GlassHeart extends ItemAbs {

    private final int itemCost = ItemPrice.GLASSHEART_PRICE.getItemPrice();
    private PickupableStatus itemTag = PickupableStatus.NORMAL;
    private final PickupableName pickupTag = PickupableName.GLASS_HEART;
    /**
     * Create a new istance of a GlassHeart.
     * 
     * @param position       spawn position (as a Point2d)
     * @param colliderRadius radius of the circle collider (hitbox).
     *                       The center of the collider will be this game object's
     *                       position
     * @param tag            the tag of this game object
     * @throws NullPointerException if any parameter passed is null
     */
    protected GlassHeart(final Point2d position, final double colliderRadius, final GameTag tag) {
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
     * When the GlassHeart is picked up
     * by the player, its health will
     * be fulled to max level.
     * 
     * @param player
    */
    private void onPickup(final Player player) {
        if (!super.isConsumed()) {
            if (this.itemTag.equals(PickupableStatus.SPECIAL)) {
                if (player.getCoins() >= this.itemCost) {
                    player.maxRecovery();
                    player.consumeCoins(itemCost);
                    destroy();
                    super.consume();
                }
            } else {
                player.maxRecovery();
                destroy();
                super.consume();
            }
        }
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
    public PickupableName getObjectName() {
        return this.pickupTag;
    }

    /** {@inheritDoc} */
    @Override
    public PickupableStatus getStatus() {
        return this.itemTag;
    }
}
