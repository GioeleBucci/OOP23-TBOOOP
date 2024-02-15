package tbooop.model.player.api;

import java.util.Optional;

import tbooop.commons.api.CardinalDirection;
import tbooop.commons.api.Health;
import tbooop.commons.api.Point2d;
import tbooop.commons.impl.Point2dImpl;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.movable.AbstractEntity;
import tbooop.model.player.impl.PlayerCoinImpl;

/**
 * A Player is a game object that can move on a 2D space,
 * a player can interact with an enemy trying to kill him or can collect
 * objects on the map.
 */
public abstract class AbstractPlayer extends AbstractEntity implements Player {

    private static final int PLAYER_COLLIDER_RADIUS = 15;
    private static final int PLAYER_COINS = 40;
    private static final int PLAYER_INITIAL_DAMAGE = 1;
    private final PlayerKey keys;
    private final PlayerCoin coins;
    private int coin;
    private int damage;

    /**
     * Create a new istance of a Entity.
     * @param position starting position
     * @param health the entity's health
     * @param velocity  it is the Entity velocity
     * @param keys the player's keys
     */
    protected AbstractPlayer(final Point2d position, final Health health, final double velocity, final PlayerKey keys) {
        super(position, health, velocity, GameTag.PLAYER, PLAYER_COLLIDER_RADIUS);
        this.coin = PLAYER_COINS;
        this.coins = new PlayerCoinImpl();
        this.damage = PLAYER_INITIAL_DAMAGE;
        this.keys = keys;
    }

    /** {@inheritDoc} */
    @Override
    public Optional<CardinalDirection> getPoint2ds() {
        for (final var point2d : CardinalDirection.getAll()) {
            if (getDirection().toP2d().equals(point2d.toP2d())) {
                return Optional.of(point2d);
            }
        }

        if (!getDirection().toP2d().equals(Point2dImpl.ZERO)) {
            throw new IllegalArgumentException();
        }
        return Optional.empty();
    }

    /** {@inheritDoc} */
    @Override
    public int getCoin() {
        return this.coins.getCoin();
    }

    /** {@inheritDoc} */
    @Override
    public void addCoins(final int coins) {
        this.coins.addCoins(coins);
    }

    /** {@inheritDoc} */
    @Override
    public void consumeCoins(final int coins) {
        this.coins.consumeCoins(coins);
    }

    /** {@inheritDoc} */
    @Override
    public void setCoin(final int amount) {
        this.coin = this.coin + amount;
    }

    /** {@inheritDoc} */
    @Override
    public void setKeys(final int keys) {
        this.keys.setKeys(keys);
    }

    /** {@inheritDoc} */
    @Override
    public int getKey() {
        return this.keys.getKey();
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasKey() {
        return this.keys.hasKey();
    }

    /**
     * This method increases the amount of damage the Player can do.
     * @return the amount of damage the player can do.
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * This method increases the amount of damage the Player can do.
     * @param damage it's the amount of damege to increase.
     */
    public void setDamage(final int damage) {
        this.damage = damage;
    }

    /** {@inheritDoc} */
    @Override
    public void pickupKeys() {
        setKeys(getKey() + 1);
    }

    /** {@inheritDoc} */
    @Override
    public void useKey() {
        if (hasKey()) {
            setKeys(getKey() - 1);
        }
    }
}
