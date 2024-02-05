package tbooop.model.dungeon.rooms;

import org.junit.jupiter.api.Test;

import tbooop.commons.Point2ds;
import tbooop.model.dungeon.rooms.api.DoorPositions;
import tbooop.model.dungeon.rooms.impl.RegularDoor;
import tbooop.model.dungeon.rooms.impl.RegularRoom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TestRegularRoom {
    @Test
    void checkFields() {
        final RegularRoom room = new RegularRoom();
        assertFalse(room.isExplored());
        // only one door per axis
        room.addDoor(Point2ds.UP, new RegularDoor(DoorPositions.TOP.getPosition(), new RegularRoom()));
        room.addDoor(Point2ds.UP, new RegularDoor(DoorPositions.TOP.getPosition(), new RegularRoom()));
        assertEquals(1, room.getDoorMap().size());
    }
}
