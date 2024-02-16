package tbooop.model.player.api.coin;

public abstract class AbstractCoin implements Coins {

    private int coins;

    public AbstractCoin(int coins) {
        this.coins = coins;
    }

    @Override
    public int getCoins() {
        return coins;
    }

    @Override
    public void addCoins(int coins) {
        this.coins += coins;
    }

    @Override
    public void consumeCoins(int coins) {
        this.coins -= coins;
    }
}
