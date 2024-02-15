package tbooop.model.dungeon.rooms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tbooop.commons.api.CardinalDirection;
import tbooop.commons.impl.Point2dImpl;
import tbooop.model.dungeon.doors.api.DoorUnmodifiable;
import tbooop.model.dungeon.doors.impl.RegularDoor;
import tbooop.model.dungeon.rooms.api.Room;
import tbooop.model.dungeon.rooms.impl.ItemRoom;

class TestRoom {

    private Room room;

    @BeforeEach
    void initRoom() {
        room = new ItemRoom();
        room.getGameObjects().clear(); // obtain an empty room
    }

    private DoorUnmodifiable createMockDoor() {
        return new RegularDoor(Point2dImpl.ZERO, new ItemRoom());
    }

    @Test
    void testAddDoorInSamePosition() {
        assertTrue(room.getGameObjects().isEmpty());
        room.addDoor(CardinalDirection.UP, createMockDoor());
        room.addDoor(CardinalDirection.UP, createMockDoor());
        assertEquals(1, room.getDoorMap().size()); // only one door per cardinal direction
        assertEquals(1, room.getGameObjects().size());
    }

    @Test
    void testOpenAndCloseDoors() {
        CardinalDirection.getAll().forEach(d -> {
            room.addDoor(d, createMockDoor());
        });
        final Collection<DoorUnmodifiable> doors = room.getDoorMap().values();
        room.openDoors();
        assertTrue(doors.stream().allMatch(DoorUnmodifiable::isOpen));
        room.closeDoors();
        assertTrue(doors.stream().noneMatch(DoorUnmodifiable::isOpen));
    }
}
