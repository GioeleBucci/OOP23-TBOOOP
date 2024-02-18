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
import tbooop.view.api.InputManager;
import tbooop.view.api.Keybinds;
import tbooop.view.api.ViewElements;

/**
 * The InputManager class handles user input and triggers the corresponding
 * events.
 */
public final class InputManagerImpl implements InputManager {

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
    public InputManagerImpl(final Controller controller, final ViewElements view) {
        this.commandListener = controller;
        this.view = view;
    }

    /** {@inheritDoc} */
    @Override
    public void keyPressed(final KeyCode key) {
        final Optional<Keybinds> keybind = Keybinds.getKeybind(key);
        if (keybind.isPresent() && Keybinds.isGui(keybind.get())) {
            handleInput(keybind);
        } else {
            keysPressed.add(key);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void keyReleased(final KeyCode key) {
        if (keysPressed.contains(key)) {
            keysPressed.remove(key);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void update() {
        final Set<Keybinds> validKeys = keysPressed.stream()
                .map(Keybinds::getKeybind)
                .filter(Optional::isPresent)
                .map(Optional::get).collect(Collectors.toSet());
        handleInput(validKeys.stream().filter(Keybinds::isMove).findFirst());
        handleInput(validKeys.stream().filter(Keybinds::isShoot).findFirst());
    }

    /** {@inheritDoc} */
    @Override
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
            case EXIT_FULLSCREEN -> {
                System.out.println("Exiting fullscreen");
                view.getStage().setWidth(view.getStage().getWidth() / 2);
                resizeWindow(false);
            }
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
        > Screen.getPrimary().getVisualBounds().getHeight() && scaleUp) {
            view.getStage().setFullScreen(true);
            return;
        }
        view.getStage().setWidth(newWidth);
        view.getStage().setHeight(newWidth / view.getStageAspectRatio());
        view.getStage().centerOnScreen();
    }

}
