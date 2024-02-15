package tbooop.model.pickupables.items.impl;

import tbooop.commons.api.RoomBounds;
import tbooop.model.core.api.GameTag;
import tbooop.model.pickupables.items.api.Item;
import tbooop.model.pickupables.items.api.ItemFactory;

/**
 * Implements a factory of item where each method
 * creates and returns a particular type of item.
 */
public class ItemFactoryImpl implements ItemFactory {
    /** {@inheritDoc} */
    @Override
    public Item glassHeart() {
        return new GlassHeart(RoomBounds.CENTER, 1.0, GameTag.PICKUP);
    }

    /** {@inheritDoc} */
    @Override
    public Item goldenApple() {
        return new GoldenApple(RoomBounds.CENTER, 1.0, GameTag.PICKUP);
    }

    /** {@inheritDoc} */
    @Override
    public Item lockedRing() {
        return new LockedRing(RoomBounds.CENTER, 1.0, GameTag.PICKUP);
    }

    /** {@inheritDoc} */
    @Override
    public Item spicySauce() {
        return new SpicySauce(RoomBounds.CENTER, 1.0, GameTag.PICKUP);
    }

    /** {@inheritDoc} */
    @Override
    public Item zap() {
        return new Zap(RoomBounds.CENTER, 1.0, GameTag.PICKUP);
    }
}
