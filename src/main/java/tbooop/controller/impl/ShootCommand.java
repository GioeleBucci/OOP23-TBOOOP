package tbooop.controller.impl;

import tbooop.commons.api.CardinalDirection;
import tbooop.controller.api.PlayerCommand;
import tbooop.model.player.api.Player;

/**
 * A command to shoot in a specific direction.
 */
public class ShootCommand implements PlayerCommand {

    private final CardinalDirection direction;

    /**
     * Constructs a ShootCommand object with the specified direction.
     * 
     * @param direction the direction in which the player should shoot
     */
    public ShootCommand(final CardinalDirection direction) {
        this.direction = direction;
    }

    /** {@inheritDoc} */
    @Override
    public void execute(final Player player) {
        player.shoot(direction.toP2d().toV2d());
    }
}
