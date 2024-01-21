package tbooop.model.pickupables.api;

import tbooop.commons.Point2d;
import tbooop.commons.RoomBounds;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.unmovable.UnmovableAbs;

/**
 * Abstract class for pickupable items
 * <p>
 * Implements methods from Pickupable
 * interface (equals for every item)
 * and declare abstract methods to
 * be implemented by single different
 * classes.
 */
public abstract class PickupableAbstract extends UnmovableAbs implements Pickupable {

    private static final double MAP_HEIGHT_LIMIT = RoomBounds.HEIGHT;
    private static final double MAP_WIDTH_LIMIT = RoomBounds.WIDTH;
    /**
     * Create a new istance of a Pickup.
     * 
     * @param position       starting position (as a {@link javafx.geometry.Point2D
     *                       Point2D})
     * @param colliderRadius radius of the circle collider (hitbox).
     *                       The center of the collider will be this game object's
     *                       position
     * @param tag            the tag of this game object
     * @throws NullPointerException if any parameter passed is null
     */
    protected PickupableAbstract(final Point2d position, final double colliderRadius, final GameTag tag) {
        super(position, colliderRadius, tag);
    }

    /**
     * Generates a new random Point2d
     * that is consistent with the
     * game map dimensions.
     *
     * @return new Point2D
     */
    protected Point2d randomPointGenerator() {
        final double randomX = Math.random() * MAP_WIDTH_LIMIT;
        final double randomY = Math.random() * MAP_HEIGHT_LIMIT;
        return new Point2d(randomX, randomY);
    }
}
