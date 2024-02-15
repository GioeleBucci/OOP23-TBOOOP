package tbooop.model.dungeon.rooms;

import org.junit.jupiter.api.Test;

import tbooop.commons.api.RoomBounds;
import tbooop.model.dungeon.rooms.api.Room;
import tbooop.model.dungeon.rooms.impl.EnemyRoom;
import tbooop.model.enemy.api.EnemyFactory;
import tbooop.model.enemy.impl.EnemyFactoryImpl;
import tbooop.model.player.impl.PlayerImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestRegularRoom {

    private final EnemyFactory enemyFactory = new EnemyFactoryImpl(new PlayerImpl(RoomBounds.CENTER));

    @Test
    void checkFields() {
        final Room room = new EnemyRoom(enemyFactory, () -> 0);
        assertFalse(room.isFirstRoom());
        assertFalse(room.isSpecial());
        assertTrue(room.getGameObjects().isEmpty());
    }

    @Test
    void checkSpawnAmount() {
        final Room room = new EnemyRoom(enemyFactory, () -> 10);
        assertEquals(10, room.getGameObjects().size());
    }

}
