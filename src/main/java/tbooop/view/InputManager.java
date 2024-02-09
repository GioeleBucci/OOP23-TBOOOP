package tbooop.view;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.scene.input.KeyCode;
import javafx.stage.Screen;
import tbooop.commons.Point2ds;
import tbooop.commons.RoomBounds;
import tbooop.controller.MoveCommand;
import tbooop.controller.ShootCommand;
import tbooop.controller.api.Controller;
import tbooop.controller.api.PlayerCommand;
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

    public void keyPressed(final KeyCode key) {
        Optional<Keybinds> keybind = Keybinds.getKeybind(key);
        if (keybind.isPresent() && Keybinds.isGui(keybind.get())) {
            handleInput(keybind);
        } else {
            keysPressed.add(key);
        }
    }

    public void keyReleased(final KeyCode key) {
        if (keysPressed.contains(key)) {
            keysPressed.remove(key);
        }
    }

    public void update() {
        Set<Keybinds> validKeys = keysPressed.stream()
                .map(Keybinds::getKeybind)
                .filter(Optional::isPresent)
                .map(Optional::get).collect(Collectors.toSet());
        handleInput(validKeys.stream().filter(Keybinds::isMove).findFirst());
        handleInput(validKeys.stream().filter(Keybinds::isShoot).findFirst());
    }

    /**
     * Handles user input based on the pressed key.
     *
     * @param keyCode the user input
     */
    public void handleInput(final Optional<Keybinds> keybind) {
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
