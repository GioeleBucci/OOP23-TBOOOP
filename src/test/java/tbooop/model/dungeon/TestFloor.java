package tbooop.model.dungeon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class TestFloor {

    @Test
    void moreThanTwoRooms() {
        try {
            new Floor(2);
            fail("There should be at least 3 rooms in a floor");
        } catch (IllegalArgumentException e) {
            assertFalse(e.getMessage().isBlank());
        }
        new Floor(3);
    }

    @Test
    void checkRightRoomsAmount() {
        final int expectedRoomsAmount = 15;
        assertEquals(expectedRoomsAmount, new Floor(expectedRoomsAmount).getRoomsMap().size());
    }
}
