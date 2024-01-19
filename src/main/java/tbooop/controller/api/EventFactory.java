package tbooop.controller.api;

import tbooop.commons.Point2ds;
import java.util.NoSuchElementException;

/** A factory of events. 
 * @see Event
*/
public interface EventFactory {

    /**
     * Changes the floor, increasing the difficulty level by 1.
     * 
     * @return an event that changes the floor.
     */
    Event changeFloor();

    /**
     * 
     * @param dir the direction to move to.
     * @return an event that changes the room.
     * @throws NoSuchElementException if such room doesn't exist
     */
    Event changeRoom(Point2ds dir);
}
