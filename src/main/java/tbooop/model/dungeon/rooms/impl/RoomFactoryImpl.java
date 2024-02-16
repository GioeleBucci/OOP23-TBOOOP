package tbooop.model.dungeon.rooms.impl;

import java.util.function.Supplier;

import tbooop.model.dungeon.doors.impl.TrapdoorImpl;
import tbooop.model.dungeon.rooms.api.RegularRoom;
import tbooop.model.dungeon.rooms.api.Room;
import tbooop.model.dungeon.rooms.api.RoomFactory;
import tbooop.model.dungeon.rooms.api.SpecialRoom;
import tbooop.model.enemy.api.EnemyFactory;
import tbooop.model.enemy.impl.EnemySpawnerImpl;
import tbooop.model.pickupables.items.impl.ItemRoomLogic;
import tbooop.model.pickupables.items.impl.ItemShopLogic;

/**
 * Creates all different kinds of rooms.
 */
public class RoomFactoryImpl implements RoomFactory {

    /** {@inheritDoc} */
    @Override
    public Room enemyRoom(final EnemyFactory enemyFactory, final Supplier<Integer> enemyAmountSupplier) {
        return new RegularRoom() {
            @Override
            public void init() {
                addGameObjects(new EnemySpawnerImpl(enemyFactory).getRandomEnemies(enemyAmountSupplier.get()));
            }
        };
    }

    /** {@inheritDoc} */
    @Override
    public Room itemRoom() {
        return new SpecialRoom() {
            @Override
            public void init() {
                addGameObject(new ItemRoomLogic().getRandomItem());
            }
        };
    }

    /** {@inheritDoc} */
    @Override
    public Room shopRoom() {
        return new SpecialRoom() {
            @Override
            public void init() {
                addGameObjects(new ItemShopLogic().getItemPool().keySet());
            }
        };
    }

    /** {@inheritDoc} */
    @Override
    public Room trapdoorRoom() {
        return new RegularRoom() {
            @Override
            public void init() {
                addGameObject(new TrapdoorImpl());
            }
        };
    }

    /** {@inheritDoc} */
    @Override
    public Room emptyRoom() {
        return new RegularRoom() {
            @Override
            public boolean isFirstRoom() {
                return true;
            }

            @Override
            public void init() {
                // nothing to do
            }
        };
    }

}
