package tbooop.model.player.impl;

public class PlayerCoinImpl implements PlayerCoin {

    private static final int INITIAL_COINS = 0;
    private int coins;

    public PlayerCoinImpl() {
        this.coins = INITIAL_COINS;
    }

    @Override
    public int getCoins() {
        return this.coins;
    }

    @Override
    public void addCoins(final int coins) {
        this.coins += coins;
    }

    @Override
    public void consumeCoins(final int coins) {
        this.coins -= coins;
    }
}
