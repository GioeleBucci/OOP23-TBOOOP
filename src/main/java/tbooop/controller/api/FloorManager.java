package tbooop.controller.api;

import tbooop.model.dungeon.doors.api.DoorUnmodifiable;

/**
 * The FloorManager interface is responsible for managing the floors and rooms
 * of the game.
 */
public interface FloorManager extends ControllerComponent {

    /**
     * Handles player/door collisions.
     * 
     * @param door the door that the player collided with
     */
    void onDoorCollision(DoorUnmodifiable door);

    /**
     * Sets the floor to a new, more difficult, one.
     */
    void changeFloor();
}
