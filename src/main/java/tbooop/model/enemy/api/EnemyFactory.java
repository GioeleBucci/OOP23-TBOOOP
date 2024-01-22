package tbooop.model.enemy.api;

/**
 * An enemy factory is an object that handles the creation of enemies.
 * Each method returns a particular enemy configuration.
 */
public interface EnemyFactory {

    /**
     * Creates and returns an enemy that is able to chase the player and
     * deals damage to it when a physical contact happens.
     * 
     * @return an istance of a melee enemy
     */
    Enemy melee();
}
