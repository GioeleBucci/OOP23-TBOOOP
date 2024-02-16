package tbooop.model.player.impl;

import tbooop.model.player.api.key.AbstractKey;
import tbooop.model.player.api.key.PlayerKey;

/**
 * Represents the key of a player.
 */
public class PlayerKeyImpl extends AbstractKey implements PlayerKey {

    private static final int INITIAL_KEYS = 2;

    /**
     * Create a new instance of a PlayerKey.
     */
    public PlayerKeyImpl() {
        super(INITIAL_KEYS);
    }

    /** {@inheritDoc} */
    @Override
    public void pickupKey() {
        if (getKey() < 20) {
            super.pickupKey();
        }
    }
}
