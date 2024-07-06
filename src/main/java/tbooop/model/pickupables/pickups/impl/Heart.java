package tbooop.model.pickupables.pickups.impl;

import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.pickupables.api.PickupableName;
import tbooop.model.pickupables.pickups.api.AbstractPickup;
import tbooop.model.player.api.Player;
import tbooop.view.sound_manager.Sound;
import tbooop.view.sound_manager.SoundManager;

/**
 * Class rapresenting "Heart" item in the
 * game. If picked up by the player, it
 * will increase its health of one unit.
 */
public class Heart extends AbstractPickup {

    private final PickupableName pickupTag = PickupableName.HEART;

    /**
     * Create a new istance of a Heart.
     * 
     * @param position       spawn position (as a Point2d)
     * @param colliderRadius radius of the circle collider (hitbox).
     *                       The center of the collider will be this game object's
     *                       position
     * @param tag            the tag of this game object
     * @throws NullPointerException if any parameter passed is null
     */
    protected Heart(final Point2d position, final double colliderRadius, final GameTag tag) {
        /*
         * dato che la super è la prima istruzione che può esserci nel costruttore
         * per far si che la posizione sia l'output di randomPointGenerator bisognerebbe
         * mettere il metodo statico oppure usare un blocco di inizializzazione static {
         * } (ho fatto una cosa del genere in View.java nel package view). Ci sono
         * probabilmente anche altri modi.
         */
        super(position, colliderRadius, tag);
    }

    /**
     * {@inheritDoc}
     * 
     * @param player
     */
    @Override
    public void onPlayerCollision(final Player player) {
        if (player.getHealth() == player.getMaxHealth()) {
            return;
        }
        onPickup(player);
        SoundManager.getInstance().playSound(Sound.HEART_PICKUP);
    }

    /**
     * When the Heart item is picked up by
     * the player, its health will be
     * increased by one.
     * 
     * @param player
     */
    private void onPickup(final Player player) {
        if (!super.isConsumed()) {
            player.recovery();
            destroy();
            super.consume();
        }
    }

    /** {@inheritDoc} */
    @Override
    public PickupableName getObjectName() {
        return this.pickupTag;
    }
}
