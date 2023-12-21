package tbooop.model.dungeon.rooms;

import org.junit.jupiter.api.Test;

import tbooop.model.dungeon.rooms.impl.RegularRoom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TestRegularRoom {
    @Test
    void checkFields() {
        final RegularRoom room = new RegularRoom();
        assertFalse(room.isExplored());
        //room.newDoor(List.of(Points2d.UP, Points2d.UP));
        assertEquals(1, room.getDoorMap().size());
    }
}
