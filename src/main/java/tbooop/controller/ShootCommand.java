package tbooop.controller;

import tbooop.commons.Point2ds;
import tbooop.controller.api.PlayerCommand;
import tbooop.model.player.api.Player;

/**
 * A command to shoot in a specific direction.
 */
public class ShootCommand implements PlayerCommand {

    private final Point2ds direction;

    /**
     * Constructs a ShootCommand object with the specified direction.
     * 
     * @param direction the direction in which the player should shoot
     */
    public ShootCommand(final Point2ds direction) {
        this.direction = direction;
    }

    /** {@inheritDoc} */
    @Override
    public void execute(final Player player) {
        player.move(direction);
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}
