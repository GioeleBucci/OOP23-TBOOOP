package tbooop.view.api;

import java.util.Optional;

import javafx.scene.input.KeyCode;

/**
 * Keybinds enum.
 */
public enum Keybinds {
    /**
     * Keybind for moving up.
     */
    UP(KeyCode.W),

    /**
     * Keybind for moving down.
     */
    DOWN(KeyCode.S),

    /**
     * Keybind for moving left.
     */
    LEFT(KeyCode.A),

    /**
     * Keybind for moving right.
     */
    RIGHT(KeyCode.D),

    /**
     * Keybind for shooting upwards.
     */
    SHOOT_UP(KeyCode.UP),

    /**
     * Keybind for shooting downwards.
     */
    SHOOT_DOWN(KeyCode.DOWN),

    /**
     * Keybind for shooting to the left.
     */
    SHOOT_LEFT(KeyCode.LEFT),

    /**
     * Keybind for shooting to the right.
     */
    SHOOT_RIGHT(KeyCode.RIGHT),

    /**
     * Keybind for zooming in.
     */
    ZOOM_IN(KeyCode.P),

    /**
     * Keybind for zooming out.
     */
    ZOOM_OUT(KeyCode.M),

    /**
     * Keybind for toggling fullscreen mode.
     */
    FULLSCREEN(KeyCode.F11);

    private final KeyCode keyCode;

    /**
     * Constructs a Keybind with the specified KeyCode.
     *
     * @param keyCode the KeyCode associated with the keybind
     */
    Keybinds(final KeyCode keyCode) {
        this.keyCode = keyCode;
    }

    /**
     * Returns the KeyCode associated with the keybind.
     *
     * @return the KeyCode associated with the keybind
     */
    public KeyCode getKeyCode() {
        return keyCode;
    }

    /**
     * Checks if the given keybind corresponds to a GUI action.
     *
     * @param keybind The keybind to check.
     * @return True if the keybind corresponds to a GUI action, false otherwise.
     */
    public static boolean isGui(final Keybinds keybind) {
        return keybind == Keybinds.FULLSCREEN
                || keybind == Keybinds.ZOOM_IN
                || keybind == Keybinds.ZOOM_OUT;
    }

    /**
     * Checks if the given keybind represents a shoot action.
     *
     * @param keybind the keybind to check
     * @return true if the keybind represents a shoot action, false otherwise
     */
    public static boolean isShoot(final Keybinds keybind) {
        return keybind == Keybinds.SHOOT_UP || keybind == Keybinds.SHOOT_DOWN || keybind == Keybinds.SHOOT_LEFT
                || keybind == Keybinds.SHOOT_RIGHT;
    }

    /**
     * Checks if the given Keybinds is a move command.
     *
     * @param keybind The Keybinds to check.
     * @return true if the Keybinds is a move command, false otherwise.
     */
    public static boolean isMove(final Keybinds keybind) {
        return keybind == Keybinds.UP || keybind == Keybinds.DOWN || keybind == Keybinds.LEFT
                || keybind == Keybinds.RIGHT;
    }

    /**
     * Returns an Optional containing the Keybinds enum associated with the
     * specified KeyCode,
     * or an empty Optional if no matching keybind is found.
     *
     * @param keyCode the KeyCode to search for
     * @return an Optional containing the Keybinds enum associated with the
     *         specified KeyCode,
     *         or an empty Optional if no matching keybind is found
     */
    public static Optional<Keybinds> getKeybind(final KeyCode keyCode) {
        for (final Keybinds keybind : Keybinds.values()) {
            if (keybind.getKeyCode().equals(keyCode)) {
                return Optional.of(keybind);
            }
        }
        return Optional.empty();
    }
}
