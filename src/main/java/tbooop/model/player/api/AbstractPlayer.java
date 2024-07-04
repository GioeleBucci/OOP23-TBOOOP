package tbooop.model.player.api;

import java.util.Optional;

import tbooop.commons.api.Direction;
import tbooop.commons.api.Health;
import tbooop.commons.api.Point2d;
import tbooop.commons.impl.Point2dImpl;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.movable.AbstractEntity;
import tbooop.model.player.api.coin.Coins;
import tbooop.model.player.api.key.Key;
import tbooop.model.player.impl.BasePlayerCoinImpl;

/**
 * A Player is a game object that can move on a 2D space,
 * a player can interact with an enemy trying to kill him or can collect
 * objects on the map.
 */
public abstract class AbstractPlayer extends AbstractEntity implements Player {

    private static final int PLAYER_COLLIDER_RADIUS = 12;
    private static final int PLAYER_INITIAL_DAMAGE = 1;
    private final Key keys;
    private final Coins coins;
    private float damage;

    /**
     * Create a new istance of a Entity.
     * 
     * @param position starting position
     * @param health   the entity's health
     * @param velocity it is the Entity velocity
     * @param keys     the player's keys
     */
    protected AbstractPlayer(final Point2d position, final Health health, final double velocity, final Key keys) {
        super(position, health, velocity, GameTag.PLAYER, PLAYER_COLLIDER_RADIUS);
        this.coins = new BasePlayerCoinImpl();
        this.damage = PLAYER_INITIAL_DAMAGE;
        this.keys = keys;
    }

    /** {@inheritDoc} */
    @Override
    public Optional<Direction> getCardinalDirection() {
        for (final var point2d : Direction.getAll()) {
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
    public int getCoins() {
        return this.coins.getCoins();
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
     * 
     * @return the amount of damage the player can do.
     */
    public float getDamage() {
        return this.damage;
    }

    /**
     * This method increases the amount of damage the Player can do.
     * 
     * @param damage it's the amount of damege to increase.
     */
    public void setDamage(final float damage) {
        this.damage = damage;
    }

    /** {@inheritDoc} */
    @Override
    public void pickupKey() {
        this.keys.pickupKey();
    }

    /** {@inheritDoc} */
    @Override
    public void useKey() {
        if (hasKey()) {
            this.keys.useKey();
        }
    }
}
