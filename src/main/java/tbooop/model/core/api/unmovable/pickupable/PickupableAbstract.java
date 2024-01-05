package tbooop.model.core.api.unmovable.pickupable;

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
    * Determines the effect of the
    * item picked up by the 
    * player.
    */
    public abstract void onPickup();
}
