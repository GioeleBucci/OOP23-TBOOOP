package tbooop.model.core.api.unmovable.pickupable;

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
     *
     * @param player player CircleCollider
     * @param item   item CircleCollider
     * @return true if is colliding
     */
    boolean isColliding(CircleCollider player, CircleCollider item);

    /**
     * Determines the effect of the
     * item picked up by the
     * player.
     */
    void onPickup();

}
