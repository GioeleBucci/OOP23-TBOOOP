package tbooop.model.dungeon.rooms.impl;

import tbooop.model.dungeon.rooms.api.RegularRoom;

/**
 * The first room of the dungeon.
 */
public class StartingRoom extends RegularRoom {

    /** {@inheritDoc} */
    @Override
    public boolean isFirstRoom() {
        return true;
    }

}
