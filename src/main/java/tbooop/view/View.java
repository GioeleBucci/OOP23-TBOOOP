package tbooop.view;

import java.util.logging.Logger;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tbooop.commons.api.Point2d;
import tbooop.commons.Point2ds;
import tbooop.controller.Controller;
import tbooop.model.dungeon.rooms.api.Door;

import java.util.Map;

/** View. */
public class View extends Application {

    /**
     * How much big the window is, compared to the native screen resolution.
     * <p>
     * The value is a percentage, so it must be between {@code 0.0} and {@code 1.0}.
     * </p>
     * <p>
     * <i>Note</i>: Setting this value at {@code 1.0} will maximize the window,
     * keeping the intended aspect ratio for the game, (so it doesn't mean
     * fullscreen).
     * </p>
     */
    private static final double SCALE_PERCENTAGE = 0.7;

    /** The width of this window. */
    public static final double WIDTH;
    /** The height of this window. */
    public static final double HEIGHT;
    /**
     * Indicates how much images needs to be scaled to be correctly rendered.
     * 
     * @implNote This value is calculated at runtime, based on the native screen
     *           using the following formula:
     *           {@code WIDTH / reference.getWidth()}, where {@code reference} is
     *           the background image.
     */
    public static final double UPSCALE_FACTOR;

    private RoomRenderer roomRenderer;
    private final Controller controller = new Controller(this);
    private final Logger logger = Logger.getLogger(View.class.getName());
    private final Group root = new Group();

    static {
        // Retrieve the native screen resolution
        final Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        final double screenHeight = bounds.getHeight();

        // the following reference image will be used to correctly scale the window
        final Image reference = new Image("/room.png");
        final double aspectRatio = reference.getWidth() / reference.getHeight();
        HEIGHT = screenHeight * SCALE_PERCENTAGE;
        WIDTH = HEIGHT * aspectRatio; // maintain same aspect ratio
        UPSCALE_FACTOR = WIDTH / reference.getWidth();
    }

    /** {@inheritDoc} */
    @Override
    public void init() throws Exception {
        logger.info("View thread started");
        logger.info("Window resolution set at: " + (int) WIDTH + "x" + (int) HEIGHT
                + ".\n(Upscale factor = " + UPSCALE_FACTOR + ")");
    }

    /** {@inheritDoc} */
    @Override
    public void start(final Stage stage) throws Exception {
        stage.initStyle(StageStyle.UNIFIED);

        final Image img = new Image("/room.png");
        final ImageView background = new ImageView(img);
        background.fitWidthProperty().bind(stage.widthProperty());
        background.fitHeightProperty().bind(stage.heightProperty());
        root.getChildren().add(background);

        final Scene scene = new Scene(root, WIDTH, HEIGHT);

        stage.setTitle("Demo Scene");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        this.roomRenderer = new RoomRenderer(root);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                logger.info("DEBUG: resetting");
                controller.changeFloor();
            }
            if (event.getCode() == KeyCode.W) {
                controller.changeRoom(Point2ds.UP);
            }
            if (event.getCode() == KeyCode.S) {
                controller.changeRoom(Point2ds.DOWN);
            }
            if (event.getCode() == KeyCode.A) {
                controller.changeRoom(Point2ds.LEFT);
            }
            if (event.getCode() == KeyCode.D) {
                controller.changeRoom(Point2ds.RIGHT);
            }
        });
    }

    /**
     * Used by the controller to swap rooms.
     * 
     * @param newPoint the new room position
     * @param doorMap  the door map of the room
     */
    public void changeRooms(final Point2d newPoint, final Map<Point2ds, Door> doorMap) {
        roomRenderer.changeRoom(newPoint, doorMap);
    }
}
