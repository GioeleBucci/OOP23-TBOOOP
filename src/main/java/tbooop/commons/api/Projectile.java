package tbooop.commons.api;

import tbooop.model.core.api.movable.Movable;

/**
 * Represents a Projectile.
 */
public interface Projectile extends Movable{
    
    /**
    * If you call this method the Projectile is removed from the world.
    */ 
    void disappear();
}
