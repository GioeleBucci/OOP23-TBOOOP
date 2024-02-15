package tbooop.model.player.impl;

import tbooop.model.player.api.PlayerCoin;

/**
 * Implementation of the PlayerCoin interface.
 * Represents a player's coins in a game.
 */
public class PlayerCoinImpl implements PlayerCoin {

    private static final int INITIAL_COINS = 20;
    private int coins;

    /**
     * Create a new instance of a PlayerCoin.
     */
    public PlayerCoinImpl() {
        this.coins = INITIAL_COINS;
    }

    /** {@inheritDoc} */
    @Override
    public int getCoins() {
        return this.coins;
    }

    /** {@inheritDoc} */
    @Override
    public void addCoins(final int coins) {
        this.coins += coins;
    }

    /** {@inheritDoc} */
    @Override
    public void consumeCoins(final int coins) {
        this.coins -= coins;
    }
}
