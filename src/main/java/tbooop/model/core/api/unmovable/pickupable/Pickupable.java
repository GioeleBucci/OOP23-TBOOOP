package tbooop.model.core.api.unmovable.pickupable;

import tbooop.model.core.api.unmovable.Unmovable;

/**
 * Interface for pickupable game items.
 * <p>
 * Contains the methods that all 
 * collectible items must have in the game.
 */

public interface Pickupable extends Unmovable {

    /**
    * Changes the state of the object it takes
    * as a parameter, following the collection
    * of a game item.
    */
    void onPickup();
}
