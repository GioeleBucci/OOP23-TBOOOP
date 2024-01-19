package tbooop.controller.api;

import tbooop.controller.World;

/**
 * This interface models an event.
 * Each event can be triggered by a different source, and executes some type of
 * action on the world.
 */
public interface Event {
    /**
     * Excecute some type of action on the world.
     * 
     * @param world where the event takes action
     */
    void execute(World world);
}
