package tbooop.model.dungeon.rooms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tbooop.model.dungeon.rooms.api.Room;
import tbooop.model.dungeon.rooms.impl.RoomFactoryImpl;

class TestItemRoom {

    private final Room itemRoom = new RoomFactoryImpl().itemRoom();

    @Test
    void testOnlyOneItemInside() {
        itemRoom.init();
        assertEquals(1, itemRoom.getGameObjects().size());
    }

}
