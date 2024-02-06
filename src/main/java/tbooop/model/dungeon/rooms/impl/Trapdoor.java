package tbooop.model.dungeon.rooms.impl;

import tbooop.commons.RoomBounds;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.unmovable.UnmovableAbs;

/**
 * A trapdoor takes the player to the next floor.
 */
public class Trapdoor extends UnmovableAbs {

    private static final int COLLIDER_RADIUS = 20;

    /**
     * Creates a new Trapdoor.
     */
    public Trapdoor() {
        super(RoomBounds.CENTER, COLLIDER_RADIUS, GameTag.TRAPDOOR);
    }
}
