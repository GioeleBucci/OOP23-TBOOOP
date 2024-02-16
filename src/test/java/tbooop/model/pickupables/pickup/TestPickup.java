package tbooop.model.pickupables.pickup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tbooop.commons.api.RoomBounds;
import tbooop.commons.impl.Point2dImpl;
import tbooop.model.pickupables.api.PickupableName;
import tbooop.model.pickupables.pickups.api.Pickup;
import tbooop.model.pickupables.pickups.impl.PickupFactoryImpl;
import tbooop.model.player.impl.PlayerImpl;

/**Test class for pickups. */
class TestPickup {

    private static Pickup coin;
    private static Pickup key;
    private static Pickup bill;
    private static Pickup heart;
    private static PickupFactoryImpl factory = new PickupFactoryImpl();
    private static PlayerImpl player = new PlayerImpl(new Point2dImpl(1.0, 1.0));

    /**
     * Configuration step: this is performed BEFORE each test.
     */
    @BeforeAll
    static void initPickups() {
        coin = factory.coin();
        key = factory.key();
        bill = factory.bill();
        heart = factory.heart();
    }

    @Test
    void testPickupName() {
        assertEquals(bill.getObjectName(), PickupableName.BILL);
        assertEquals(coin.getObjectName(), PickupableName.COIN);
        assertEquals(key.getObjectName(), PickupableName.KEY);
        assertEquals(heart.getObjectName(), PickupableName.HEART);
    }

    @Test
    void testPickupEffect() {
        final int initialPlayerKeys = player.getKey();
        int initialePlayerCoins = player.getCoins();
        final int initialPlayerHealth = player.getHealth();
        bill.onPlayerCollision(player);
        assertEquals(initialePlayerCoins + 10, player.getCoins());
        initialePlayerCoins = player.getCoins();
        coin.onPlayerCollision(player);
        assertEquals(initialePlayerCoins + 1, player.getCoins());
        key.onPlayerCollision(player);
        assertEquals(initialPlayerKeys + 1, player.getKey());
        heart.onPlayerCollision(player);
        if (initialPlayerHealth == player.getMaxHealth()) {
            assertEquals(initialPlayerHealth, player.getHealth());
        } else {
            assertEquals(initialPlayerHealth + 1, player.getHealth());
        }
    }

    /**
     * Test one casual pickup is enough
     * cause they all share the same position.
     */
    @Test
    void testPickupSpawn() {
        assertEquals(coin.getPosition(), RoomBounds.CENTER);
    }
}
