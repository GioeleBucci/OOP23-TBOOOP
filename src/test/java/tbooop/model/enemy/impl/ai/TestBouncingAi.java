package tbooop.model.enemy.impl.ai;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tbooop.commons.Point2dImpl;
import tbooop.commons.RoomBounds;
import tbooop.commons.Vector2dImpl;
import tbooop.commons.api.Point2d;
import tbooop.model.enemy.api.ai.MovementAi;

class TestBouncingAi {

    private MovementAi ai;
    private final Random rand = new Random();

    @BeforeEach
    void initAi() {
        this.ai = new BouncingAi(new Point2dImpl(0.0, 0.0), 0);
    }

    @Test
    void testBouncingOnMapBounds() {
        // CHECKSTYLE: MagicNumber OFF
        // rule disabled because these numbers are not supposed to have any meaning and are only for testing purpose
        Point2d currentPos;
        for (int i = 0; i < 50; i++) {
            // random initial direction
            this.ai = new BouncingAi(new Vector2dImpl(
                rand.nextDouble(-10, 10),
                rand.nextDouble(-10, 10))
                .normalize().toP2d(), 1);
            for (int j = 0; j < 100; j++) {
                // random initial position
                currentPos = new Point2dImpl(
                    rand.nextDouble(0, RoomBounds.WIDTH),
                    rand.nextDouble(0, RoomBounds.HEIGHT));
                for (int k = 0; k < 10_000; k++) {
                    currentPos = ai.newPosition(currentPos, 1, 1);
                }
                assertFalse(RoomBounds.outOfBounds(currentPos));
            }
        }
        // CHECKSTYLE: MagicNumber ON
    }

}
