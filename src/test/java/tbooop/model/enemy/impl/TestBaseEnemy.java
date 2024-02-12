package tbooop.model.enemy.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tbooop.commons.HealthImpl;
import tbooop.commons.Point2dImpl;
import tbooop.commons.api.Point2d;
import tbooop.commons.RoomBounds;
import tbooop.model.enemy.api.Enemy;
import tbooop.model.enemy.api.EnemyType;
import tbooop.model.enemy.impl.ai.ChasingAi;
import tbooop.model.player.api.Player;
import tbooop.model.player.impl.PlayerImpl;

class TestBaseEnemy {

    private static final Point2d BASE_POS = new Point2dImpl(0, 0);
    private static final int ENEMY_HP = 3;
    private static final int PLAYER_HP = 5;
    private static final double BASE_RADIUS = 15;

    private final Player player = new PlayerImpl(BASE_POS);
    private Enemy enemy;

    @BeforeEach
    void initEnemy() {
        this.enemy = new BaseEnemy(BASE_POS, new HealthImpl(ENEMY_HP),
            1.0, new ChasingAi(this.player), EnemyType.MELEE, BASE_RADIUS);
    }

    @Test
    void testAttack() {
        this.enemy.onPlayerCollision(this.player);
        assertEquals(PLAYER_HP, this.player.getHealth());
        this.enemy.onPlayerCollision(this.player);
        assertEquals(PLAYER_HP, this.player.getHealth());
        this.enemy.onPlayerCollision(this.player);
        assertEquals(PLAYER_HP, this.player.getHealth());
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
            new Point2dImpl(RoomBounds.WIDTH / 2, RoomBounds.HEIGHT / 2));
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
