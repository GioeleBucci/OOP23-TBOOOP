package tbooop.view;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.scene.input.KeyCode;
import javafx.stage.Screen;
import tbooop.commons.api.CardinalDirection;
import tbooop.commons.api.RoomBounds;
import tbooop.controller.api.Controller;
import tbooop.controller.api.PlayerCommand;
import tbooop.controller.impl.MoveCommand;
import tbooop.controller.impl.ShootCommand;
import tbooop.view.api.ViewElements;

/**
 * The InputManager class handles user input and triggers the corresponding
 * events.
 */
public final class InputManager {

    private final Controller commandListener;
    private final ViewElements view;
    private static final double WINDOW_SCALE_PERCENTAGE = 0.1;
    private final Set<KeyCode> keysPressed = new LinkedHashSet<>();

    /**
     * Constructs an InputManager object with the specified controller and stage.
     *
     * @param controller the controller commands will be sent to
     * @param view       the main view
     */
    public InputManager(final Controller controller, final ViewElements view) {
        this.commandListener = controller;
        this.view = view;
    }

    /**
     * Handles the key press event.
     * If the key corresponds to a GUI keybind, it calls the handleInput method.
     * Otherwise, it adds the key to the list of pressed keys.
     *
     * @param key The KeyCode of the pressed key.
     */
    public void keyPressed(final KeyCode key) {
        final Optional<Keybinds> keybind = Keybinds.getKeybind(key);
        if (keybind.isPresent() && Keybinds.isGui(keybind.get())) {
            handleInput(keybind);
        } else {
            keysPressed.add(key);
        }
    }

    /**
     * Called when a key is released.
     * Removes the released key from the list of pressed keys.
     *
     * @param key the key that was released
     */
    public void keyReleased(final KeyCode key) {
        if (keysPressed.contains(key)) {
            keysPressed.remove(key);
        }
    }

    /**
     * Updates the input state by handling the pressed keys.
     * This method filters the pressed keys, extracts the valid keybinds,
     * and then handles the input based on the type of keybind.
     */
    public void update() {
        final Set<Keybinds> validKeys = keysPressed.stream()
                .map(Keybinds::getKeybind)
                .filter(Optional::isPresent)
                .map(Optional::get).collect(Collectors.toSet());
        handleInput(validKeys.stream().filter(Keybinds::isMove).findFirst());
        handleInput(validKeys.stream().filter(Keybinds::isShoot).findFirst());
    }

    /**
     * Handles the input based on the provided keybind.
     * If the keybind is not present, the method returns without performing any action.
     * Otherwise, it executes the corresponding command based on the keybind.
     *
     * @param keybind The optional keybind to handle.
     */
    public void handleInput(final Optional<Keybinds> keybind) {
        if (!keybind.isPresent()) {
            return;
        }
        switch (keybind.get()) {
            case UP -> sendCommand(new MoveCommand(CardinalDirection.UP));
            case DOWN -> sendCommand(new MoveCommand(CardinalDirection.DOWN));
            case LEFT -> sendCommand(new MoveCommand(CardinalDirection.LEFT));
            case RIGHT -> sendCommand(new MoveCommand(CardinalDirection.RIGHT));
            case SHOOT_UP -> sendCommand(new ShootCommand(CardinalDirection.UP));
            case SHOOT_DOWN -> sendCommand(new ShootCommand(CardinalDirection.DOWN));
            case SHOOT_LEFT -> sendCommand(new ShootCommand(CardinalDirection.LEFT));
            case SHOOT_RIGHT -> sendCommand(new ShootCommand(CardinalDirection.RIGHT));
            case FULLSCREEN -> view.getStage().setFullScreen(!view.getStage().isFullScreen());
            case ZOOM_IN -> resizeWindow(true);
            case ZOOM_OUT -> resizeWindow(false);
            default -> {
            }
        }
    }

    private void sendCommand(final PlayerCommand cmd) {
        commandListener.notifyCommand(cmd);
    }

    private void resizeWindow(final boolean scaleUp) {
        final double newWidth = view.getStage().getWidth()
                * (1 + (scaleUp ? WINDOW_SCALE_PERCENTAGE : WINDOW_SCALE_PERCENTAGE * -1));
        if (newWidth < RoomBounds.WIDTH) {
            return;
        }
        if (newWidth / view.getStageAspectRatio()
        > Screen.getPrimary().getVisualBounds().getHeight()) {
            view.getStage().setFullScreen(true);
            return;
        }
        view.getStage().setWidth(newWidth);
        view.getStage().setHeight(newWidth / view.getStageAspectRatio());
        view.getStage().centerOnScreen();
    }

}
