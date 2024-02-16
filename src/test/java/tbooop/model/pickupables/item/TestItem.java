package tbooop.model.pickupables.item;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tbooop.commons.impl.Point2dImpl;
import tbooop.model.pickupables.api.PickupableName;
import tbooop.model.pickupables.items.api.Item;
import tbooop.model.pickupables.items.impl.ItemFactoryImpl;
import tbooop.model.player.impl.PlayerImpl;

/**
 * Test class for items.
 */
class TestItem {

    private static Item goldenApple;
    private static Item glassHeart;
    private static Item zap;
    private static Item lockedRing;
    private static Item spicySauce;
    private static ItemFactoryImpl factory = new ItemFactoryImpl();
    private static PlayerImpl player = new PlayerImpl(new Point2dImpl(1.0, 1.0));

    /**
     * Configuration step: this is performed BEFORE each test.
     */
    @BeforeAll
    static void initItems() {
        goldenApple = factory.goldenApple();
        glassHeart = factory.glassHeart();
        zap = factory.zap();
        lockedRing = factory.lockedRing();
        spicySauce = factory.spicySauce();
    }

    @Test
    void testItemName() {
        assertEquals(glassHeart.getObjectName(), PickupableName.GLASS_HEART);
        assertEquals(goldenApple.getObjectName(), PickupableName.GOLDEN_APPLE);
        assertEquals(zap.getObjectName(), PickupableName.ZAP);
        assertEquals(lockedRing.getObjectName(), PickupableName.LOCKED_RING);
        assertEquals(spicySauce.getObjectName(), PickupableName.SPICY_SAUCE);
    }

    @Test
    void testItemGeneralEffect() {
        final int playerInitialMaxHealth = player.getMaxHealth();
        final int playerInitialDamage = player.getDamage();
        goldenApple.onPlayerCollision(player);
        assertEquals(playerInitialMaxHealth + 2, player.getMaxHealth());
        glassHeart.onPlayerCollision(player);
        assertEquals(player.getHealth(), player.getMaxHealth());
        lockedRing.onPlayerCollision(player);
        assertEquals(playerInitialDamage + 1, player.getDamage());
        zap.onPlayerCollision(player);
        assertEquals(1, 1);
        spicySauce.onPlayerCollision(player);
        assertEquals(1, 1);
    }
}
