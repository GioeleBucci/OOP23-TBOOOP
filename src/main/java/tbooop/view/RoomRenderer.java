package tbooop.view;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tbooop.commons.Point2d;
import tbooop.commons.Point2ds;
import tbooop.model.dungeon.rooms.api.Door;

import java.util.Map;

/** Renders the room. */
public class RoomRenderer {

    private final Group root = new Group();
    private final MinimapRenderer minimapRenderer;

    private static final int FLIP_VERTICALLY = -180;
    private static final int ROTATE_LEFT = -90;

    /** @param parent the parent this attaches to */
    public RoomRenderer(final Group parent) {
        parent.getChildren().add(root);
        this.minimapRenderer = new MinimapRenderer(this.root);
    }

    /**
     * Changes the room.
     * 
     * @param newRoomPos the room position (as a Point2d, where (0,0) is the
     *                   starting room)
     * @param doorMap    the door map of the room
     */
    public void changeRoom(final Point2d newRoomPos, final Map<Point2ds, Door> doorMap) {
        root.getChildren().removeIf(node -> node instanceof ImageView); // FIXME this is ugly
        if (newRoomPos.equals(Point2d.ZERO)) {
            addCommandsOnStartingRoom();
        }
        placeDoors(doorMap);
        this.minimapRenderer.update(newRoomPos, doorMap);
    }

    private ImageView createDoor(final Door door) {
        final Image doorImg = new Image(door.isSpecial() ? "/door_special_open.png" : "/door_open.png");
        final ImageView doorImageView = new ImageView(doorImg);
        doorImageView.setFitWidth(doorImg.getWidth() * View.UPSCALE_FACTOR);
        doorImageView.setFitHeight(doorImg.getHeight() * View.UPSCALE_FACTOR);
        root.getChildren().add(doorImageView);
        return doorImageView;
    }

    private void addCommandsOnStartingRoom() {
        final Image doorImg = new Image("/commands.png");
        final ImageView img = new ImageView(doorImg);
        img.setFitWidth(doorImg.getWidth() * View.UPSCALE_FACTOR);
        img.setFitHeight(doorImg.getHeight() * View.UPSCALE_FACTOR);
        img.setX(View.WIDTH / 2 - img.getFitWidth() / 2);
        img.setY(View.HEIGHT / 2 - img.getFitHeight() / 2);
        root.getChildren().add(img);
    }

    private void placeDoors(final Map<Point2ds, Door> doorMap) {
        for (final var entry : doorMap.entrySet()) {
            final ImageView door = createDoor(entry.getValue());
            switch (entry.getKey()) {
                case UP -> {
                    door.setX(DoorPositions.TOP.getX());
                    door.setY(DoorPositions.TOP.getY());
                }
                case DOWN -> {
                    door.setX(DoorPositions.BOTTOM.getX());
                    door.setY(DoorPositions.BOTTOM.getY());
                    door.rotateProperty().set(FLIP_VERTICALLY);
                }
                case LEFT -> {
                    door.setX(DoorPositions.LEFT.getX());
                    door.setY(DoorPositions.LEFT.getY());
                    door.rotateProperty().set(ROTATE_LEFT);
                }
                case RIGHT -> {
                    door.setX(DoorPositions.RIGHT.getX());
                    door.setY(DoorPositions.RIGHT.getY());
                    door.rotateProperty().set(-ROTATE_LEFT);
                }
                default -> throw new IllegalStateException("Unexpected value: " + entry.getKey());
            }
        }
    }
}
