package tbooop.model.pickupables.items.impl;

import java.util.List;
import tbooop.commons.Point2dImpl;
import tbooop.commons.RoomBounds;
import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.pickupables.items.api.Item;

/**
 * Class implementing item's room logic
 * in the game.
 * It will be used to get a random item
 * to spawn there.
 */
public class ItemRoomLogic {
    private final Point2d location = new Point2dImpl(RoomBounds.WIDTH / 2, RoomBounds.HEIGHT / 2);
    private static final double ITEM_COLLIDER_RADIUS = 1.0;
    private static final double GOLDENHEART_PROB = 0.1;
    private static final double BELT_PROB = 0.2;
    private static final double IRONBAR_PROB = 0.2;
    private static final double FIREMIND_PROB = 0.2;
    private static final double GOLDENAPPLE_PROB = 0.3;
    private final List<Item> itemsList = List.of(new GlassHeart(location, ITEM_COLLIDER_RADIUS, GameTag.PICKUP),
                                                new Zap(location, ITEM_COLLIDER_RADIUS, GameTag.PICKUP),
                                                new LockedRing(location, ITEM_COLLIDER_RADIUS, GameTag.PICKUP),
                                                new SpicySauce(location, ITEM_COLLIDER_RADIUS, GameTag.PICKUP),
                                                new GoldenApple(location, ITEM_COLLIDER_RADIUS, GameTag.PICKUP));

    /**
     * Compute the chances every item has
     * to spawn.
     * 
     * @param randomNumber
     * @return an Integer from 0 to
     * the max number of Item.
     */
    private int getChanceToSpawn(final double randomNumber) {
        if (randomNumber <= GOLDENHEART_PROB) {
            return 0;
        } else if (randomNumber > GOLDENHEART_PROB && randomNumber <= (GOLDENHEART_PROB + BELT_PROB)) {
            return 1;
        } else if (randomNumber > IRONBAR_PROB && randomNumber <= (IRONBAR_PROB + FIREMIND_PROB)) {
            return 2;
        } else if (randomNumber > (GOLDENAPPLE_PROB + GOLDENHEART_PROB) && randomNumber <= (IRONBAR_PROB * 3)) {
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
