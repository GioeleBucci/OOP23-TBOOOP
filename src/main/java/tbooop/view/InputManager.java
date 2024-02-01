package tbooop.view;

import java.util.Optional;

import javafx.scene.input.KeyCode;
import tbooop.commons.Point2ds;
import tbooop.commons.RoomBounds;
import tbooop.controller.MoveCommand;
import tbooop.controller.ShootCommand;
import tbooop.controller.api.Controller;
import tbooop.controller.api.PlayerCommand;
import tbooop.view.api.ViewUnm;

/**
 * The InputManager class handles user input and triggers the corresponding
 * events.
 */
public class InputManager {

    private final Controller commandListener;
    private final ViewUnm view;
    private static final double WINDOW_SCALE_PERCENTAGE = 0.1;

    /**
     * Constructs an InputManager object with the specified controller and stage.
     *
     * @param controller the controller commands will be sent to
     * @param view       the main view
     */
    public InputManager(final Controller controller, final ViewUnm view) {
        this.commandListener = controller;
        this.view = view;
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
        view.getStage().setWidth(newWidth);
        // view.getStage().setHeight((newWidth * RoomBounds.HEIGHT / RoomBounds.WIDTH) +
        // view.getBarsize());
        view.getStage().setHeight(newWidth / view.getStageAspectRatio());
    }

}
