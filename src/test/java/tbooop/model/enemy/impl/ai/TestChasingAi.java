package tbooop.model.enemy.impl.ai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.HashSet;

import tbooop.commons.Point2d;
import tbooop.commons.RoomBounds;
import tbooop.model.enemy.api.ai.MovementAi;
import tbooop.model.player.api.Player;
import tbooop.model.player.impl.PlayerImpl;
import tbooop.commons.HealthImpl;

class TestChasingAi {

    private MovementAi ai;
    private Player player;

    @BeforeEach
    void initAi() {
        this.player = new PlayerImpl(new Point2d(0.0, 0.0), new HealthImpl(1), 1.0);
        this.ai = new ChasingAi(this.player);
    }

    @Test
    void testSingleMove() {
        final Set<Point2d> positions = new HashSet<>();
        this.player.setPosition(new Point2d(1.0, 1.0));
        // only positive coordinates
        positions.add(new Point2d(1.0, 2.0));
        positions.add(new Point2d(1.0, 0.0));
        positions.add(new Point2d(2.0, 1.0));
        positions.add(new Point2d(0.0, 1.0));
        positions.forEach(p -> assertEquals(
            player.getPosition(), ai.newPosition(p, 1, 1)));
        // negative and positive coordinates
        this.player.setPosition(new Point2d(0.0, 0.0));
        positions.clear();
        positions.add(new Point2d(0.0, -1.0));
        positions.add(new Point2d(0.0, 1.0));
        positions.add(new Point2d(-1.0, 0.0));
        positions.add(new Point2d(1.0, 0.0));
        positions.forEach(p -> assertEquals(
            player.getPosition(), ai.newPosition(p, 1, 1)));
        // different resulting positions
        positions.clear();
        positions.add(new Point2d(1.0, 1.0));
        positions.add(new Point2d(-1.0, -1.0));
        positions.add(new Point2d(-1.0, 1.0));
        positions.add(new Point2d(1.0, -1.0));
        // CHECKSTYLE: MagicNumber OFF
        // rule disabled because these numbers are not supposed to have any meaning and are only for testing purpose
        positions.add(new Point2d(-1.0, 1.1));
        positions.add(new Point2d(0.9, -1.0));
        positions.forEach(p -> {
            assertNotEquals(player.getPosition(), ai.newPosition(p, 1, 1));
            assertTrue(player.getPosition().distance(ai.newPosition(p, 1, 1)) <= 1);
        });
        // CHECKSTYLE: MagicNumber ON
    }

    @Test
    void testLinearMovement() {
        final Set<Point2d> positions = new HashSet<>();
        // CHECKSTYLE: MagicNumber OFF
        // rule disabled because these numbers are not supposed to have any meaning and are only for testing purpose
        positions.add(new Point2d(0.0, 100.0));
        positions.add(new Point2d(0.0, -100.0));
        positions.add(new Point2d(100.0, 0.0));
        positions.add(new Point2d(-100.0, 0.0));
        // CHECKSTYLE: MagicNumber ON
        positions.forEach(p -> {
            for (int i = 0; i < 100; i++) {
                p = this.ai.newPosition(p, 1, 1);
            }
            assertEquals(player.getPosition(), p);
        });
    }

    @Test
    void testDiagonalMovement() {
        final Set<Point2d> positions = new HashSet<>();
        // CHECKSTYLE: MagicNumber OFF
        // rule disabled because these numbers are not supposed to have any meaning and are only for testing purpose
        positions.add(new Point2d(0.0, 0.0));
        positions.add(new Point2d(0.0, RoomBounds.HEIGHT));
        positions.add(new Point2d(RoomBounds.WIDTH, 0.0));
        positions.add(new Point2d(RoomBounds.WIDTH, RoomBounds.HEIGHT));
        this.player.setPosition(
            new Point2d(RoomBounds.WIDTH / 2, RoomBounds.HEIGHT / 2));
        positions.forEach(p -> {
            for (int i = 0; i < 400; i++) {
                p = this.ai.newPosition(p, 1, 1);
            }
            assertTrue(player.getPosition().distance(p) <= 1);
        });
        // CHECKSTYLE: MagicNumber ON
    }

}
