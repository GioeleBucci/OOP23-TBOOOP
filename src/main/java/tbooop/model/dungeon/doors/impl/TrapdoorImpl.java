package tbooop.model.dungeon.doors.impl;

import tbooop.commons.api.RoomBounds;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.unmovable.UnmovableAbs;
import tbooop.model.dungeon.doors.api.Trapdoor;
import tbooop.model.player.api.Player;

/**
 * A trapdoor takes the player to the next floor.
 */
public class TrapdoorImpl extends UnmovableAbs implements Trapdoor {

    private static final int COLLIDER_RADIUS = 1;

    /**
     * Creates a new Trapdoor.
     */
    public TrapdoorImpl() {
        super(RoomBounds.CENTER, COLLIDER_RADIUS, GameTag.TRAPDOOR);
    }

    /** {@inheritDoc} */
    @Override
    public void onPlayerCollision(final Player player) {
        super.destroy();
    }

}
