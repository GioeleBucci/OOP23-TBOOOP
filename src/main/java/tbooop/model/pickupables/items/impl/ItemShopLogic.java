package tbooop.model.pickupables.items.impl;

import java.util.HashMap;
import java.util.Map;

import tbooop.commons.Point2dImpl;
import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.pickupables.items.api.Items;

/**
 * Class implementing item's handling logic
 * in the item shop room.
 */
public class ItemShopLogic {

    private final Point2d location = new Point2dImpl(1.0, 1.0);
    private static final double ITEM_COLLIDER_RADIUS = 1.0;
    private final Items goldenHeart = new GoldenHeart(location, ITEM_COLLIDER_RADIUS, GameTag.PICKUP);

    /**
     * Returns a map from an item to its
     * price label.
     * It will be used to shown the item and
     * relative price in the item shop.
     * 
     * @return a map from Items to Items
     */
    public Map<Items, Integer> getItemsPool() {
        final Map<Items, Integer> itemsMap = new HashMap<>();
        itemsMap.put(goldenHeart, goldenHeart.getPrice());

        return itemsMap;
    }
}
