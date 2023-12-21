package tbooop.view;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import tbooop.commons.Point2d;
import tbooop.commons.Point2ds;
import tbooop.model.dungeon.Floor;
import tbooop.model.dungeon.rooms.api.Door;

/** Renders a minimap. */
public class MinimapRenderer {
    private final Group root = new Group();
    private static final int MAP_SIZE = Floor.MAX_DIST_FROM_START * 2 + 1;
    private static final Color UNEXPLORED_COLOR = Color.GRAY;
    private static final Color EXPLORED_COLOR = Color.WHITESMOKE;
    private final Map<Point2d, Rectangle> map = new HashMap<>();
    private Point2d currentRoom = Point2d.ZERO;

    /** @param parentRoot the root this attaches to */
    public MinimapRenderer(final Group parentRoot) {
        parentRoot.getChildren().add(this.root);
        init();
    }

    /**
     * Updates the minimap.
     * 
     * @param newRoomPos the new room position
     * @param doorMap    the door map
     */
    public void update(final Point2d newRoomPos, final Map<Point2ds, Door> doorMap) {
        this.currentRoom = newRoomPos;
        map.get(currentRoom).setFill(EXPLORED_COLOR);
        placePlayerIndicator();
        for (final var dir : doorMap.keySet()) {
            final var neighbour = map.get(currentRoom.add(dir.toP2d()));
            if (neighbour.getFill() != EXPLORED_COLOR) {
                neighbour.setFill(UNEXPLORED_COLOR);
            }
        }
    }

    /** Places the player indicator. */
    private void placePlayerIndicator() {
        // remove previous indicator
        root.getChildren().removeIf(node -> node instanceof Circle);
        final var tile = map.get(currentRoom);

        final double centerX = tile.getX() + tile.getWidth() / 2;
        final double centerY = tile.getY() + tile.getHeight() / 2;
        final double radius = Math.min(tile.getWidth(), tile.getHeight()) / 4;

        final Circle playerIndicator = new Circle(centerX, centerY, radius, Color.RED);
        root.getChildren().add(playerIndicator);
    }

    /** Initializes the minimap. */
    private void init() {
        final double squareSize = View.WIDTH / 5;
        final double squareX = View.WIDTH - squareSize;
        final double squareY = 0;

        final Rectangle square = new Rectangle(squareX, squareY, squareSize, squareSize);
        square.setFill(Color.TRANSPARENT);
        root.getChildren().add(square);

        final double smallerSquareSize = squareSize / MAP_SIZE;

        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                final double smallerSquareX = squareX + i * smallerSquareSize;
                final double smallerSquareY = squareY + j * smallerSquareSize;

                final Rectangle smallerSquare = new Rectangle(smallerSquareX, smallerSquareY, smallerSquareSize - 2,
                        smallerSquareSize - 2);
                smallerSquare.setArcWidth(10);
                smallerSquare.setArcHeight(10);
                smallerSquare.setFill(Color.TRANSPARENT);
                map.put(new Point2d(i - Floor.MAX_DIST_FROM_START, j - Floor.MAX_DIST_FROM_START), smallerSquare);
                root.getChildren().add(smallerSquare);
            }
        }
        map.get(Point2d.ZERO).setFill(EXPLORED_COLOR);
        placePlayerIndicator();
    }
}
