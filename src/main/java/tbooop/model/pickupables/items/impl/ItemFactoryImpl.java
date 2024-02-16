package tbooop.model.pickupables.items.impl;

import tbooop.commons.api.RoomBounds;
import tbooop.model.core.api.GameTag;
import tbooop.model.pickupables.api.PickupableName;
import tbooop.model.pickupables.items.api.Item;
import tbooop.model.pickupables.items.api.ItemFactory;
import tbooop.model.pickupables.items.api.ItemPrice;

/**
 * Implements a factory of item where each method
 * creates and returns a particular type of item.
 */
public class ItemFactoryImpl implements ItemFactory {
    /** {@inheritDoc} */
    @Override
    public Item glassHeart() {
        return new GlassHeart(RoomBounds.CENTER, 1.0, GameTag.PICKUP, 
                                ItemPrice.GLASSHEART_PRICE.getItemPrice(),
                                PickupableName.GLASS_HEART);
    }

    /** {@inheritDoc} */
    @Override
    public Item goldenApple() {
        return new GoldenApple(RoomBounds.CENTER, 1.0, GameTag.PICKUP,
                                ItemPrice.GOLDENAPPLE_PRICE.getItemPrice(),
                                PickupableName.GOLDEN_APPLE);
    }

    /** {@inheritDoc} */
    @Override
    public Item lockedRing() {
        return new LockedRing(RoomBounds.CENTER, 1.0, GameTag.PICKUP,
                                ItemPrice.LOCKEDRING_PRICE.getItemPrice(),
                                PickupableName.LOCKED_RING);
    }

    /** {@inheritDoc} */
    @Override
    public Item spicySauce() {
        return new SpicySauce(RoomBounds.CENTER, 1.0, GameTag.PICKUP,
                                ItemPrice.SPICYSAUCE_PRICE.getItemPrice(),
                                PickupableName.SPICY_SAUCE);
    }

    /** {@inheritDoc} */
    @Override
    public Item zap() {
        return new Zap(RoomBounds.CENTER, 1.0, GameTag.PICKUP,
                        ItemPrice.ZAP_PRICE.getItemPrice(),
                        PickupableName.ZAP);
    }
}
