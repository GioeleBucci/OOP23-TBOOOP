package tbooop.model.items.impl;

import tbooop.commons.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.items.api.PickupableAbstract;
import tbooop.model.items.api.PickupablePrices;
import tbooop.model.items.api.PickupableStatus;
import tbooop.model.player.api.Player;

/**
 * Class rapresenting "GoldenHeart" item in the
 * game. If picked up by the player, it
 * will increase its health to max level.
 */
public class GoldenHeart extends PickupableAbstract {

    private final int itemCost = PickupablePrices.GOLDENHEART_PRICE.getItemPrice();
    private PickupableStatus itemTag = PickupableStatus.NORMAL;
    /**
     * Create a new istance of a GoldenHeart.
     * 
     * @param position       spawn position (as a {@link javafx.geometry.Point2D
     *                       Point2D})
     * @param colliderRadius radius of the circle collider (hitbox).
     *                       The center of the collider will be this game object's
     *                       position
     * @param tag            the tag of this game object
     * @throws NullPointerException if any parameter passed is null
     */
    protected GoldenHeart(final Point2d position, final double colliderRadius, final GameTag tag) {
        super(position, colliderRadius, tag);
    }

    /**
     * When the GoldenHeart is picked up
     * by the player, its health will
     * be fulled to max level.
    */
    @Override
    public void onPickup(final Player player) {
        if (this.itemTag.equals(PickupableStatus.SPECIAL)) {
            if (player.getCoin() >= this.itemCost) {
                player.maxRecovery();
                player.setCoin(-itemCost);
            }
        } else {
            player.maxRecovery();
        }
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
}