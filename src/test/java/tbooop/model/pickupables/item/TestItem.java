package tbooop.model.pickupables.item;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tbooop.commons.RoomBounds;
import tbooop.model.core.api.GameTag;
import tbooop.model.pickupables.PickupableName;
import tbooop.model.pickupables.items.api.Item;
import tbooop.model.pickupables.items.api.PickupableStatus;
import tbooop.model.pickupables.items.impl.GoldenApple;

/**
 * Test class for items.
 */
public class TestItem {

    private static Item goldenApple;

    /**
     * Configuration step: this is performed BEFORE each test.
     */
    @BeforeAll
    static void initRandomItem() {
        goldenApple = new GoldenApple(RoomBounds.CENTER, 2.0, GameTag.PICKUP);
    }

    @Test
    void checkItemStatus() {
        assertEquals(goldenApple.getStatus(), PickupableStatus.NORMAL);
        goldenApple.setInShop();
        assertEquals(goldenApple.getStatus(), PickupableStatus.SPECIAL);
    }

    @Test
    void checkItemName() {
        assertEquals(goldenApple.getObjectName(), PickupableName.GOLDEN_APPLE);
    }
}
