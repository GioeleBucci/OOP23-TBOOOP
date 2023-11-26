package tbooop.model.dungeon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import javafx.geometry.Point2D;

public class TestFloor<A> {

    @Test
    void moreThanTwoRooms() {
        try {
            new Floor(2);
            fail();
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
