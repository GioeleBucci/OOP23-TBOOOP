package tbooop.model.enemy.impl;

import tbooop.commons.Point2d;
import tbooop.commons.api.Health;
import tbooop.model.enemy.api.AbstractEnemy;
import tbooop.model.enemy.api.ai.MovementAi;
import tbooop.model.player.api.Player;

/**
 * basic enemy class, it can only move and be damaged/killed, but it does not
 * attack the player.
 */
public class BaseEnemy extends AbstractEnemy {

    /**
     * creates a new istance of a basic enemy.
     * 
     * @param position on the 2D map
     * @param health the enemy's health
     * @param velocity determines how fast the enemy moves
     * @param player the game's player
     * @param ai the enemy's movement ai
     */
    protected BaseEnemy(
        final Point2d position,
        final Health health,
        final double velocity,
        final Player player,
        final MovementAi ai) {
        super(position, health, velocity, player, ai);
        //TODO Auto-generated constructor stub
    }

}
