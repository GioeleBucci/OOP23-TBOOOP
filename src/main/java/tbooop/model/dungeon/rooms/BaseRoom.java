package tbooop.model.dungeon.rooms;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import tbooop.commons.Directions;

/**
 * Base for all Rooms implementations
 * A room can have up to 4 doors and a series of rooms
 * create a floor.
 * 
 * @see {@link tbooop.model.dungeon.Floor Floor}
 */
public abstract class BaseRoom implements Room {

    private final Set<Directions> doorSet = new HashSet<>();
    private boolean isExplored;

    /** {@inheritDoc} */
    @Override
    public void setDoorSet(final Collection<Directions> directions) {
        doorSet.addAll(directions);
    }

    /** {@inheritDoc} */
    @Override
    public Set<Directions> getDoorSet() {
        return Collections.unmodifiableSet(doorSet);
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
