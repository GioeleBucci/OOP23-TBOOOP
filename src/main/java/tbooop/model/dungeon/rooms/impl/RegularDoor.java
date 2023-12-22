package tbooop.model.dungeon.rooms.impl;

import tbooop.model.dungeon.rooms.api.Door;
import tbooop.model.dungeon.rooms.api.RoomView;

import java.util.Objects;

/**
 * A regular door has no locks and takes to a regular room.
 */
public class RegularDoor implements Door {

    private final RoomView room;

    /**
     * Creates a regular door.
     * 
     * @param room the room this door leads to
     */
    public RegularDoor(final RoomView room) {
        this.room = Objects.requireNonNull(room);
    }

    /** {@inheritDoc} */
    @Override
    public RoomView getRoom() {
        return room;
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
