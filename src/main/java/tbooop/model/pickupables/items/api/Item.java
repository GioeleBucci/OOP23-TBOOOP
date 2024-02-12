package tbooop.model.pickupables.items.api;

import tbooop.model.pickupables.Pickupable;

/**
 * Interface for pickupable game items.
 * <p>
 * Contains the methods that all
 * collectible items must have in the game.
 */

public interface Item extends Pickupable {

    /**
     * Sets the item as "special"
     * when it's spawned in the
     * item shop room.
     */
    void setInShop();

    /**
     * Returns item's price.
     * 
     * @return item's price.
     */
    int getPrice();

    /**
     * Returns a tag if the item is
     * in the itemshop or not.
     * 
     * @return SPECIAL if it's
     * in the itemshop, NORMAL
     * if not.
     */
    PickupableStatus getStatus();
}
