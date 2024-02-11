package tbooop.model.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tbooop.commons.Point2dImpl;
import tbooop.commons.Point2ds;
import tbooop.commons.RoomBounds;
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
        assertEquals(5, player.getHealth());
        player.recovery();
        assertEquals(5, player.getHealth());
        player.increaseMaxHealth(2);
        assertEquals(7, player.getMaxHealth());
        assertEquals(7, player.getHealth());
    }

    @Test 
    void playerPosition() {
        assertEquals(new Point2dImpl(RoomBounds.HEIGHT / 2, RoomBounds.WIDTH / 2), player.getPosition());
        player.updateState(1);
        assertEquals(new Point2dImpl(RoomBounds.HEIGHT / 2, RoomBounds.WIDTH / 2), player.getPosition());
        
        for (int i = 0; i < MOVES; i++) {
            player.move(Point2ds.UP);
        }

        for (int i = 0; i < MOVES; i++) {
            player.move(Point2ds.LEFT);
        }

        if (RoomBounds.outOfBounds(player.getPosition())) {
            fail();
        }
    }
}
