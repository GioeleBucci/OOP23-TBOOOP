package tbooop.view;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import tbooop.commons.api.Direction;
import tbooop.commons.api.RoomBounds;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.unmovable.UnmovableAbs;
import tbooop.model.dungeon.doors.api.DoorUnmodifiable;
import tbooop.model.dungeon.rooms.api.RoomUnmodifiable;
import tbooop.view.api.ViewComponentImpl;
import tbooop.view.api.ViewElements;

/**
 * The RoomRenderer class is responsible for rendering the room and its doors in
 * the game view.
 */
public class RoomRenderer extends ViewComponentImpl {
    private static final Image REGULAR_DOOR_OPEN = new Image("tileset/door_open.png");
    private static final Image REGULAR_DOOR_CLOSED = new Image("tileset/door_closed.png");
    private static final Image SPECIAL_DOOR_OPEN = new Image("tileset/special_door_open.png");
    private static final Image SPECIAL_DOOR_CLOSED = new Image("tileset/special_door_closed.png");
    /**
     * An image with the commands that should be rendered only in the first room.
     */
    private static final ImageView COMMANDS_TOOLTIP = new ImageView(new Image("tileset/commands.png"));

    private final Map<DoorUnmodifiable, ImageView> doorsSpriteMap = new HashMap<>();
    private final MusicPlayer musicPlayer = new MusicPlayer();
    private final ViewElements view;

    /**
     * Constructs a RoomRenderer object with the specified view.
     * 
     * @param view the view that will be updated
     */
    public RoomRenderer(final ViewElements view) {
        super(view);
        this.view = view;
    }

    /** {@inheritDoc} */
    @Override
    public void init() {
        addTooltip();
    }

    /** {@inheritDoc} */
    @Override
    public void update() {
        // nothing to do
    }

    /**
     * Change the room to render.
     * 
     * @param newRoom the new room to render
     */
    public void changeRoom(final RoomUnmodifiable newRoom) {

        onRoomSwap(newRoom);

        Platform.runLater(() -> {
            // clear doors
            getView().getRoot().getChildren().removeAll(doorsSpriteMap.values());
            newRoom.getDoorMap().entrySet().forEach(e -> {
                ImageView imgView;
                if (e.getValue().isSpecial()) {
                    imgView = new ImageView(
                            e.getValue().isOpen() ? SPECIAL_DOOR_OPEN : SPECIAL_DOOR_CLOSED);
                } else {
                    imgView = new ImageView(
                            e.getValue().isOpen() ? REGULAR_DOOR_OPEN : REGULAR_DOOR_CLOSED);
                }
                ((ViewImpl) getView()).addGameObjectToView(imgView, e.getValue());
                doorsSpriteMap.put(e.getValue(), imgView);
                rotateDoor(imgView, e.getKey());
            });
        });
    }

    private void addTooltip() {
        Platform.runLater(() -> {
            // add a dummy gameobject and associate it with the tooltip imageview
            ((ViewImpl) view).addGameObjectToView(COMMANDS_TOOLTIP,
                    new UnmovableAbs(RoomBounds.CENTER, 0, GameTag.DOOR) {
                    });
        });
    }

    private void onRoomSwap(final RoomUnmodifiable newRoom) {
        updateTooltipVisibility(newRoom);
        playRoomMusic(newRoom);
    }

    private void playRoomMusic(final RoomUnmodifiable newRoom) {
        if (newRoom.isSpecial()) {
            musicPlayer.playSpecialRoomMusic();
            return;
        }
        if (newRoom.isBossRoom()) {
            musicPlayer.playBossMusic();
            return;
        }
        musicPlayer.playDefaultMusic();
    }

    private void updateTooltipVisibility(final RoomUnmodifiable newRoom) {
        COMMANDS_TOOLTIP.setVisible(newRoom.isFirstRoom());
    }

    private void rotateDoor(final ImageView imgView, final Direction dir) {
        switch (dir) {
            case DOWN -> imgView.setRotate(180);
            case LEFT -> imgView.setRotate(90 * -1);
            case RIGHT -> imgView.setRotate(90);
            default -> {
            }
        }
    }

    /**
     * Plays a fading animation when changing floor.
     */
    protected void changeFloor() {
        final FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), view.getScene().getRoot());
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
    }

}
