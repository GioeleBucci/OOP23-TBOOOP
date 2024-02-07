package tbooop.model.dungeon.rooms.impl;

import tbooop.model.dungeon.rooms.api.RegularRoom;

/**
 * A trapdoor room is an empty room with a trapdoor in the middle that takes the
 * player to the next level.
 */
public class TrapdoorRoom extends RegularRoom {

    /**
     * Creates a new TrapdoorRoom, an empty room with a trapdoor in the middle.
     */
    public TrapdoorRoom() {
        getGameObjects().add(new TrapdoorImpl());
    }

}
