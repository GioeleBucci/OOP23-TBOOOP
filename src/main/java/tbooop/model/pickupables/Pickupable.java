package tbooop.model.pickupables;

import tbooop.model.core.api.unmovable.Unmovable;

/**Rapresents methods every
 * pickupable item in the game must have.
 */
public interface Pickupable extends Unmovable {
    /**Returns the name of this
     * item/pickup.
     * 
     * @return an UnmovableName
     */
    UnmovableName getObjectName();
}
