package tbooop.model.enemy.api;

import java.util.List;

/**
 * An EnemySpawner contains getter methods that return lists of enemies.
 */
public interface EnemySpawner {

    /**
     * Returns a list of enemies, their type and position will be choosen randomly.
     * @param amount the amount of enemies
     * @return a list of enemies
     * @throws IllegalArgumetnException if amount is negative
     */
    List<Enemy> getRandomEnemies(int amount);

}
