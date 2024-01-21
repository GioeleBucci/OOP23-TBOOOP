package tbooop.model.enemy.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tbooop.commons.HealthImpl;
import tbooop.commons.Point2d;
import tbooop.commons.RoomBounds;
import tbooop.model.enemy.api.Enemy;
import tbooop.model.enemy.impl.ai.ChasingAi;
import tbooop.model.player.api.Player;
import tbooop.model.player.impl.PlayerImpl;


class TestBaseEnemy {

    private static final Point2d BASE_POS = new Point2d(0, 0);
    private static final int ENEMY_HP = 3;
    private static final int PLAYER_HP = 3;

    private final Player player = new PlayerImpl(
        BASE_POS, new HealthImpl(PLAYER_HP), 1.0);
    private Enemy enemy;

    @BeforeEach
    void initEnemy() {
        this.enemy = new BaseEnemy(
            BASE_POS, new HealthImpl(ENEMY_HP), 1.0, player, new ChasingAi(this.player));
    }

    @Test
    void testAttack() {
        this.enemy.onCollision(this.player);
        assertEquals(this.player.getHealth(), PLAYER_HP - 1);
        this.enemy.onCollision(this.player);
        assertEquals(this.player.getHealth(), PLAYER_HP - 2);
        this.enemy.onCollision(this.player);
        assertEquals(this.player.getHealth(), PLAYER_HP - 3);
    }

    @Test
    void testDamage() {
        this.enemy.takeDamage(1);
        assertEquals(this.enemy.getHealth(), ENEMY_HP - 1);
        this.enemy.takeDamage(1);
        assertEquals(this.enemy.getHealth(), ENEMY_HP - 2);
        this.enemy.takeDamage(1);
        assertEquals(this.enemy.getHealth(), ENEMY_HP - 3);
    }

    @Test
    void testMovement() {
        this.enemy.setPosition(
            new Point2d(RoomBounds.WIDTH / 2, RoomBounds.HEIGHT / 2));
        // CHECKSTYLE: MagicNumber OFF
        // rule disabled because these numbers are not supposed to have any meaning and are only for testing purpose
        for (int i = 0; i < 400; i++) {
            this.enemy.updateState(1);
        }
        // CHECKSTYLE: MagicNumber ON
        assertTrue(this.player.getPosition()
            .distance(this.enemy.getPosition()) <= 1);
    }

}
