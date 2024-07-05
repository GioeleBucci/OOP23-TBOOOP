package tbooop.model.pickupables.pickups.impl;

import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.pickupables.api.PickupableName;
import tbooop.model.pickupables.pickups.api.AbstractPickup;
import tbooop.model.player.api.Player;
import tbooop.view.sound_manager.Sound;
import tbooop.view.sound_manager.SoundManager;

/**
 * Class rapresenting "Bill" item in
 * the game.
 * Bill will be collected by the player
 * and will increment its coins to use
 * in the item shop.
 */
public class Bill extends AbstractPickup {

    private static final int BILL_VALUE = 10;
    private final PickupableName pickupTag = PickupableName.BILL;
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
        SoundManager.getInstance().playSound(Sound.COIN_PICKUP);
    }

    /**
     * When the Bill is picked
     * up it will increase the number
     * of coins owned by the player.
     * 
     * @param player
    */
    private void onPickup(final Player player) {
        if (!super.isConsumed()) {
            player.addCoins(BILL_VALUE);
            destroy();
            super.consume();
        }
    }

    /** {@inheritDoc} */
    @Override
    public PickupableName getObjectName() {
        return this.pickupTag;
    }
}
