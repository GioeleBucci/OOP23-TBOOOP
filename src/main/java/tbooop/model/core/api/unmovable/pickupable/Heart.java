package tbooop.model.core.api.unmovable.pickupable;

import tbooop.commons.Point2d;

/**
 * Class rapresenting "Heart" item in the
 * game. If picked up by the player, it
 * will increase its health of one unit.
 */
public class Heart extends PickupableAbstract {
 
    private static final int MAP_DIMENSION = 5;
    private Point2d heartPosition = randomPointGenerator();
    //private final CircleColliderImpl heartCollider = new CircleColliderImpl(this.heartPosition, 2);

     /**
    * Generates a new random Point2d
    * that is consistent with the 
    * game map dimensions.
    *
    * @return new Point2D
    */
    //INCONSISTENT NOW, CASUAL MAP DIMENSIONS !!!
    private Point2d randomPointGenerator() {
        final double randomX = Math.random() * MAP_DIMENSION;
        final double randomY = Math.random() * MAP_DIMENSION;
        return new Point2d(randomX, randomY);
    }

    /** {@inheritDoc} */
    @Override
    public Point2d getPosition() {
        return this.heartPosition;
    }

    /** {@inheritDoc} */
    @Override
    public void setPosition(final Point2d newPos) {
        this.heartPosition = newPos;
    }

    /**
    * Determines the effect of the
    * item picked up by the 
    * player.
    */
    @Override
    public void onPickup() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onPickup'");
    }

}
