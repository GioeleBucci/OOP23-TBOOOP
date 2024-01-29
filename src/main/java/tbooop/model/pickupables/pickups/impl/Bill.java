package tbooop.model.pickupables.pickups.impl;

import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.pickupables.pickups.api.PickupsAbs;
import tbooop.model.player.api.Player;

/**
 * Class rapresenting "Bill" item in
 * the game.
 * Bill will be collected by the player
 * and will increment its coins to use
 * in the item shop.
 */
public class Bill extends PickupsAbs {

    private static final int BILL_VALUE = 10;
    /**
     * Create a new istance of a Bill.
     * 
     * @param position       spawn position (as a Point2d)
     * @param colliderRadius radius of the circle collider (hitbox).
     *                       The center of the collider will be this game object's
     *                       position
     * @param tag            the tag of this game object
     * @throws NullPointerException if any parameter passed is null
     */
    protected Bill(final Point2d position, final double colliderRadius, final GameTag tag) {
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
     * When the Bill is picked
     * up it will increase the number
     * of coins owned by the player.
     * 
     * @param player
    */
    private void onPickup(final Player player) {
        player.setCoin(BILL_VALUE);
        destroy();
    }

    /** {@inheritDoc} */
    @Override
    public void updateState(final long deltaTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateState'");
    }
}