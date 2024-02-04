package tbooop.model.core.api.unmovable;

import tbooop.model.core.api.GameObject;

/**
 * Interface for unmovable game object.
 * <p>
 * It represents a tag interface for unmovable game objects.
 */

public interface Unmovable extends GameObject {

    /**Returns the name of this
     * item/pickup.
     * 
     * @return an UnmovableName
     */
    UnmovableName getObjectName();
}
