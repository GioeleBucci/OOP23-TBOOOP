package tbooop.controller.api;

/**
 * Interface for the game controller.
 */
public interface Controller extends CommandListener {

    /**
     * The main game loop that processes input and updates the game state.
     */
    void mainLoop();

}
