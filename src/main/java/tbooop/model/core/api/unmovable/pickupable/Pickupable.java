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
    * Determines the condition under which 
    * a game entity comes into contact 
    * with an object.
    */
    void onCollision();
}
