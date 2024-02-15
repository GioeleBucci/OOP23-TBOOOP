package tbooop.model.player.impl;

public class PlayerCoinImpl {

    private static final int INITIAL_COINS = 0;
    private int coins;

    public PlayerCoinImpl() {
        this.coins = INITIAL_COINS;
    }

    public int getCoins() {
        return this.coins;
    }

    public void addCoins(final int coins) {
        this.coins += coins;
    }

    public void consumeCoins(final int coins) {
        this.coins -= coins;
    }
}
