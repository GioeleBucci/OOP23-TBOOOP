package tbooop.model.pickupables.items.impl;

import java.util.List;

import tbooop.model.pickupables.items.api.Item;

/**
 * Class implementing item's room logic
 * in the game.
 * It will be used to get a random item
 * to spawn there.
 */
public class ItemRoomLogic {
    private static final double SPICYSAUCE_PROB = 0.45;
    private static final double ZAP_PROB = 0.70;
    private static final double GLASSHEART_PROB = 0.85;
    private static final double LOCKEDRING_PROB = 0.95;
    private final ItemFactoryImpl factory = new ItemFactoryImpl();
    private final List<Item> itemsList = List.of(factory.spicySauce(), factory.zap(),
                                                factory.glassHeart(), factory.lockedRing(),
                                                factory.goldenApple());

    /**
     * Compute the chances every item has
     * to spawn.
     * 
     * @param randomNumber
     * @return an Integer from 0 to
     * the max number of Item.
     */
    private int getChanceToSpawn(final double randomNumber) {
        if (randomNumber <= SPICYSAUCE_PROB) {
            return 0;
        } else if (randomNumber > SPICYSAUCE_PROB && randomNumber <= ZAP_PROB) {
            return 1;
        } else if (randomNumber > ZAP_PROB && randomNumber <= GLASSHEART_PROB) {
            return 2;
        } else if (randomNumber > GLASSHEART_PROB && randomNumber <= LOCKEDRING_PROB) {
            return 3;
        } else {
            return 4;
        }
    }

    /**
     * Return a random Item to spawn.
     * Every item has a certain chance
     * to be chosen.
     * 
     * @return a random Item.
     */
    public Item getRandomItem() {
        final double randomNumber = Math.random();
        final int itemIndex = getChanceToSpawn(randomNumber);
        return itemsList.get(itemIndex);
    }
}
