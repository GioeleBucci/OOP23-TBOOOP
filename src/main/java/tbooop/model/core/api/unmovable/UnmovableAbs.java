package tbooop.model.core.api.unmovable;

import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameObjectAbs;
import tbooop.model.core.api.GameTag;

/**
 * Abstract class for unmovable game objects.
 */
public abstract class UnmovableAbs extends GameObjectAbs implements Unmovable {

    /**
     * Create a new istance of an unmovable GameObject.
     * 
     * @param position       starting position
     * @param colliderRadius radius of the circle collider (hitbox).
     *                       The center of the collider will be this game object's
     *                       position
     * @param tag            the tag of this game object
     * @throws NullPointerException if any parameter passed is null
     */
    protected UnmovableAbs(final Point2d position, final double colliderRadius, final GameTag tag) {
        super(position, colliderRadius, tag);
    }
}
