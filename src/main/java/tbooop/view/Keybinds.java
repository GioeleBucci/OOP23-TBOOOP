package tbooop.view;

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
    ZOOM_IN(KeyCode.PLUS),

    /**
     * Keybind for zooming out.
     */
    ZOOM_OUT(KeyCode.MINUS),

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
            if (keybind.getKeyCode() == keyCode) {
                return Optional.of(keybind);
            }
        }
        return Optional.empty();
    }
}
