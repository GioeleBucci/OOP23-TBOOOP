package tbooop.model.dungeon.doors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tbooop.commons.api.CardinalDirection;
import tbooop.model.dungeon.doors.api.DoorPositions;
import tbooop.model.dungeon.doors.impl.SpecialDoor;
import tbooop.model.dungeon.rooms.api.Room;
import tbooop.model.dungeon.rooms.api.RoomFactory;
import tbooop.model.dungeon.rooms.impl.RoomFactoryImpl;

class TestSpecialDoor {

    private final RoomFactory roomFactory = new RoomFactoryImpl();
    private Room room;
    private SpecialDoor doorA;
    private SpecialDoor doorB;

    @BeforeEach
    void initRoom() {
        room = roomFactory.itemRoom();
        doorA = new SpecialDoor(DoorPositions.TOP.getPosition(), room);
        // doorA takes to room, and doorB takes back.
        doorB = new SpecialDoor(DoorPositions.BOTTOM.getPosition(), roomFactory.itemRoom());
        room.addDoor(CardinalDirection.DOWN, doorB);
    }

    @Test
    void checkFields() {
        assertTrue(doorA.isSpecial());
        assertEquals(room, doorA.getRoom()); // doorA takes to room
        assertTrue(doorA.isLocked());
        assertFalse(doorA.isOpen());
    }

    @Test
    void testDoorLock() {
        doorA.open();
        assertTrue(doorA.isLocked()); // still locked
        assertFalse(doorA.isOpen()); // since the door is still locked, it should not open
        doorA.unlock();
        assertFalse(doorA.isLocked() || doorB.isLocked()); // the linked door should be unlocked as well
        assertTrue(doorA.isOpen() && doorB.isOpen()); // unlocked doors should also open
    }

    @Test
    void testUnlockOnce() {
        testDoorLock();
        doorA.close();
        assertFalse(doorA.isLocked()); // the door closes but remains unlocked
        doorA.open();
        assertTrue(doorA.isOpen() && !doorA.isLocked());
    }
}
