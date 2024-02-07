package tbooop.model.dungeon.rooms.impl;

import java.util.Map;

import tbooop.model.dungeon.rooms.api.SpecialRoom;
import tbooop.model.pickupables.items.api.Item;
import tbooop.model.pickupables.items.impl.ItemShopLogic;

/**
 * Class rapresenting item shop room
 * in the game dungeon.
 * Here the player will be able to buy
 * items using coins. Player will be
 * allowed to enter this room using a
 * special key.
 */
public class ItemShopRoom extends SpecialRoom {

    private final ItemShopLogic shopLogic = new ItemShopLogic();

    /**Instantiates items inside the shop room.*/
    public ItemShopRoom() {
        for (final var i : getItemMap().keySet()) {
            addGameObject(i);
        }
    }
    /**
     * Get the items map, from Item to its price.
     *
     * @return a map from Item to Integer
     */
    private Map<Item, Integer> getItemMap() {
        return shopLogic.getItemPool();
    }
}
