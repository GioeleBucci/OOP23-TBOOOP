package tbooop.model.pickupables.items.impl;

import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.pickupables.api.PickupableName;
import tbooop.model.pickupables.items.api.AbstractItem;
import tbooop.model.player.api.Player;
/**
 * Class rapresenting Iron Bar item in the
 * game. If picked up by the player, it
 * will increase its damage.
 */
public class LockedRing extends AbstractItem {

    public static final float DAMAGE_MULTIPLIER = 1.3f;

    /**
     * Create a new istance of Locked Ring item.
     * 
     * @param position       spawn position (as a Point2d)
     * @param colliderRadius radius of the circle collider (hitbox).
     *                       The center of the collider will be this game object's
     *                       position
     * @param tag            the tag of this game object
     * @param itemCost       cost of this item
     * @param pickupTag      name of this item
     * @throws NullPointerException if any parameter passed is null
     */
    protected LockedRing(final Point2d position, 
                        final double colliderRadius, 
                        final GameTag tag,
                        final int itemCost,
                        final PickupableName pickupTag) {
        super(position, colliderRadius, tag, itemCost, pickupTag);
    }

    /**
     * When the Locked Ring item is picked up
     * by the player, its
     * damage will increse.
     * 
     * @param player
    */
    @Override
    protected void onPickup(final Player player) {
        super.onPickup(player);
        player.increaseDamage(player.getDamage() * DAMAGE_MULTIPLIER - player.getDamage());
        destroy();
    }
}
