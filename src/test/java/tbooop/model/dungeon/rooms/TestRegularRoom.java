package tbooop.model.dungeon.rooms;

import org.junit.jupiter.api.Test;

import tbooop.commons.Point2ds;
import tbooop.model.dungeon.rooms.api.DoorPositions;
import tbooop.model.dungeon.rooms.api.RegularRoom;
import tbooop.model.dungeon.rooms.impl.EnemyRoom;
import tbooop.model.dungeon.rooms.impl.RegularDoor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TestRegularRoom {
    @Test
    void checkFields() {
        final RegularRoom room = new EnemyRoom();
        assertFalse(room.isExplored());
        // only one door per axis
        room.addDoor(Point2ds.UP, new RegularDoor(DoorPositions.TOP.getPosition(), new EnemyRoom()));
        room.addDoor(Point2ds.UP, new RegularDoor(DoorPositions.TOP.getPosition(), new EnemyRoom()));
        assertEquals(1, room.getDoorMap().size());
    }
}
