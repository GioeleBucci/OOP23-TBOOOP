package tbooop.controller;

import java.util.logging.Logger;

import tbooop.commons.Point2d;
import tbooop.commons.Point2ds;
import tbooop.model.dungeon.Floor;
import tbooop.model.dungeon.LevelFloor;
import tbooop.view.View;

/**
 * <i>Note:</i> this is <b>NOT</b> how the controller should be.
 * This is just a quick and dirty way to get stuff running.
 * The controller should recieve the events from the view and feed them to the
 * model.
 * Then after getting the model's response, it should feed the response back to
 * the view, updating it.
 */
public class Controller {

    private static final int LEVEL = 2;

    private final View view;
    private final Logger logger = Logger.getLogger(Controller.class.getName());
    private Floor floor = new LevelFloor(LEVEL);
    private Point2d currentRoom = Point2d.ZERO;

    /**
     * Creates a new controller.
     * 
     * @param view the view
     */
    public Controller(final View view) {
        this.view = view;
    }

    /**
     * Changes the floor. (reset the game). Debug only.
     */
    public void changeFloor() {
        this.floor = new LevelFloor(LEVEL);
        this.currentRoom = Point2d.ZERO;
        final var firstRoom = floor.getRoomsMap().get(currentRoom).getDoorMap();
        logger.info(floor.toString());
        view.changeRooms(currentRoom, firstRoom);
    }

    /**
     * Changes the room.
     * Note: this emulates the work that the model should do.
     * 
     * @param dir the direction to move to
     */
    public void changeRoom(final Point2ds dir) {
        final var newPoint = dir.toP2d().add(currentRoom);
        if (!floor.getRoomsMap().containsKey(newPoint)) {
            return;
        }
        view.changeRooms(newPoint, floor.getRoomsMap().get(newPoint).getDoorMap());
        this.currentRoom = newPoint;
    }
}
