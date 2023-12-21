package tbooop.model.dungeon.rooms.impl;

import tbooop.model.dungeon.rooms.api.Door;
import tbooop.model.dungeon.rooms.api.Room;

import java.util.Objects;

/**
 * A regular door has no locks and takes to a regular room.
 */
public class RegularDoor implements Door {

    private final Room room;

    /**
     * Creates a regular door.
     * 
     * @param room the room this door leads to
     */
    public RegularDoor(final Room room) {
        this.room =  Objects.requireNonNull(room);
    }

    /** {@inheritDoc} */
    @Override
    public Room getRoom() {
        return Objects.requireNonNull(room, "No room has been set!");
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "RegularDoor";
    }

    /** {@inheritDoc} */
    @Override
    public boolean isSpecial() {
        return false;
    }

}
