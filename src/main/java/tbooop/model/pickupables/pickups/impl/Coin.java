package tbooop.model.pickupables.pickups.impl;

import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.pickupables.pickups.api.PickupsAbs;
import tbooop.model.player.api.Player;

/**
 * Class rapresenting "Coin" item in
 * the game.
 * Coins will be collected by the player
 * and will be used in the items shop.
 */
public class Coin extends PickupsAbs {

    private static final int COIN_VALUE = 1;
    /**
     * Create a new istance of a Coin.
     * 
     * @param position       spawn position (as a Point2d)
     * @param colliderRadius radius of the circle collider (hitbox).
     *                       The center of the collider will be this game object's
     *                       position
     * @param tag            the tag of this game object
     * @throws NullPointerException if any parameter passed is null
     */
    protected Coin(final Point2d position, final double colliderRadius, final GameTag tag) {
        super(position, colliderRadius, tag);
    }

    /** {@inheritDoc} 
     * 
     * @param player
    */
    @Override
    public void onCollision(final Player player) {
        onPickup(player);
    }

    /**
     * When the Coin is picked
     * up it will increase the number
     * of coins owned by the player.
     * 
     * @param player
    */
    private void onPickup(final Player player) {
        player.setCoin(COIN_VALUE);
        destroy();
    }

    /** {@inheritDoc} */
    @Override
    public void updateState(final long deltaTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateState'");
    }
}
