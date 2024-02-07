package tbooop.model.enemy.api;

import tbooop.commons.Point2ds;
import tbooop.commons.api.Point2d;

/**
 * An enemy factory is an object that handles the creation of enemies.
 * Each method returns a particular enemy configuration.
 */
public interface EnemyFactory {

    /**
     * Creates and returns an enemy that is able to chase the player and
     * deals damage to it when a physical contact happens.
     * 
     * @return an istance of a melee enemy.
     */
    Enemy melee();

    /**
     * Creates and returns an enemy that moves left-right or up-down
     * and can shoot projectiles against the player.
     * <p>
     * If initialDirection is LEFT or RIGHT the shooter will move left-right,
     * vice versa if it is UP or DOWN the shooter will move up-down.
     * 
     * @param initialDirection it determines if the shooter moves up-down or left-right
     * @return an instance of a shooter
     * @throws NullPointerException if the parameter is null
     */
    Enemy shooter(Point2ds initialDirection);

    /**
     * Creates and returns an enemy that moves in a straight line and bounces on walls 
     * like a ball, in death it "explodes" by shooting projectiles in all direction.
     * It also deals damage on physical contact.
     * 
     * @param initialDirection the bouncer's initial direction
     * @return an instance of a bouncer enemy.
     * @throws NullPointerException if initialDirection is null
     */
    Enemy bouncer(Point2d initialDirection);

    /**
     * Creates and returns an enemy that uses all the attack systems available.
     * It moves by chasing the player.
     * @return an instance of a crazy enemy
     */
    Enemy crazy();

}
