package tbooop.model.player.api.coin;

/**
 * This class represents an abstract implementation of the Coins interface.
 * It provides common functionality for managing coins.
 */
public abstract class AbstractCoin implements Coins {

    private int coins;

    /**
     * Constructs a new AbstractCoin object with the specified number of coins.
     *
     * @param coins the initial number of coins
     */
    public AbstractCoin(final int coins) {
        this.coins = coins;
    }

    /**
     * Returns the current number of coins.
     *
     * @return the number of coins
     */
    @Override
    public int getCoins() {
        return coins;
    }

    /**
     * Adds the specified number of coins to the current number of coins.
     *
     * @param coins the number of coins to add
     */
    @Override
    public void addCoins(final int coins) {
        this.coins += coins;
    }

    /**
     * Consumes the specified number of coins from the current number of coins.
     *
     * @param coins the number of coins to consume
     */
    @Override
    public void consumeCoins(final int coins) {
        this.coins -= coins;
    }
}
