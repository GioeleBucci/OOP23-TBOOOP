package tbooop.model.enemy.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

class TestMelee {

    private static final Point2d BASE_POS = new Point2dImpl(0, 0);
    private static final int ENEMY_HP = 100;
    private static final int PLAYER_HP = 100;


    private final Player player = new PlayerImpl(
        BASE_POS, new HealthImpl(PLAYER_HP), 1.0);
    private Enemy melee;

    @BeforeEach
    void initEnemy() {
        this.player.maxRecovery();
        this.melee = new Melee(new BaseEnemy(
            BASE_POS,
            new HealthImpl(ENEMY_HP),
            1.0,
            new ChasingAi(this.player), EnemyType.MELEE));
    }

    @Test
    void testExceptions() {
        assertThrows(NullPointerException.class,
            () -> this.melee = new Melee(null));

        assertThrows(NullPointerException.class,
            () -> this.melee = new Melee(new Melee(null)));

        assertThrows(NullPointerException.class,
            () -> this.melee = new Melee(new Melee(new Melee(null))));
    }

    @Test
    void testAttack() {
        this.melee.updateState(1000);
        this.melee.onPlayerCollision(this.player);
        assertEquals(this.player.getHealth(), PLAYER_HP - 1);
        this.melee.updateState(1000);
        this.melee.onPlayerCollision(this.player);
        assertEquals(this.player.getHealth(), PLAYER_HP - 2);
        this.melee.updateState(1000);
        this.melee.onPlayerCollision(this.player);
        assertEquals(this.player.getHealth(), PLAYER_HP - 3);
    }

    @Test
    void testDamage() {
        this.melee.takeDamage(1);
        assertEquals(this.melee.getHealth(), ENEMY_HP - 1);
        this.melee.takeDamage(1);
        assertEquals(this.melee.getHealth(), ENEMY_HP - 2);
        this.melee.takeDamage(1);
        assertEquals(this.melee.getHealth(), ENEMY_HP - 3);
    }

    @Test
    void testMovement() {
        this.melee.setPosition(
            new Point2dImpl(RoomBounds.WIDTH / 2, RoomBounds.HEIGHT / 2));
        // CHECKSTYLE: MagicNumber OFF
        // rule disabled because these numbers are not supposed to have any meaning and are only for testing purpose
        for (int i = 0; i < 400; i++) {
            this.melee.updateState(1);
        }
        // CHECKSTYLE: MagicNumber ON
        assertTrue(this.player.getPosition()
            .distance(this.melee.getPosition()) <= 1);
    }

    @Test
    void testDoubleMelee() {
        final Enemy doubleMelee = new Melee(this.melee);
        doubleMelee.updateState(1000);
        doubleMelee.onPlayerCollision(this.player);
        // CHECKSTYLE: MagicNumber OFF
        // rule disabled because these numbers are not supposed to have any meaning and are only for testing purpose
        assertEquals(this.player.getHealth(), PLAYER_HP - 2);
        doubleMelee.updateState(1000);
        doubleMelee.onPlayerCollision(this.player);
        assertEquals(this.player.getHealth(), PLAYER_HP - 4);
        doubleMelee.updateState(1000);
        doubleMelee.onPlayerCollision(this.player);
        assertEquals(this.player.getHealth(), PLAYER_HP - 6);
        // CHECKSTYLE: MagicNumber ON
    }

    @Test
    void testDoubleMeleeMovement() {
        final Enemy doubleMelee = new Melee(this.melee);
        doubleMelee.setPosition(
            new Point2dImpl(RoomBounds.WIDTH / 2, RoomBounds.HEIGHT / 2));
        // CHECKSTYLE: MagicNumber OFF
        // rule disabled because these numbers are not supposed to have any meaning and are only for testing purpose
        for (int i = 0; i < 400; i++) {
            doubleMelee.updateState(1);
        }
        // CHECKSTYLE: MagicNumber ON
        assertTrue(this.player.getPosition()
            .distance(doubleMelee.getPosition()) <= 1);
    }

    @Test
    void testTripleMelee() {
        final Enemy tripleMelee = new Melee(new Melee(this.melee));
        tripleMelee.updateState(1000);
        tripleMelee.onPlayerCollision(this.player);
        // CHECKSTYLE: MagicNumber OFF
        // rule disabled because these numbers are not supposed to have any meaning and are only for testing purpose
        assertEquals(this.player.getHealth(), PLAYER_HP - 3);
        tripleMelee.updateState(1000);
        tripleMelee.onPlayerCollision(this.player);
        assertEquals(this.player.getHealth(), PLAYER_HP - 6);
        tripleMelee.updateState(1000);
        tripleMelee.onPlayerCollision(this.player);
        assertEquals(this.player.getHealth(), PLAYER_HP - 9);
        // CHECKSTYLE: MagicNumber OFF
    }

    @Test
    void testTripleMeleeMovement() {
        final Enemy tripleMelee = new Melee(new Melee(this.melee));
        tripleMelee.setPosition(
            new Point2dImpl(RoomBounds.WIDTH / 2, RoomBounds.HEIGHT / 2));
        // CHECKSTYLE: MagicNumber OFF
        // rule disabled because these numbers are not supposed to have any meaning and are only for testing purpose
        for (int i = 0; i < 400; i++) {
            tripleMelee.updateState(1);
        }
        // CHECKSTYLE: MagicNumber ON
        assertTrue(this.player.getPosition()
            .distance(tripleMelee.getPosition()) <= 1);
    }
}
