package tbooop.model.core.api.unmovable.pickupable;

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
    public void onCollision() {
    }

    /**
    * Determines the effect of the
    * item picked up by the 
    * player.
    */
    public abstract void onPickup();
}
