package tbooop.model.pickupables.items.impl;

import java.util.HashMap;
import java.util.Map;

import tbooop.commons.Point2dImpl;
import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.pickupables.items.api.Item;
import tbooop.commons.RoomBounds;

/**
 * Class implementing item's handling logic
 * in the item shop room.
 */
public class ItemShopLogic {

    private static final double ITEM_COLLIDER_RADIUS = 1.0;
    private static final int BASE_WIDTH_DIVISION = 2;
    private static final int BASE_HEIGHT_DIVISION = 3;
    private final Item goldenHeart = new GlassHeart(getItemPosition(1), ITEM_COLLIDER_RADIUS, GameTag.PICKUP);
    private final Item belt = new Zap(getItemPosition(2), ITEM_COLLIDER_RADIUS, GameTag.PICKUP);
    private final Item fireMind = new SpicySauce(getItemPosition(3), ITEM_COLLIDER_RADIUS, GameTag.PICKUP);
    private final Item ironBar = new LockedRing(getItemPosition(4), ITEM_COLLIDER_RADIUS, GameTag.PICKUP);
    private final Item goldenApple = new GoldenApple(getItemPosition(5), ITEM_COLLIDER_RADIUS, GameTag.PICKUP);

    /**
     * Returns a map from an item to its
     * price.
     * It will be used to shown the item and
     * relative price in the item shop.
     * 
     * @return a map from Item to Item
     */
    public Map<Item, Integer> getItemPool() {
        final Map<Item, Integer> itemsMap = new HashMap<>();
        goldenHeart.setInShop();
        itemsMap.put(goldenHeart, goldenHeart.getPrice());
        belt.setInShop();
        itemsMap.put(belt, belt.getPrice());
        fireMind.setInShop();
        itemsMap.put(fireMind, fireMind.getPrice());
        ironBar.setInShop();
        itemsMap.put(ironBar, ironBar.getPrice());
        goldenApple.setInShop();
        itemsMap.put(goldenApple, goldenApple.getPrice());

        return itemsMap;
    }

    /**
     * Calculate and return a Point2d
     * for item's location.
     * 
     * @param itemNumber
     * @return a Point2d
     */
    private Point2d getItemPosition(final Integer itemNumber) {
        final double widthDivision = RoomBounds.WIDTH / 8;
        final double heightDivision = RoomBounds.HEIGHT / 3;
        switch (itemNumber) {
            case 1: return new Point2dImpl(BASE_WIDTH_DIVISION * widthDivision, heightDivision);
            case 2: return new Point2dImpl((BASE_WIDTH_DIVISION + 2) * widthDivision, heightDivision);
            case 3: return new Point2dImpl((BASE_WIDTH_DIVISION + 4) * widthDivision, heightDivision);
            case 4: return new Point2dImpl(BASE_HEIGHT_DIVISION * widthDivision, BASE_WIDTH_DIVISION * heightDivision);
            case 5: return new Point2dImpl((BASE_HEIGHT_DIVISION + 2) * widthDivision, BASE_WIDTH_DIVISION * heightDivision);
            default: return new Point2dImpl(widthDivision, heightDivision);
        }
    }
}
