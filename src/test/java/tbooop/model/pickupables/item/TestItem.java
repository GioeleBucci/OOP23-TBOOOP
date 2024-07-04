package tbooop.model.pickupables.item;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tbooop.commons.impl.Point2dImpl;
import tbooop.model.pickupables.api.PickupableName;
import tbooop.model.pickupables.items.api.Item;
import tbooop.model.pickupables.items.impl.ItemFactoryImpl;
import tbooop.model.pickupables.items.impl.LockedRing;
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
        assertEquals(PickupableName.GLASS_HEART, glassHeart.getObjectName());
        assertEquals(PickupableName.GOLDEN_APPLE, goldenApple.getObjectName());
        assertEquals(PickupableName.ZAP, zap.getObjectName());
        assertEquals(PickupableName.LOCKED_RING, lockedRing.getObjectName());
        assertEquals(PickupableName.SPICY_SAUCE, spicySauce.getObjectName());
    }

    @Test
    void testItemGeneralEffect() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        final int playerInitialMaxHealth = (int) player.getMaxHealth();
        final float playerInitialDamage = player.getDamage();
        final double playerInitialProjectilesVel = player.getProjectileVelocity();
        goldenApple.onPlayerCollision(player);
        assertEquals(playerInitialMaxHealth + 2, player.getMaxHealth());
        glassHeart.onPlayerCollision(player);
        assertEquals(player.getHealth(), player.getMaxHealth());
        lockedRing.onPlayerCollision(player);
        assertEquals(playerInitialDamage * LockedRing.DAMAGE_MULTIPLIER, player.getDamage());
        spicySauce.onPlayerCollision(player);
        Field field = PlayerImpl.class.getDeclaredField("PROJECTILE_VELOCITY_INCREMENT");
        field.setAccessible(true); 
        double projectileVelocityIncrement = field.getDouble(player);
        assertEquals(playerInitialProjectilesVel + projectileVelocityIncrement, player.getProjectileVelocity());
    }

    /**
     * Test one casual item is enough
     * cause they all share the same
     * behaviour when pickuped in the item
     * shop by the player.
     */
    @Test
    void testItemPrices() {
        final int playerInitialCoins = player.getCoins();
        zap.setInShop();
        zap.onPlayerCollision(player);
        if (playerInitialCoins >= zap.getPrice()) {
            assertEquals(playerInitialCoins - zap.getPrice(), player.getCoins());
        } else {
            assertEquals(playerInitialCoins, player.getCoins());
        }
    }
}
