package tbooop.model.pickupables.items.impl;

import java.util.HashMap;
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
    private static final int GLASS_HEART_NUM = 1;
    private static final int ZAP_NUM = 2;
    private static final int SPICY_SAUCE_NUM = 3;
    private static final int LOCKED_RING_NUM = 4;
    private static final int GOLDEN_APPLE_NUM = 5;
    private final ItemFactoryImpl factory = new ItemFactoryImpl();
    private final Item glassHeart = factory.glassHeart();
    private final Item zap = factory.zap();
    private final Item spicySauce = factory.spicySauce();
    private final Item lockedRing = factory.lockedRing();
    private final Item goldenApple = factory.goldenApple();

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
        glassHeart.setInShop();
        itemsMap.put(glassHeart, glassHeart.getPrice());
        zap.setInShop();
        itemsMap.put(zap, zap.getPrice());
        spicySauce.setInShop();
        itemsMap.put(spicySauce, spicySauce.getPrice());
        lockedRing.setInShop();
        itemsMap.put(lockedRing, lockedRing.getPrice());
        goldenApple.setInShop();
        itemsMap.put(goldenApple, goldenApple.getPrice());

        return itemsMap;
    }

    /**
     * Set every item correct position in
     * the item shop.
     */
    private void setItemsPositions() {
        glassHeart.setPosition(getItemPosition(GLASS_HEART_NUM));
        zap.setPosition(getItemPosition(ZAP_NUM));
        spicySauce.setPosition(getItemPosition(SPICY_SAUCE_NUM));
        lockedRing.setPosition(getItemPosition(LOCKED_RING_NUM));
        goldenApple.setPosition(getItemPosition(GOLDEN_APPLE_NUM));
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
            case GLASS_HEART_NUM: return new Point2dImpl(BASE_WIDTH_DIVISION * widthDivision, heightDivision);
            case ZAP_NUM: return new Point2dImpl((BASE_WIDTH_DIVISION + 2) * widthDivision, heightDivision);
            case SPICY_SAUCE_NUM: return new Point2dImpl((BASE_WIDTH_DIVISION + 4) * widthDivision, heightDivision);
            case LOCKED_RING_NUM: return new Point2dImpl(BASE_HEIGHT_DIVISION * widthDivision, 
                                                        BASE_WIDTH_DIVISION * heightDivision);
            case GOLDEN_APPLE_NUM: return new Point2dImpl((BASE_HEIGHT_DIVISION + 2) * widthDivision, 
                                                        BASE_WIDTH_DIVISION * heightDivision);
            default: return new Point2dImpl(widthDivision, heightDivision);
        }
    }
}
