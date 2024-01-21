package tbooop.model.pickupables.pickups.impl;

import tbooop.commons.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.pickupables.api.PickupableAbstract;
import tbooop.model.player.api.Player;

/**
 * Class rapresenting "Coin" item in
 * the game.
 * Coins will be collected by the player
 * and will be used in the items shop.
 */
public class Coin extends PickupableAbstract {

    private static final int COIN_VALUE = 1;
    /**
     * Create a new istance of a Coin.
     * 
     * @param position       spawn position (as a {@link javafx.geometry.Point2D
     *                       Point2D})
     * @param colliderRadius radius of the circle collider (hitbox).
     *                       The center of the collider will be this game object's
     *                       position
     * @param tag            the tag of this game object
     * @throws NullPointerException if any parameter passed is null
     */
    public Coin(final Point2d position, final double colliderRadius, final GameTag tag) {
        super(position, colliderRadius, tag);
    }

    /**
     * When the Coin is picked
     * up it will increase the number
     * of coins owned by the player.
    */
    @Override
    public void onPickup(final Player player) {
        player.setCoin(COIN_VALUE);
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
