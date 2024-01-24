package tbooop.view;

import javafx.scene.image.Image;
import tbooop.commons.api.Point2d;
import tbooop.commons.Point2dImpl;
import tbooop.commons.RoomBounds;

/** Contains the positions of the doors, expressed as Point2ds. */
public final class DoorPositions {

    /** Position of the top door, expressed as a Point2d. */
    public static final Point2d TOP;
    /** Position of the botom door, expressed as a Point2d. */
    public static final Point2d BOTTOM;
    /** Position of the left door, expressed as a Point2d. */
    public static final Point2d LEFT;
    /** Position of the right door, expressed as a Point2d. */
    public static final Point2d RIGHT;

    /** Distance between a game window margin and the walkable area of the room. */
    private static final double WALL_THICKNESS = (View.WIDTH - RoomBounds.WIDTH * View.UPSCALE_FACTOR) / 2;
    private static final double DOOR_W;
    private static final double DOOR_H;
    private static final double DISPLACEMENT = 5.0 / 6;

    static {
        /*
         * Determine the effective (in-game) pixel size of a door multiplying the
         * original size by the upscale factor, then perform some calculations to
         * determine where each door should be placed.
         */
        final Image reference = new Image("/door_open.png");
        DOOR_W = reference.getWidth() * View.UPSCALE_FACTOR;
        DOOR_H = reference.getHeight() * View.UPSCALE_FACTOR;
        final double widthMiddle = View.WIDTH / 2 - DOOR_W / 2;
        final double heightMiddle = View.HEIGHT / 2 - DOOR_H / 2;
        TOP = new Point2dImpl(widthMiddle, WALL_THICKNESS - DOOR_H * DISPLACEMENT);
        BOTTOM = new Point2dImpl(widthMiddle, View.HEIGHT - WALL_THICKNESS + DOOR_H * 1 / 4);
        LEFT = new Point2dImpl(WALL_THICKNESS - DOOR_W * DISPLACEMENT, heightMiddle);
        RIGHT = new Point2dImpl(View.WIDTH - WALL_THICKNESS + DOOR_W * 1 / 8, heightMiddle);
    }

    /* Don't let anyone instantiate this class. */
    private DoorPositions() {
    }
}
