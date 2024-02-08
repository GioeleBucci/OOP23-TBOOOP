package tbooop.model.dungeon.rooms.impl;

import tbooop.commons.RoomBounds;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.unmovable.UnmovableAbs;
import tbooop.model.dungeon.rooms.api.Trapdoor;
import tbooop.model.player.api.Player;

/**
 * A trapdoor takes the player to the next floor.
 */
public class TrapdoorImpl extends UnmovableAbs implements Trapdoor {

    private static final int COLLIDER_RADIUS = 10;

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
