package tbooop.model.dungeon.rooms.impl;

import java.util.function.Supplier;

import tbooop.model.dungeon.rooms.api.Door;
import tbooop.model.dungeon.rooms.api.RegularRoom;
import tbooop.model.dungeon.rooms.api.RoomClosable;
import tbooop.model.enemy.api.EnemyFactory;
import tbooop.model.enemy.impl.EnemySpawnerImpl;

/**
 * A room with enemies.
 */
public class EnemyRoom extends RegularRoom implements RoomClosable {

    /**
     * Creates a new enemy room.
     * 
     * @param enemyFactory        the factory used to create the enemies
     * @param enemyAmountSupplier the function used to get the amount of enemies to
     *                            spawn
     */
    public EnemyRoom(final EnemyFactory enemyFactory, final Supplier<Integer> enemyAmountSupplier) {
        addMultipleGameObjects(new EnemySpawnerImpl(enemyFactory).getRandomEnemies(enemyAmountSupplier.get()));
    }

    /** {@inheritDoc} */
    @Override
    public void closeDoors() {
        getDoorMap().values().forEach(door -> {
            ((Door) door).close();
        });
    }

    /** {@inheritDoc} */
    @Override
    public void openDoors() {
        getDoorMap().values().forEach(door -> {
            ((Door) door).open();
        });
    }
}
