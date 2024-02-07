package tbooop.model.pickupables.pickups.impl;

import java.util.List;
import java.util.Random;
import java.util.Arrays;
import tbooop.commons.RoomBounds;
import tbooop.model.core.api.GameTag;
import tbooop.model.pickupables.pickups.api.Pickup;

/**
 * Class implementing logic for pickups
 * generations in the game.
 * A pickup will spawn when a standard
 * room is visited and the player
 * defeats every enemy.
 */
public class PickupLogic {

    private static final int PICKUPS_NUMBER = 3;
    private final List<Pickup> list = Arrays.asList(new Coin(RoomBounds.CENTER, 2.0, GameTag.PICKUP), 
                                                new Heart(RoomBounds.CENTER, 2.0, GameTag.PICKUP),
                                                new Key(RoomBounds.CENTER, 2.0, GameTag.PICKUP),
                                                new Bill(RoomBounds.CENTER, 2.0, GameTag.PICKUP));
    private final Random random = new Random();

    /**
     * Return a random Pickupable.
     * 
     * @return a random Pickupable
     */
    public Pickup getRandomPickup() {
        return list.get(random.nextInt(PICKUPS_NUMBER));
    }
}
