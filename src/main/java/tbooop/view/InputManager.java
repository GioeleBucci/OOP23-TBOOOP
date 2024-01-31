package tbooop.view;

import java.util.Optional;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import tbooop.commons.Point2ds;
import tbooop.commons.RoomBounds;
import tbooop.controller.MoveCommand;
import tbooop.controller.ShootCommand;
import tbooop.controller.api.Controller;
import tbooop.controller.api.PlayerCommand;

/**
 * The InputManager class handles user input and triggers the corresponding
 * events.
 */
public class InputManager {

    private final Controller commandListener;
    private final Stage stage;
    private static final double WINDOW_SCALE_PERCENTAGE = 0.1;

    /**
     * Constructs an InputManager object with the specified controller and stage.
     *
     * @param controller the controller commands will be sent to
     * @param stage      the main stage of the view
     */
    public InputManager(final Controller controller, final Stage stage) {
        this.commandListener = controller;
        this.stage = stage;
    }

    /**
     * Handles user input based on the pressed key.
     *
     * @param keyCode the user input
     */
    public void handleInput(final KeyCode keyCode) {
        final Optional<Keybinds> keybind = Keybinds.getKeybind(keyCode);
        if (!keybind.isPresent()) {
            return;
        }
        switch (keybind.get()) {
            case UP -> sendCommand(new MoveCommand(Point2ds.UP));
            case DOWN -> sendCommand(new MoveCommand(Point2ds.DOWN));
            case LEFT -> sendCommand(new MoveCommand(Point2ds.LEFT));
            case RIGHT -> sendCommand(new MoveCommand(Point2ds.RIGHT));
            case SHOOT_UP -> sendCommand(new ShootCommand(Point2ds.UP));
            case SHOOT_DOWN -> sendCommand(new ShootCommand(Point2ds.DOWN));
            case SHOOT_LEFT -> sendCommand(new ShootCommand(Point2ds.LEFT));
            case SHOOT_RIGHT -> sendCommand(new ShootCommand(Point2ds.RIGHT));
            case FULLSCREEN -> stage.setFullScreen(!stage.isFullScreen());
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
        final double newWidth = stage.getWidth()
                * (1 + (scaleUp ? WINDOW_SCALE_PERCENTAGE : WINDOW_SCALE_PERCENTAGE * -1));
        if (newWidth < RoomBounds.WIDTH) {
            return;
        }
        stage.setWidth(newWidth);
        stage.setHeight(newWidth * RoomBounds.HEIGHT / RoomBounds.WIDTH);
    }

}
