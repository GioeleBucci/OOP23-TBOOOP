package tbooop.model.pickupables.pickups.impl;

import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.pickupables.api.PickupableAbstract;
import tbooop.model.pickupables.api.PickupablePrices;
import tbooop.model.pickupables.api.PickupableStatus;
import tbooop.model.player.api.Player;

/**
 * Class rapresenting "Heart" item in the
 * game. If picked up by the player, it
 * will increase its health of one unit.
 */
public class Heart extends PickupableAbstract {

    private final int itemCost = PickupablePrices.HEART_PRICE.getItemPrice();
    private PickupableStatus itemTag = PickupableStatus.NORMAL;
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
     * When the Heart item is picked up by
     * the player, its health will be
     * increased by one.
     * 
     * @param player
    */
    @Override
    public void onPickup(final Player player) {
        if (this.itemTag.equals(PickupableStatus.SPECIAL)) {
            if (player.getCoin() >= this.itemCost) {
                player.recovery();
                player.setCoin(-itemCost);
            }
        } else {
            player.recovery();
        }
    }

    /** {@inheritDoc} */
    @Override
    public void updateState(final long deltaTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateState'");
    }

    /** {@inheritDoc} */
    @Override
    public void setInShop() {
        this.itemTag = PickupableStatus.SPECIAL;
    }
}
