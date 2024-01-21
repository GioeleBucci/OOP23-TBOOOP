package tbooop.controller.api;

/**
 * The CommandListener interface represents an object that listens for commands
 * and notifies when a command is received.
 */
public interface CommandListener {
    /**
     * Notifies the listener when a player command is received.
     * 
     * @param cmd the player command received
     */
    void notifyCommand(PlayerCommand cmd);
}
