package tbooop.model.player.api.key;

public abstract class AbstractKey implements Key {
    
    private int keys;

    AbstractKey(final int initialKeys) {
        this.keys = initialKeys;
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
