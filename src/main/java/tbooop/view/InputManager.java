package tbooop.view;

import java.util.Optional;

import javafx.scene.input.KeyCode;
import tbooop.view.api.Keybinds;


/**
 * The InputManager interface defines methods for handling user input events.
 * It provides functionality for handling key press and release events,
 * updating the input state, and handling input based on keybinds.
 */
public interface InputManager {

    /**
     * Handles the key press event.
     * If the key corresponds to a GUI keybind, it calls the handleInput method.
     * Otherwise, it adds the key to the list of pressed keys.
     *
     * @param key The KeyCode of the pressed key.
     */
    void keyPressed(KeyCode key);

    /**
     * Called when a key is released.
     * Removes the released key from the list of pressed keys.
     *
     * @param key the key that was released
     */
    void keyReleased(KeyCode key);

    /**
     * Updates the input state by handling the pressed keys.
     * This method filters the pressed keys, extracts the valid keybinds,
     * and then handles the input based on the type of keybind.
     */
    void update();

    /**
     * Handles the input based on the provided keybind.
     * If the keybind is not present, the method returns without performing any action.
     * Otherwise, it executes the corresponding command based on the keybind.
     *
     * @param keybind The optional keybind to handle.
     */
    void handleInput(Optional<Keybinds> keybind);

}
