package tbooop.model.core.api.unmovable;

import tbooop.model.core.api.GameObject;
import tbooop.model.player.api.Player;

/**
 * Interface for unmovable game object.
 * <p>
 * It represents a tag interface for unmovable game objects.
 */

public interface Unmovable extends GameObject {

    /**
     * Determines the effect of the
     * item picked up by the
     * player.
     * 
     * @param player istance of player
     */
    void onPickup(Player player);
}
