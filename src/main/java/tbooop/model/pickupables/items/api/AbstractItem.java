package tbooop.model.pickupables.items.api;

import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.pickupables.api.PickupableAbs;
import tbooop.model.pickupables.api.PickupableName;
import tbooop.model.player.api.Player;

/**
 * Abstract class for pickupable items
 * <p>
 * Implements methods from Item
 * interface (equals for every item)
 * and declare abstract methods to
 * be implemented by single different
 * classes.
 */
public abstract class AbstractItem extends PickupableAbs implements Item {

    private final int itemCost;
    private ItemStatus itemTag = ItemStatus.NORMAL;
    private final PickupableName pickupTag;

    /**
     * Create a new istance of an Item.
     * 
     * @param position       starting position (as a Point2d)
     * @param colliderRadius radius of the circle collider (hitbox).
     *                       The center of the collider will be this game object's
     *                       position
     * @param tag            the tag of this game object
     * @param itemCost       cost of this item
     * @param pickupTag      name of this item
     * @throws NullPointerException if any parameter passed is null
     */
    protected AbstractItem(final Point2d position, 
                    final double colliderRadius, 
                    final GameTag tag,
                    final int itemCost,
                    final PickupableName pickupTag) {
        super(position, colliderRadius, tag);
        this.itemCost = itemCost;
        this.pickupTag = pickupTag;
    }

    /** {@inheritDoc} */
    @Override
    public void setInShop() {
        this.itemTag = ItemStatus.SPECIAL;
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
    public ItemStatus getStatus() {
        return this.itemTag;
    }

    /** {@inheritDoc} */
    @Override
    public void onPlayerCollision(final Player player) {
        if (!super.isConsumed()) {
            if (getStatus().equals(ItemStatus.SPECIAL)) {
                if (player.getCoins() >= getPrice()) {
                    onPickup(player);
                    player.consumeCoins(getPrice());
                    super.consume();
                }
            } else {
                onPickup(player);
                super.consume();
            }
        }
    }

    /**
     * Implements the effect of the item
     * when is picked up by the player.
     * This method can be overrided by subclasses
     * to define differents behaviours.
     * 
     * @param player
     */
    protected abstract void onPickup(Player player);
}
