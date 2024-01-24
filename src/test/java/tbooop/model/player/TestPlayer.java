package tbooop.model.player;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tbooop.commons.HealthImpl;
import tbooop.commons.Point2dImpl;
import tbooop.commons.Point2ds;
import tbooop.commons.RoomBounds;
import tbooop.model.player.impl.PlayerImpl;

class TestPlayer {

    private static PlayerImpl player;

    /**
     * Configuration step: this is performed BEFORE each test.
     */
    @BeforeAll
    static void initPlayer() {
        player = new PlayerImpl(
            new Point2dImpl(RoomBounds.HEIGHT / 2, RoomBounds.WIDTH / 2), 
            new HealthImpl(1),
            2);
    }

    @Test
    void playerHealth() {
        assertEquals(1, player.getHealth());
        player.recovery();
        assertEquals(1, player.getHealth());
        player.increaseMaxHealth(2);
        assertEquals(3, player.getMaxHealth());
        assertEquals(3, player.getHealth());
    }

    @Test 
    void playerPosition() {
        assertEquals(new Point2dImpl(RoomBounds.HEIGHT / 2, RoomBounds.WIDTH / 2), player.getPosition());
        player.updateState(1);
        player.move(Point2ds.UP);
        assertEquals(new Point2dImpl(RoomBounds.HEIGHT / 2, RoomBounds.WIDTH / 2 - 1 * 2), player.getPosition());
    }
}
