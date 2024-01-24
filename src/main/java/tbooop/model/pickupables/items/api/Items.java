package tbooop.model.pickupables.items.api;

import tbooop.model.core.api.unmovable.Unmovable;

/**
 * Interface for pickupable game items.
 * <p>
 * Contains the methods that all
 * collectible items must have in the game.
 */

public interface Items extends Unmovable {

    /**
     * Sets the item as "special"
     * when it's spawned in the
     * item shop room.
     */
    void setInShop();
}
