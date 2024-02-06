package tbooop.view;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tbooop.commons.Point2ds;
import tbooop.model.dungeon.rooms.api.DoorUnmodifiable;
import tbooop.model.dungeon.rooms.api.RoomUnmodifiable;
import tbooop.view.api.ViewComponent;
import tbooop.view.api.ViewElements;

/**
 * The RoomRenderer class is responsible for rendering the room and its doors in
 * the game view.
 */
public class RoomRenderer extends ViewComponent {
    private static final Image REGULAR_DOOR_OPEN = new Image("tileset/door_open.png");
    private static final Image REGULAR_DOOR_CLOSED = new Image("tileset/door_closed.png");
    private static final Image SPECIAL_DOOR_OPEN = new Image("tileset/special_door_open.png");
    private static final Image SPECIAL_DOOR_CLOSED = new Image("tileset/special_door_closed.png");

    private final Map<DoorUnmodifiable, ImageView> doorsSpriteMap = new HashMap<>();

    /**
     * Constructs a RoomRenderer object with the specified view.
     * 
     * @param view the view that will be updated
     */
    public RoomRenderer(final ViewElements view) {
        super(view);
    }

    /** {@inheritDoc} */
    @Override
    public void init() {
    }

    /** {@inheritDoc} */
    @Override
    public void update() {
    }

    /**
     * Change the room to render.
     * 
     * @param newRoom the new room to render
     */
    public void changeRoom(final RoomUnmodifiable newRoom) {
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

    private void rotateDoor(final ImageView imgView, final Point2ds dir) {
        switch (dir) {
            case DOWN -> imgView.setRotate(180);
            case LEFT -> imgView.setRotate(90 * -1);
            case RIGHT -> imgView.setRotate(90);
            default -> {
            }
        }
    }

}
