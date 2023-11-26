package tbooop.model.dungeon.rooms;

import java.util.Collection;
import java.util.Set;

import tbooop.commons.Directions;

/**
 * Base for all Rooms implementations.
 * 
 * @see {@link tbooop.model.dungeon.Floor Floor}
 */
public interface Room {
    /**
     * The doors that this room has.
     * 
     * @return a set of directions with an entry for every door present.
     * @see Directions
     */
    Set<Directions> getDoorSet();

    /**
     * Takes a collection of directions and sets the doors of the room accordingly.
     * 
     * @param directions a collection of directions
     * @see Directions
     */
    void setDoorSet(Collection<Directions> directions);

    /**
     * Whether this room has been visited at least once or not.
     * 
     * @return {@code true} if the room has been visited
     */
    boolean isExplored();

    /** Flags the room as explored. Cannot be undone. */
    void setExplored();
}
