package tbooop.model.dungeon.rooms.impl;

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
    public Room enemyRoom(final EnemyFactory enemyFactory, final int amount) {
        return new RegularRoom() {
            @Override
            public void init() {
                addGameObjects(new EnemySpawnerImpl(enemyFactory).getRandomEnemies(amount));
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
    public Room bossRoom(final EnemyFactory enemyFactory) {
        return new RegularRoom() {
            @Override
            public boolean isBossRoom() {
                return true;
            }

            @Override
            public void init() {
                addGameObject(enemyFactory.boss());
            }
        };
    }

    /** {@inheritDoc} */
    @Override
    public Room startingRoom() {
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
