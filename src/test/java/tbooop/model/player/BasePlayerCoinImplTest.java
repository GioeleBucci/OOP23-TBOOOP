package tbooop.model.player;

import org.junit.jupiter.api.Test;

import tbooop.model.player.impl.BasePlayerCoinImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;

class BasePlayerCoinImplTest {

    private static final int TEST_COIN = 50;
    private static final int MAX_COIN = 200;
    private static BasePlayerCoinImpl playerCoin;

    @BeforeAll
    static void setUp() {
        playerCoin = new BasePlayerCoinImpl();
    }

    @Test
    void addCoins() {
        final int initialCoinCount = playerCoin.getCoins();
        final int coinsToAdd = TEST_COIN;
        playerCoin.addCoins(coinsToAdd);
        assertEquals(initialCoinCount + coinsToAdd, playerCoin.getCoins());
    }

    @Test
    void addOverMax() {
        final int initialCoinCount = playerCoin.getCoins();
        final int coinsToAdd = MAX_COIN + TEST_COIN;
        playerCoin.addCoins(coinsToAdd);
        assertEquals(initialCoinCount, playerCoin.getCoins());
    }
}
