package tbooop.model.core.api.unmovable.pickupable;

import tbooop.commons.Point2d;
import tbooop.commons.api.CircleCollider;

/**
 * Abstract class for pickupable items
 * <p>
 * Implements methods from Pickupable
 * interface (equals for every item)
 * and declare abstract methods to
 * be implemented by single different
 * classes.
 */
public abstract class PickupableAbstract implements Pickupable {

    /**
    * Determines the condition under which 
    * a game entity comes into contact 
    * with an object.
    */
    @Override
    public boolean isColliding(final CircleCollider player, final CircleCollider item) {
        if(item.isColliding(player)){
            return true;
        }else{
            return false;
        }
    }

    /**
    * Generates a new random Point2d
    * that is consistent with the 
    * game map dimensions.
    */
    //INCONSISTENT NOW, MAP DIMENSIONS UNKNOWN !!!
    @Override
    public Point2d randomCoordinatesGenerator(){
        double randomX = (Math.random()*5);
        double randomY = (Math.random()*5);
        return new Point2d(randomX, randomY);
    }

    /**
    * Determines the effect of the
    * item picked up by the 
    * player.
    */
    public abstract void onPickup();
}
