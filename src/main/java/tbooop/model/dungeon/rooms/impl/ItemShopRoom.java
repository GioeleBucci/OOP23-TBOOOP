package tbooop.model.dungeon.rooms.impl;

import java.util.Map;

import tbooop.commons.Point2ds;
import tbooop.model.dungeon.rooms.api.Door;
import tbooop.model.dungeon.rooms.api.Room;

/**
 * Class rapresenting item shop room
 * in the game dungeon.
 * Here the player will be able to buy
 * items using coins. Player will be
 * allowed to enter this room using a
 * special key.
 */
public class ItemShopRoom implements Room {

    /** {@inheritDoc} */
    @Override
    public Map<Point2ds, Door> getDoorMap() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDoorMap'");
    }

    /** {@inheritDoc} */
    @Override
    public boolean isExplored() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExplored'");
    }

    /** {@inheritDoc} */
    @Override
    public boolean isSpecial() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isSpecial'");
    }

    /** {@inheritDoc} */
    @Override
    public void addDoor(final Point2ds direction, final Door door) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addDoor'");
    }

    /** {@inheritDoc} */
    @Override
    public void setExplored() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setExplored'");
    }
}
