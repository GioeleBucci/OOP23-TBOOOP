package tbooop.model.dungeon.rooms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tbooop.model.dungeon.rooms.api.Room;
import tbooop.model.dungeon.rooms.impl.ItemRoom;

class TestItemRoom {

    private final Room itemRoom = new ItemRoom();

    @Test
    void testOnlyOneItemInside() {
        assertEquals(1, itemRoom.getGameObjects().size());
    }

}
