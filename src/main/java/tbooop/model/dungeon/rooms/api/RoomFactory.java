package tbooop.model.dungeon.rooms.api;

import tbooop.model.enemy.api.EnemyFactory;

/**
 * A RoomFactory allows the creation different types of rooms.
 */
public interface RoomFactory {

    /**
     * Creates an empty room.
     *
     * @return the created starting room
     */
    Room emptyRoom();

    /**
     * Creates an enemy room.
     *
     * @param enemyFactory the factory used to spawn the enemies
     * @param amount       the amount of enemies to spawn
     * @return the created enemy room
     */
    Room enemyRoom(EnemyFactory enemyFactory, int amount);

    /**
     * Creates an item room.
     *
     * @return the created item room
     */
    Room itemRoom();

    /**
     * Creates a shop room.
     *
     * @return the created shop room
     */
    Room shopRoom();

    /**
     * Creates a trapdoor room.
     *
     * @return the created trapdoor room
     */
    Room trapdoorRoom();

}
