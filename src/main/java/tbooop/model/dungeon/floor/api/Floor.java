package tbooop.model.dungeon.floor.api;

import tbooop.model.dungeon.rooms.api.Room;

/** Interface for floor. */
public interface Floor {

    /**
     * Returns the starting room of this floor.
     * 
     * @return the starting room
     */
    Room getStaringRoom();

}
