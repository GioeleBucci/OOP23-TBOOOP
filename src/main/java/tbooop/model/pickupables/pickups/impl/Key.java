package tbooop.model.pickupables.pickups.impl;

import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.pickupables.pickups.api.PickupsAbs;
import tbooop.model.player.api.Player;

/**
 * Class rapresenting "Key" item in the
 * game. When it's picked up, it will
 * increase the number of the keys
 * owned by the player, and they will be
 * used to open doors.
 */
public class Key extends PickupsAbs {
    /**
     * Create a new istance of a Key.
     * 
     * @param position       spawn position (as a Point2d)
     * @param colliderRadius radius of the circle collider (hitbox).
     *                       The center of the collider will be this game object's
     *                       position
     * @param tag            the tag of this game object
     * @throws NullPointerException if any parameter passed is null
     */
    protected Key(final Point2d position, final double colliderRadius, final GameTag tag) {
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
     * When the Key is picked
     * up it will increase the number
     * of keys owned by the player.
     * 
     * @param player
    */
    private void onPickup(final Player player) {
        player.pickupKeys();
        destroy();
    }

    /** {@inheritDoc} */
    @Override
    public void updateState(final long deltaTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateState'");
    }
}
