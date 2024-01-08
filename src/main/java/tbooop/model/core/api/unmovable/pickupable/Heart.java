package tbooop.model.core.api.unmovable.pickupable;

import tbooop.commons.Point2d;
import tbooop.model.core.api.GameTag;

/**
 * Class rapresenting "Heart" item in the
 * game. If picked up by the player, it
 * will increase its health of one unit.
 */
public class Heart extends PickupableAbstract {

    private static final int MAP_DIMENSION = 5;
    // private Point2d heartPosition = randomPointGenerator(); leggi commento in costruttore

    /**
     * Create a new istance of a Heart.
     * 
     * @param position       spawn position (as a {@link javafx.geometry.Point2D
     *                       Point2D})
     * @param colliderRadius radius of the circle collider (hitbox).
     *                       The center of the collider will be this game object's
     *                       position
     * @param tag            the tag of this game object
     * @throws NullPointerException if any parameter passed is null
     */
    protected Heart(final Point2d position, final double colliderRadius, final GameTag tag) {
        /*
         * TODO dato che la super è la prima istruzione che può esserci nel costruttore
         * per far si che la posizione sia l'output di randomPointGenerator bisognerebbe
         * mettere il metodo statico oppure usare un blocco di inizializzazione static {
         * } (ho fatto una cosa del genere in View.java nel package view). Ci sono
         * probabilmente anche altri modi.
         */
        super(position, colliderRadius, tag);
        randomPointGenerator();
    }

    // private final CircleColliderImpl heartCollider = new
    // CircleColliderImpl(this.heartPosition, 2);

    /**
     * Generates a new random Point2d
     * that is consistent with the
     * game map dimensions.
     *
     * @return new Point2D
     */
    private Point2d randomPointGenerator() {
        // INCONSISTENT NOW, CASUAL MAP DIMENSIONS !!!
        final double randomX = Math.random() * MAP_DIMENSION;
        final double randomY = Math.random() * MAP_DIMENSION;
        return new Point2d(randomX, randomY);
    }

    /** {@inheritDoc} */
    @Override
    public void onPickup() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onPickup'");
    }

    /** {@inheritDoc} */
    @Override
    public void updateState(final long deltaTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateState'");
    }

}
