package tbooop.model.dungeon.floor.api;

import java.util.Map;

import tbooop.commons.Point2d;
import tbooop.model.dungeon.rooms.api.Room;

/** Interface for floor. */
public interface Floor {

    /**
     * Returns the rooms map.
     * 
     * @return a Map where the key is a room's position and the value the associated
     *         room object
     */
    Map<Point2d, Room> getRoomsMap();

}
