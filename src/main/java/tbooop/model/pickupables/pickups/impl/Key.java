package tbooop.model.pickupables.pickups.impl;

import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.pickupables.api.PickupableName;
import tbooop.model.pickupables.pickups.api.AbstractPickup;
import tbooop.model.player.api.Player;
import tbooop.view.sound_manager.Sound;
import tbooop.view.sound_manager.SoundManager;

/**
 * Class rapresenting "Key" item in the
 * game. When it's picked up, it will
 * increase the number of the keys
 * owned by the player, and they will be
 * used to open doors.
 */
public class Key extends AbstractPickup {

    private final PickupableName pickupTag = PickupableName.KEY;

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

    /**
     * {@inheritDoc}
     * 
     * @param player
     */
    @Override
    public void onPlayerCollision(final Player player) {
        onPickup(player);
        if (SoundManager.getInstance() != null) {
            SoundManager.getInstance().playSound(Sound.KEY_PICKUP);
        }
    }

    /**
     * When the Key is picked
     * up it will increase the number
     * of keys owned by the player.
     * 
     * @param player
     */
    private void onPickup(final Player player) {
        if (!super.isConsumed()) {
            player.pickupKey();
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
