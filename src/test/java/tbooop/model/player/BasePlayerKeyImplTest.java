package tbooop.model.player;

import org.junit.jupiter.api.Test;

import tbooop.model.player.impl.BasePlayerKeyImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;

class BasePlayerKeyImplTest {

    private static final int MAX_KEYS = 20;
    private static BasePlayerKeyImpl playerKey;

    @BeforeAll
    static void setUp() {
        playerKey = new BasePlayerKeyImpl();
    }

    @Test
    void pickupKey() {
        final int initialKeyCount = playerKey.getKey();
        playerKey.pickupKey();
        assertEquals(initialKeyCount + 1, playerKey.getKey());
    }

    @Test
    void pickupToMax() {
        final int initialKeyCount = playerKey.getKey();
        for (int i = initialKeyCount; i < MAX_KEYS; i++) {
            playerKey.pickupKey();
        }
        playerKey.pickupKey();
        assertEquals(MAX_KEYS, playerKey.getKey());
    }
}
