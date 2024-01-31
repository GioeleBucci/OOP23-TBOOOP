package tbooop.view;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import tbooop.commons.HealthImpl;
import tbooop.commons.Point2dImpl;
import tbooop.commons.RoomBounds;
import tbooop.commons.api.Point2d;
import tbooop.controller.ControllerImpl;
import tbooop.controller.api.Controller;
import tbooop.model.core.api.GameObject;
import tbooop.model.dungeon.rooms.api.Room;
import tbooop.model.player.impl.PlayerImpl;
import tbooop.view.api.View;

/**
 * The main view.
 */
public final class ViewImpl extends Application implements View {

    private final Map<GameObject, ImageView> map = new HashMap<>();

    private final Logger logger = Logger.getLogger(ViewImpl.class.getName());
    private static final double INITIAL_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();
    private static final double INITIAL_WIDTH = INITIAL_HEIGHT * RoomBounds.WIDTH / RoomBounds.HEIGHT;
    private final Group root = new Group();
    private final Controller controller = new ControllerImpl(this);
    private Scene scene;
    private InputManager inputManager;
    private static final double BASE_ROOM_W = 468;
    private static final double WALL_THICKNESS = BASE_ROOM_W - RoomBounds.WIDTH / 2;
    private Rectangle rect;

    @Override
    public void start(final Stage primaryStage) {

        this.inputManager = new InputManager(controller, primaryStage);

        primaryStage.setResizable(false);

        // Create a scene
        scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
        setBackgroundImage("tileset/room.png");

        // Add event handlers for key presses
        scene.setOnKeyPressed(event -> {
            inputManager.handleInput(event.getCode());
            updateView();
        });

        // TEST
        double upscaleFactor = scene.getWidth() / 468;
        rect = new Rectangle(RoomBounds.WIDTH * upscaleFactor, RoomBounds.HEIGHT * upscaleFactor, Color.RED);
        // rect.setX(scene.getWidth() / 2 - rect.getWidth() / 2);
        // rect.setY(scene.getHeight() / 2 - rect.getHeight() / 2);

        // Bind the image view's size to the scene's size
        rect.widthProperty()
                .bind(scene.widthProperty().multiply(rect.getWidth() / scene.widthProperty().get()));
        rect.heightProperty()
                .bind(scene.heightProperty().multiply(rect.getHeight() / scene.heightProperty().get()));

        // Add the image view to the layout pane
        root.getChildren().add(rect);

        //// END TEST

        // Set the scene on the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("TBOOOP!");
        primaryStage.show();

        final Thread thread = new Thread(() -> {
            controller.mainLoop();
        });
        thread.start();
    }

    /**
     * Updates the position of all the image views.
     */
    private void updateView() {
        for (final var entry : map.entrySet()) {
            final Point2d newPos = worldToScreenPos(entry.getKey().getPosition());
            final ImageView imgView = entry.getValue();
            imgView.setTranslateX(newPos.getX() - imgView.getX() - imgView.getImage().getWidth() / 2);
            imgView.setTranslateY(newPos.getY() - imgView.getY() - imgView.getImage().getHeight() / 2);
        }
        rect.setTranslateX(scene.getWidth() / 2 - rect.getWidth() / 2);
        rect.setTranslateY(scene.getHeight() / 2 - rect.getHeight() / 2);
    }

    /**
     * Converts a world position (position of a GameObject in the model)
     * to a screen position (position inside the window).
     * 
     * @param worldPos the world position of a GameObject
     * @return the screen position
     */
    private Point2d worldToScreenPos(final Point2d worldPos) {
        /*
         * To perform the conversion (for one axis) the following proportion can be
         * used:
         * RoomBounds.WIDTH : scene.getWidth() = gameObject.getPosition().getX() : x
         */
        return new Point2dImpl(
                worldPos.getX() * scene.getWidth() / RoomBounds.WIDTH,
                worldPos.getY() * scene.getHeight() / RoomBounds.HEIGHT);
    }

    private void addToMap(final String pathToImg, final GameObject gobj) {

        // Create an image view
        final Image img = new Image(pathToImg);
        final ImageView imgView = new ImageView(img);

        map.put(gobj, imgView);

        // // Set the translation
        // imgView.setX(position.getX());
        // imgView.setY(position.getY());

        final Point2d startPos = worldToScreenPos(gobj.getPosition());

        // Bind the image view's position to the scene's size
        imgView.xProperty().bind(scene.widthProperty()
                .multiply(startPos.getX() / scene.widthProperty().get()));
        imgView.yProperty().bind(scene.heightProperty()
                .multiply(startPos.getY() / scene.heightProperty().get()));

        // Bind the image view's size to the scene's size
        imgView.fitWidthProperty()
                .bind(scene.widthProperty().multiply(img.getWidth() / scene.widthProperty().get()));
        imgView.fitHeightProperty()
                .bind(scene.heightProperty().multiply(img.getHeight() / scene.heightProperty().get()));

        // Add the image view to the layout pane
        root.getChildren().add(imgView);
    }

    @Override
    public void addGameObject(final GameObject gameObject) {
        addToMap("down2.png", gameObject);
    }

    @Override
    public void update() {
        updateView();
    }

    private void setBackgroundImage(String imageUrl) {
        Image backgroundImage = new Image(imageUrl);
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.fitWidthProperty().bind(scene.widthProperty());
        backgroundImageView.fitHeightProperty().bind(scene.heightProperty());
        root.getChildren().add(backgroundImageView);
    }
}
