package tbooop.model.core.api.unmovable.pickupable;

import tbooop.commons.Point2d;
import tbooop.commons.api.CircleCollider;
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
    boolean isColliding(final CircleCollider player, final CircleCollider item);

    /**
    * Generates a new random Point2d
    * that is consistent with the 
    * game map dimensions.
    */
    //INCONSISTENT NOW, MAP DIMENSIONS UNKNOWN !!!
    Point2d randomCoordinatesGenerator();
}
