package tbooop.model.player.impl;

import tbooop.model.player.api.key.PlayerKey;

/**
 * Represents the key of a player.
 */
public class PlayerKeyImpl implements PlayerKey {

    private static final int INITIAL_KEYS = 2;
    private int keys;

    /**
     * Create a new instance of a PlayerKey.
     */
    public PlayerKeyImpl() {
        this.keys = INITIAL_KEYS;
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasKey() {
        return this.keys > 0;
    }

    /** {@inheritDoc} */
    @Override
    public void useKey() {
        if (this.keys > 0) {
            this.keys = this.keys - 1;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void pickupKey() {
        this.keys = this.keys + 1;
    }

    /** {@inheritDoc} */
    @Override
    public int getKey() {
        return this.keys;
    }
}
