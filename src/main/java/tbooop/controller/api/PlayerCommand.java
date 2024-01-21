package tbooop.controller.api;

import tbooop.model.player.api.Player;

/**
 * This interface models an Command.
 * Each command can be triggered by a different source, and executes
 * some type action on the Player.
 */
public interface PlayerCommand {
    /**
     * Excecute some type of action on the Player.
     * 
     * @param player
     */
    void execute(Player player);
}
