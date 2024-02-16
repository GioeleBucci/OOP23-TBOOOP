package tbooop.model.dungeon.rooms.api;

import java.util.function.Supplier;

import tbooop.model.enemy.api.EnemyFactory;

/**
 * The RoomsFactory interface represents a factory for creating different types
 * of rooms.
 */
public interface RoomFactory {

    /**
     * Creates an enemy room.
     *
     * @param enemyFactory        the factory used to spawn the enemies
     * @param enemyAmountSupplier the function used to get the amount of enemies to
     *                            spawn
     * @return the created enemy room
     */
    Room enemyRoom(EnemyFactory enemyFactory, Supplier<Integer> enemyAmountSupplier);

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
    Room trapDoorRoom();

    /**
     * Creates a starting room.
     *
     * @return the created starting room
     */
    Room startingRoom();
}
