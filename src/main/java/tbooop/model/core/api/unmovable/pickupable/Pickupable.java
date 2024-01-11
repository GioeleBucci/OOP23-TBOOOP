package tbooop.model.core.api.unmovable.pickupable;

import tbooop.model.core.api.unmovable.Unmovable;
import tbooop.model.player.api.Player;

/**
 * Interface for pickupable game items.
 * <p>
 * Contains the methods that all
 * collectible items must have in the game.
 */

public interface Pickupable extends Unmovable {

    /**
     * Determines the effect of the
     * item picked up by the
     * player.
     * 
     * @param player istance of player
     */
    void onPickup(Player player);
}
