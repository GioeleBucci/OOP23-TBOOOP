package tbooop.controller;


import tbooop.commons.api.CardinalDirection;
import tbooop.controller.api.PlayerCommand;
import tbooop.model.player.api.Player;


/**
 * A command to move a player in a specific direction.
 */
public class MoveCommand implements PlayerCommand {

    private final CardinalDirection direction;

    /**
     * Constructs a MoveCommand object with the specified direction.
     * 
     * @param direction the direction in which the player should move
     */
    public MoveCommand(final CardinalDirection direction) {
        this.direction = direction;
    }

    /** {@inheritDoc} */
    @Override
    public void execute(final Player player) {
        player.move(direction);
    }
}
