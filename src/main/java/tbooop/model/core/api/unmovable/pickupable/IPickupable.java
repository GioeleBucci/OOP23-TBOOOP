package tbooop.model.core.api.unmovable.pickupable;

import tbooop.model.core.api.unmovable.IUnmovable;

/**
 * Interface for pickupable game items.
 * <p>
 * Contains the methods that all 
 * collectible items must have in the game.
 */

public interface IPickupable extends IUnmovable {

    /**
    * Changes the state of the object it takes
    * as a parameter, following the collection
    * of a game item.
    */
    void onPickup();
}
