package tbooop.model.pickupables.pickups.impl;

import tbooop.commons.api.RoomBounds;
import tbooop.model.core.api.GameTag;
import tbooop.model.pickupables.pickups.api.Pickup;
import tbooop.model.pickupables.pickups.api.PickupFactory;

/**
 * Implements a factory of pickup where each method
 * creates and returns a particular type of pickup.
 */
public class PickupFactoryImpl implements PickupFactory {

    /** {@inheritDoc} */
    @Override
    public Pickup bill() {
        return new Bill(RoomBounds.CENTER, 2.0, GameTag.PICKUP);
    }

    /** {@inheritDoc} */
    @Override
    public Pickup coin() {
        return new Coin(RoomBounds.CENTER, 2.0, GameTag.PICKUP);
    }

    /** {@inheritDoc} */
    @Override
    public Pickup heart() {
        return new Heart(RoomBounds.CENTER, 2.0, GameTag.PICKUP);
    }

    /** {@inheritDoc} */
    @Override
    public Pickup key() {
        return new Key(RoomBounds.CENTER, 2.0, GameTag.PICKUP);
    }
}
