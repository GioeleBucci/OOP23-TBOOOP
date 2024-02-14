package tbooop.model.enemy.impl.ai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.HashSet;
import java.util.Random;

import tbooop.commons.api.Point2d;
import tbooop.commons.Point2dImpl;
import tbooop.commons.CardinalDirection;
import tbooop.commons.RoomBounds;
import tbooop.model.enemy.api.ai.MovementAi;
import tbooop.model.player.api.Player;
import tbooop.model.player.impl.PlayerImpl;

class TestChasingAi {

    private MovementAi ai;
    private Player player;
    private final Random rand = new Random();
    private final Set<Point2d> positions = new HashSet<>();

    @BeforeEach
    void initAi() {
        this.player = new PlayerImpl(new Point2dImpl(0.0, 0.0));
        this.ai = new ChasingAi(this.player);
        positions.clear();
    }

    @Test
    void testSingleMove() {
        this.player.setPosition(new Point2dImpl(1.0, 1.0));
        // only positive coordinates
        positions.add(new Point2dImpl(1.0, 2.0));
        positions.add(new Point2dImpl(1.0, 0.0));
        positions.add(new Point2dImpl(2.0, 1.0));
        positions.add(new Point2dImpl(0.0, 1.0));
        positions.forEach(p -> assertEquals(
            player.getPosition(), ai.newPosition(p, 1, 1)));
        // negative and positive coordinates
        this.player.setPosition(new Point2dImpl(0.0, 0.0));
        positions.clear();
        positions.add(new Point2dImpl(0.0, -1.0));
        positions.add(new Point2dImpl(0.0, 1.0));
        positions.add(new Point2dImpl(-1.0, 0.0));
        positions.add(new Point2dImpl(1.0, 0.0));
        positions.forEach(p -> assertEquals(
            player.getPosition(), ai.newPosition(p, 1, 1)));
        // different resulting positions
        positions.clear();
        positions.add(new Point2dImpl(1.0, 1.0));
        positions.add(new Point2dImpl(-1.0, -1.0));
        positions.add(new Point2dImpl(-1.0, 1.0));
        positions.add(new Point2dImpl(1.0, -1.0));
        // CHECKSTYLE: MagicNumber OFF
        // rule disabled because these numbers are not supposed to have any meaning and are only for testing purpose
        positions.add(new Point2dImpl(-1.0, 1.1));
        positions.add(new Point2dImpl(0.9, -1.0));
        positions.forEach(p -> {
            assertNotEquals(player.getPosition(), ai.newPosition(p, 1, 1));
            assertTrue(player.getPosition().distance(ai.newPosition(p, 1, 1)) <= 1);
        });
        // CHECKSTYLE: MagicNumber ON
    }

    @Test
    void testLinearMovement() {
        // CHECKSTYLE: MagicNumber OFF
        // rule disabled because these numbers are not supposed to have any meaning and are only for testing purpose
        positions.add(new Point2dImpl(0.0, 100.0));
        positions.add(new Point2dImpl(0.0, -100.0));
        positions.add(new Point2dImpl(100.0, 0.0));
        positions.add(new Point2dImpl(-100.0, 0.0));
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
        // CHECKSTYLE: MagicNumber OFF
        // rule disabled because these numbers are not supposed to have any meaning and are only for testing purpose
        positions.add(new Point2dImpl(0.0, 0.0));
        positions.add(new Point2dImpl(0.0, RoomBounds.HEIGHT));
        positions.add(new Point2dImpl(RoomBounds.WIDTH, 0.0));
        positions.add(new Point2dImpl(RoomBounds.WIDTH, RoomBounds.HEIGHT));
        this.player.setPosition(
            new Point2dImpl(RoomBounds.WIDTH / 2, RoomBounds.HEIGHT / 2));
        positions.forEach(p -> {
            for (int i = 0; i < 400; i++) {
                p = this.ai.newPosition(p, 1, 1);
            }
            assertTrue(player.getPosition().distance(p) <= 1);
        });
        // CHECKSTYLE: MagicNumber ON
    }

    @Test
    void testMovingPlayer() {
        // CHECKSTYLE: MagicNumber OFF
        // rule disabled because these numbers are not supposed to have any meaning and are only for testing purpose
        for (int i = 0; i < 50; i++) {
            positions.add(new Point2dImpl(
                rand.nextDouble(0.0, RoomBounds.WIDTH),
                rand.nextDouble(0.0, RoomBounds.HEIGHT)));
        }
        this.player.setPosition(
            new Point2dImpl(RoomBounds.WIDTH / 2.0, RoomBounds.HEIGHT / 2.0));
        //player moves up
        this.movePlayerAndAi(50, CardinalDirection.UP.toP2d());
        //player moves down-right
        this.movePlayerAndAi(50,
            CardinalDirection.RIGHT.toP2d().add(CardinalDirection.DOWN.toP2d()));
        //player moves left
        this.movePlayerAndAi(100, CardinalDirection.LEFT.toP2d());
        //player moves up-right
        this.movePlayerAndAi(50,
            CardinalDirection.RIGHT.toP2d().add(CardinalDirection.UP.toP2d()));
        //chasing positions reach the player
        positions.forEach(p -> {
            for (int i = 0; i < 400; i++) {
                p = this.ai.newPosition(p, 1, 1);
            }
            assertTrue(player.getPosition().distance(p) <= 1);
        });
        // CHECKSTYLE: MagicNumber ON
    }

    private void movePlayerAndAi(final int steps, final Point2d newPos) {
        for (int i = 0; i < steps; i++) {
            this.player.setPosition(
                this.player.getPosition().add(newPos));
           positions.forEach(p -> {
               p = this.ai.newPosition(p, 1, 1);
               assertTrue(this.isGettingCloser(p));
            });
        }
    }

    private boolean isGettingCloser(final Point2d p) {
        if (p.distance(this.player.getPosition()) > 1) {
            return this.ai.newPosition(p, 1, 1)
                .distance(this.player.getPosition())
                <= p.distance(player.getPosition());
        }
        return true;
    }

}
