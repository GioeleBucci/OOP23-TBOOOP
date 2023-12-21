package tbooop.model.dungeon.rooms.api;

import java.util.Map;

import tbooop.commons.Point2ds;

/**
 * Base for all Rooms implementations.
 * 
 * @see {@link tbooop.model.dungeon.Floor Floor}
 */
public interface Room {
    /**
     * The doors that this room has.
     * 
     * @return a map with an entry for each direction in which a door is present.
     * @see Point2ds
     */
    Map<Point2ds, Door> getDoorMap();

    /**
     * Adds a door to this room.
     * 
     * @param direction where the door is located
     * @param door      the door to add
     * @see Point2ds
     */
    void addDoor(Point2ds direction, Door door);

    /**
     * Whether this room has been visited at least once or not.
     * 
     * @return whether the room has been visited
     */
    boolean isExplored();

    /** Flags the room as explored. Cannot be undone. */
    void setExplored();

    /**
     * Whether this room is special or not.
     * 
     * @return whether the room is special
     */
    boolean isSpecial();

}
