package tbooop.model.player.impl;

import tbooop.model.player.api.coin.AbstractCoin;
import tbooop.model.player.api.coin.PlayerCoin;

/**
 * Implementation of the PlayerCoin interface.
 * Represents a player's coins in a game.
 */
public class PlayerCoinImpl extends AbstractCoin implements PlayerCoin {

    private static final int MAX_COIN = 200;
    private static final int INITIAL_COINS = 20;

    /**
     * Create a new instance of a PlayerCoin.
     */
    public PlayerCoinImpl() {
        super(INITIAL_COINS);
    }

    /** {@inheritDoc} */
    @Override
    public void addCoins(final int coins) {
        if (coins < MAX_COIN) {
            super.addCoins(coins);
        }
    }
}
