package tbooop.model.pickupables.item;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tbooop.model.pickupables.api.PickupableName;
import tbooop.model.pickupables.items.api.Item;
import tbooop.model.pickupables.items.api.PickupableStatus;
import tbooop.model.pickupables.items.impl.ItemFactoryImpl;

/**
 * Test class for items.
 */
class TestItem {

    private static Item goldenApple;
    private static ItemFactoryImpl factory = new ItemFactoryImpl();

    /**
     * Configuration step: this is performed BEFORE each test.
     */
    @BeforeAll
    static void initRandomItem() {
        goldenApple = factory.goldenApple();
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
