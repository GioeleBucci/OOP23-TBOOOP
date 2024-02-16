package tbooop.model.player.api.key;


/**
 * An abstract implementation of the Key interface that provides common functionality for key objects.
 */
public abstract class AbstractKey implements Key {

    private int keys;

    /**
     * Constructs a new AbstractKey object with the specified initial number of keys.
     *
     * @param initialKeys the initial number of keys
     */
    public AbstractKey(final int initialKeys) {
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
