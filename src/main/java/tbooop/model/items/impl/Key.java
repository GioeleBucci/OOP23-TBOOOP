package tbooop.model.items.impl;

import tbooop.commons.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.items.api.PickupableAbstract;
import tbooop.model.player.api.Player;

/**
 * Class rapresenting "Key" item in the
 * game. When it's picked up, it will
 * increase the number of the keys
 * owned by the player, and they will be
 * used to open doors.
 */
public class Key extends PickupableAbstract {
    /**
     * Create a new istance of a Key.
     * 
     * @param position       spawn position (as a {@link javafx.geometry.Point2D
     *                       Point2D})
     * @param colliderRadius radius of the circle collider (hitbox).
     *                       The center of the collider will be this game object's
     *                       position
     * @param tag            the tag of this game object
     * @throws NullPointerException if any parameter passed is null
     */
    protected Key(final Point2d position, final double colliderRadius, final GameTag tag) {
        super(position, colliderRadius, tag);
    }

    /**
     * When the Key is picked
     * up it will increase the number
     * of keys owned by the player.
    */
    @Override
    public void onPickup(final Player player) {
        player.pickupKeys();
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
