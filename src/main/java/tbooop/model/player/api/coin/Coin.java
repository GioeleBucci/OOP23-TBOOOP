package tbooop.model.player.api.coin;


/**
 * The Coin interface represents a collection of coins.
 * It provides methods to get the number of coins, add coins to the collection, and consume coins from the collection.
 */
public interface Coin {
    /**
     * Returns the number of coins in the collection.
     *
     * @return the number of coins
     */
    int getCoins();

    /**
     * Adds the specified number of coins to the collection.
     *
     * @param coins the number of coins to add
     */
    void addCoins(int coins);

    /**
     * Consumes the specified number of coins from the collection.
     *
     * @param coins the number of coins to consume
     */
    void consumeCoins(int coins);
}
