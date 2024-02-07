package tbooop.model.dungeon.rooms.impl;

import tbooop.model.dungeon.rooms.api.RegularRoom;
import tbooop.model.enemy.api.EnemyFactory;
import tbooop.model.enemy.impl.EnemySpawnerImpl;

/**
 * A room with enemies.
 */
public class EnemyRoom extends RegularRoom {

    /**
     * Creates a new enemy room.
     * 
     * @param enemyFactory the factory used to create the enemies
     */
    public EnemyRoom(final EnemyFactory enemyFactory) {
        addMultipleGameObjects(new EnemySpawnerImpl(enemyFactory).getRandomEnemies(4));
    }

}
