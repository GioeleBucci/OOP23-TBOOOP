package tbooop.model.pickupables.pickups.impl;

import java.util.List;
import java.util.Random;
import java.util.Arrays;

import tbooop.model.pickupables.pickups.api.Pickup;

/**
 * Class implementing logic for pickups
 * generations in the game.
 * A pickup will spawn when a standard
 * room is visited and the player
 * defeats every enemy.
 */
public class PickupLogic {

    private static final int PICKUPS_NUMBER = 4;
    private final Random random = new Random();
    private final PickupFactoryImpl factory = new PickupFactoryImpl();

    /**
     * Return a random Pickupable.
     * 
     * @return a random Pickupable
     */
    public Pickup getRandomPickup() {
        final List<Pickup> list = Arrays.asList(factory.bill(), factory.coin(),
                                                 factory.heart(), factory.key());
        return list.get(random.nextInt(PICKUPS_NUMBER));
    }
}
