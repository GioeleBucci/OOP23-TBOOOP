package tbooop.view;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
import tbooop.view.api.ViewComponent;
import tbooop.view.player.HealthView;

/**
 * The main view.
 */

@SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Passing modifiable view elements"
        + "is required to distribute the work load between the various view components.")
public final class ViewImpl extends Application implements View {
    /** The base width of the room. */
    public static final double BASE_ROOM_W = 468;
    /** The base height of the room. */
    public static final double BASE_ROOM_H = 311;

    private final Map<GameObject, ImageView> gameObjMap = new HashMap<>();
    private final Set<ViewComponent> viewComponents = new HashSet<>();

    private final Group root;
    private final Controller controller;
    private final InputManager inputManager;
    private Scene scene;
    private Rectangle walkableArea;
    private Stage stage;
    private double stageAspectRatio;

    /**
     * Constructs a new View.
     */
    public ViewImpl() {
        this.root = new Group();
        this.controller = new ControllerImpl(this);
        this.inputManager = new InputManager(controller, this);
    }

    @Override
    public void start(final Stage stage) {
        this.stage = stage;
        stage.setResizable(false);
        scene = new Scene(root, BASE_ROOM_W, BASE_ROOM_H);
        stage.setScene(scene);
        stage.setTitle("TBOOOP!");
        stage.show();

        setBackgroundImage("tileset/room.png");

        // Redirect keyboard events to the input manager
        scene.setOnKeyPressed(event -> {
            inputManager.handleInput(event.getCode());
            updateView();
        });

        setWalkableArea();

        final Thread thread = new Thread(() -> {
            controller.mainLoop();
        });
        thread.start();
        stageAspectRatio = stage.getWidth() / stage.getHeight();
        new DemoComponent(this).drawSquare();
        new HealthView(this).drawHeart(walkableArea);
    }

    /** {@inheritDoc} */
    @Override
    public void addGameObject(final GameObject gameObject) {
        /*
         * TODO usare una classe con la logica per far si che la scelta della sprite
         * dipenda dal tipo di GameObject!!
         */
        addGameObjectToView("down2.png", gameObject);
    }

    /** {@inheritDoc} */
    @Override
    public void update() {
        updateView();
        for (final var component: viewComponents) {
            component.update();
        }
    }

    /** {@inheritDoc} */
    @Override
    public Scene getScene() {
        return this.scene;
    }

    /** {@inheritDoc} */
    @Override
    public Stage getStage() {
        return this.stage;
    }

    /** {@inheritDoc} */
    @Override
    public Group getRoot() {
        return this.root;
    }

    /** {@inheritDoc} */
    @Override
    public double getStageAspectRatio() {
        return this.stageAspectRatio;
    }

    /**
     * The gameobjects use the walkable area for the movement calculations.
     * To correctly position a GameObject on the screen, this walkable area should
     * be used as bounding box.
     * 
     * @see RoomBounds
     */
    private void setWalkableArea() {
        final double upscaleFactor = scene.getWidth() / BASE_ROOM_W;
        walkableArea = new Rectangle(RoomBounds.WIDTH * upscaleFactor, RoomBounds.HEIGHT * upscaleFactor,
                Color.TRANSPARENT);
        // Bind the walkable area size to the scene's size
        walkableArea.widthProperty()
                .bind(scene.widthProperty().multiply(walkableArea.getWidth() / scene.widthProperty().get()));
        walkableArea.heightProperty()
                .bind(scene.heightProperty().multiply(walkableArea.getHeight() / scene.heightProperty().get()));
        root.getChildren().add(walkableArea);
    }

    /**
     * Updates the position of all the sprites.
     */
    private void updateView() {
        for (final var entry : gameObjMap.entrySet()) {
            Point2d newPos = worldToScreenPos(entry.getKey().getPosition());
            // subtract half the image size to center the image
            newPos = newPos.subtract(new Point2dImpl(
                    entry.getValue().getFitWidth() / 2,
                    entry.getValue().getFitHeight() / 2));
            final ImageView imgView = entry.getValue();
            imgView.setX(newPos.getX());
            imgView.setY(newPos.getY());
        }
        walkableArea.setTranslateX(scene.getWidth() / 2 - walkableArea.getWidth() / 2);
        walkableArea.setTranslateY(scene.getHeight() / 2 - walkableArea.getHeight() / 2);
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
        final double xWallThickness = (scene.getWidth() - walkableArea.getWidth()) / 2;
        final double yWallThickness = (scene.getHeight() - walkableArea.getHeight()) / 2;
        return new Point2dImpl(
                worldPos.getX() * walkableArea.getWidth() / RoomBounds.WIDTH + xWallThickness,
                worldPos.getY() * walkableArea.getHeight() / RoomBounds.HEIGHT + yWallThickness);
    }

    private void addGameObjectToView(final String pathToImg, final GameObject gobj) {
        final Image img = new Image(pathToImg);
        final ImageView imgView = new ImageView(img);
        gameObjMap.put(gobj, imgView);
        imgView.fitWidthProperty()
                .bind(walkableArea.widthProperty().multiply(img.getWidth() / walkableArea.widthProperty().get()));
        imgView.fitHeightProperty()
                .bind(walkableArea.heightProperty().multiply(img.getHeight() / walkableArea.heightProperty().get()));
        root.getChildren().add(imgView);
    }

    private void setBackgroundImage(final String imageUrl) {
        final ImageView backgroundImage = new ImageView(new Image(imageUrl));
        backgroundImage.fitWidthProperty().bind(scene.widthProperty());
        backgroundImage.fitHeightProperty().bind(scene.heightProperty());
        root.getChildren().add(backgroundImage);
    }
}
