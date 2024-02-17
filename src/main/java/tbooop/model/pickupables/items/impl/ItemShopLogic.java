package tbooop.model.pickupables.items.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tbooop.commons.api.Point2d;
import tbooop.commons.api.RoomBounds;
import tbooop.commons.impl.Point2dImpl;
import tbooop.model.pickupables.items.api.Item;

/**
 * Class implementing item's handling logic
 * in the item shop room.
 */
public class ItemShopLogic {

    private static final int BASE_WIDTH_DIVISION = 2;
    private static final int BASE_HEIGHT_DIVISION = 3;
    private final ItemFactoryImpl factory = new ItemFactoryImpl();
    private final Item glassHeart = factory.glassHeart();
    private final Item zap = factory.zap();
    private final Item spicySauce = factory.spicySauce();
    private final Item lockedRing = factory.lockedRing();
    private final Item goldenApple = factory.goldenApple();
    private final List<Item> itemsList = Arrays.asList(glassHeart, zap,
                                                      spicySauce, lockedRing,
                                                      goldenApple);

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
        setItemsPositions();
        itemsList.forEach(item -> item.setInShop());
        itemsList.forEach(item -> itemsMap.put(item, item.getPrice()));
        return itemsMap;
    }

    /**
     * Set every item correct position in
     * the item shop.
     */
    private void setItemsPositions() {
        itemsList.forEach(item -> item.setPosition(getItemPosition(itemsList.indexOf(item))));
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
            case 0: return new Point2dImpl(BASE_WIDTH_DIVISION * widthDivision, heightDivision);
            case 1: return new Point2dImpl((BASE_WIDTH_DIVISION + 2) * widthDivision, heightDivision);
            case 2: return new Point2dImpl((BASE_WIDTH_DIVISION + 4) * widthDivision, heightDivision);
            case 3: return new Point2dImpl(BASE_HEIGHT_DIVISION * widthDivision, 
                                                        BASE_WIDTH_DIVISION * heightDivision);
            case 4: return new Point2dImpl((BASE_HEIGHT_DIVISION + 2) * widthDivision, 
                                                        BASE_WIDTH_DIVISION * heightDivision);
            default: return new Point2dImpl(widthDivision, heightDivision);
        }
    }
}
