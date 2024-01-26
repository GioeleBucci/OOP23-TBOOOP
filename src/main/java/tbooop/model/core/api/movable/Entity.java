package tbooop.model.core.api.movable;

import java.util.Set;

import tbooop.commons.api.Projectile;

/**
 * An Entity represents a GameObject that can move and be damaged.
 */
public interface Entity extends Damageable {

    /**
     * Returns the latest projectiles that have been shot. 
     * 
     * @return the set of projectiles
     */
    Set<Projectile> getShotProjectiles();
}
