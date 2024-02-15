package tbooop.model.player.api;


/**
 * This interface represents a player's coins in a game.
 */
public interface PlayerCoin {

    /**
     * Returns the number of coins the player has.
     *
     * @return the number of coins
     */
    int getCoins();

    /**
     * Adds the specified number of coins to the player's total.
     *
     * @param coins the number of coins to add
     */
    void addCoins(int coins);

    /**
     * Consumes the specified number of coins from the player's total.
     *
     * @param coins the number of coins to consume
     */
    void consumeCoins(int coins);

}
