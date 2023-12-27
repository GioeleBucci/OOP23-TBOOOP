package tbooop.model.dungeon.rooms.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import tbooop.commons.Point2ds;
import tbooop.model.dungeon.rooms.api.Door;
import tbooop.model.dungeon.rooms.api.Room;

/**
 * Base for all Rooms implementations
 * A room can have up to 4 doors and a series of rooms
 * create a floor.
 * 
 * @see {@link tbooop.model.dungeon.Floor Floor}
 */
public abstract class BaseRoom implements Room {

    private final Map<Point2ds, Door> doorMap = new HashMap<>();
    private boolean isExplored;

    /** {@inheritDoc} */
    @Override
    public void addDoor(final Point2ds direction, final Door door) {
        doorMap.put(direction, door);
    }

    /** {@inheritDoc} */
    @Override
    public Map<Point2ds, Door> getDoorMap() {
        return Collections.unmodifiableMap(doorMap);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isExplored() {
        return isExplored;
    }

    /** {@inheritDoc} */
    @Override
    public void setExplored() {
        this.isExplored = true;
    }
}
