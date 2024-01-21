package tbooop.controller;


import tbooop.commons.Point2ds;
import tbooop.controller.api.PlayerCommand;
import tbooop.model.player.api.Player;

/**
 * .
 */
public class MoveCommand implements PlayerCommand {

    private final Point2ds direction;

    /**
     * ...
     * 
     * @param direction the view
     */
    public MoveCommand(final Point2ds direction) {
        this.direction = direction;
    }

    /** {@inheritDoc} */
    @Override
    public void execute(final Player player) {
        player.move(direction);
    }
}
