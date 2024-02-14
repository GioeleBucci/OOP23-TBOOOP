package tbooop.model.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tbooop.commons.api.CardinalDirection;
import tbooop.commons.api.RoomBounds;
import tbooop.commons.impl.Point2dImpl;
import tbooop.model.player.impl.PlayerImpl;

class TestPlayer {

    private static final int MOVES = 999;
    private static PlayerImpl player;

    /**
     * Configuration step: this is performed BEFORE each test.
     */
    @BeforeAll
    static void initPlayer() {
        player = new PlayerImpl(
            new Point2dImpl(RoomBounds.HEIGHT / 2, RoomBounds.WIDTH / 2));
    }

    @Test
    void playerHealth() {
        // CHECKSTYLE: MagicNumber OFF
        // rule disabled because these numbers are not supposed to have any meaning and are only for testing purpose
        assertEquals(5, player.getHealth());
        player.recovery();
        assertEquals(5, player.getHealth());
        player.increaseMaxHealth(2);
        assertEquals(7, player.getMaxHealth());
        assertEquals(7, player.getHealth());
        // CHECKSTYLE: MagicNumber ON
    }

    @Test 
    void playerPosition() {
        assertEquals(new Point2dImpl(RoomBounds.HEIGHT / 2, RoomBounds.WIDTH / 2), player.getPosition());
        player.updateState(1);
        assertEquals(new Point2dImpl(RoomBounds.HEIGHT / 2, RoomBounds.WIDTH / 2), player.getPosition());

        for (int i = 0; i < MOVES; i++) {
            player.move(CardinalDirection.UP);
        }

        for (int i = 0; i < MOVES; i++) {
            player.move(CardinalDirection.LEFT);
        }

        if (RoomBounds.outOfBounds(player.getPosition())) {
            fail();
        }
    }
}
