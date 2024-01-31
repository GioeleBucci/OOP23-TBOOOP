package tbooop.view;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import tbooop.commons.Point2dImpl;
import tbooop.commons.RoomBounds;
import tbooop.commons.api.Point2d;
import tbooop.controller.ControllerImpl;
import tbooop.controller.api.Controller;
import tbooop.model.core.api.GameObject;
import tbooop.view.api.View;

/**
 * The main view.
 */
public final class ViewImpl extends Application implements View {

    private final Map<GameObject, ImageView> map = new HashMap<>();

    private final Logger logger = Logger.getLogger(ViewImpl.class.getName());
    public static final double BASE_ROOM_W = 468;
    public static final double BASE_ROOM_H = 311;

    private final Group root = new Group();
    private final Controller controller = new ControllerImpl(this);
    private Scene scene;
    private InputManager inputManager;
    private Rectangle rect;
    private double barsize;
    private Stage stage;
    private double stageAspectRatio;

    public double getStageAspectRatio() {
        return stageAspectRatio;
    }

    @Override
    public void start(final Stage primaryStage) {

        

        this.stage = primaryStage;

        primaryStage.setResizable(false);

        // Create a scene
        scene = new Scene(root, BASE_ROOM_W, BASE_ROOM_H);

        setBackgroundImage("tileset/room.png");

        this.inputManager = new InputManager(controller, this);

        // Add event handlers for key presses
        scene.setOnKeyPressed(event -> {
            inputManager.handleInput(event.getCode());
            updateView();
        });

        // TEST
        double upscaleFactor = scene.getWidth() / BASE_ROOM_W;
        rect = new Rectangle(RoomBounds.WIDTH * upscaleFactor, RoomBounds.HEIGHT * upscaleFactor, Color.TRANSPARENT);

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

        /// TEST CALCULATE WINDOW BAR SIZE
        barsize = primaryStage.getHeight() - scene.getHeight();
        System.out.println("barsize is " + barsize);
        /// END TEST

        final Thread thread = new Thread(() -> {
            controller.mainLoop();
        });
        thread.start();

        logger.info("w: " + scene.getWidth());
        logger.info("w: " + primaryStage.getWidth());
        stageAspectRatio = stage.getWidth() / stage.getHeight();
    }

    /**
     * Updates the position of all the image views.
     */
    private void updateView() {
        for (final var entry : map.entrySet()) {
            Point2d newPos = worldToScreenPos(entry.getKey().getPosition());
            // subtract half the image size to center the image
            newPos = newPos.subtract(new Point2dImpl(
                    entry.getValue().getFitWidth() / 2,
                    entry.getValue().getFitHeight() / 2));
            final ImageView imgView = entry.getValue();
            logger.info("Model pos:" + entry.getKey().getPosition().toString()
                    + "\nScreen pos: " + newPos.toString()
                    + "\nImage size: " + entry.getValue().getFitWidth() + "x" + entry.getValue().getFitHeight()
                    + "\nScreen size: " + scene.getWidth() + "x" + scene.getHeight());
            imgView.setX(newPos.getX()); // - imgView.getX() - imgView.getImage().getWidth() / 2);
            imgView.setY(newPos.getY()); // - imgView.getY() - imgView.getImage().getHeight() / 2);
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
        var xThickness = (scene.getWidth() - rect.getWidth()) / 2;
        var yThickness = (scene.getHeight() - rect.getHeight()) / 2;
        return new Point2dImpl(
                worldPos.getX() * rect.getWidth() / RoomBounds.WIDTH + xThickness,
                worldPos.getY() * rect.getHeight() / RoomBounds.HEIGHT + yThickness);
    }

    private void addToMap(final String pathToImg, final GameObject gobj) {

        // Create an image view
        final Image img = new Image(pathToImg);
        final ImageView imgView = new ImageView(img);

        map.put(gobj, imgView);

        // Bind the image view's size to the scene's size
        imgView.fitWidthProperty()
                .bind(rect.widthProperty().multiply(img.getWidth() / rect.widthProperty().get()));
        imgView.fitHeightProperty()
                .bind(rect.heightProperty().multiply(img.getHeight() / rect.heightProperty().get()));

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

    public double getBarsize() {
        return barsize;
    }

    public Scene getScene() {
        return scene;
    }

    public Stage getStage() {
        return stage;
    }
}
