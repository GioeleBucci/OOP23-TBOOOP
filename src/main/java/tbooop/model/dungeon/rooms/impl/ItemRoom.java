package tbooop.model.dungeon.rooms.impl;

import tbooop.model.dungeon.rooms.api.SpecialRoom;
import tbooop.model.pickupables.items.impl.ItemRoomLogic;

/**
 * An item room is a special room that contains an item.
 */
public class ItemRoom extends SpecialRoom {

    /**
     * Create a new item room with a random item inside it.
     */
    public ItemRoom() {
        addGameObject(new ItemRoomLogic().getRandomItem());
    }
}
