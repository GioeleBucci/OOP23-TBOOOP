package tbooop.model.player.impl;

import tbooop.model.player.api.PlayerKey;

/**
 * Represents the key of a player.
 */
public class PlayerKeyImpl implements PlayerKey {

    private int keys;

    /**
     * Create a new instance of a PlayerKey.
     */
    public PlayerKeyImpl() {
        this.keys = 0;
    }

    @Override
    public boolean hasKey() {
        return this.keys > 0;
    }

    @Override
    public void useKey() {
        if (this.keys > 0) {
            this.keys = this.keys - 1;
        }
    }

    @Override
    public void setKeys(final int keys) {
        this.keys = keys;
    }

    @Override
    public void pickupKeys() {
        this.keys = this.keys + 1;
    }

    @Override
    public int getKey() {
        return this.keys;
    }
}
