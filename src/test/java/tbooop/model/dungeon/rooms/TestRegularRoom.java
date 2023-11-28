package tbooop.model.dungeon.rooms;

import org.junit.jupiter.api.Test;

import tbooop.commons.Directions;
import tbooop.model.dungeon.rooms.impl.RegularRoom;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TestRegularRoom {
    @Test
    void checkFields() {
        final RegularRoom room = new RegularRoom();
        assertFalse(room.isExplored());
        room.setDoorSet(List.of(Directions.UP, Directions.UP));
        assertEquals(1, room.getDoorSet().size());
    }
}
