package tbooop.view;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import tbooop.commons.Point2dImpl;
import tbooop.commons.RoomBounds;
import tbooop.commons.api.Point2d;
import tbooop.controller.ControllerImpl;
import tbooop.controller.api.Controller;
import tbooop.model.core.api.GameObject;
import tbooop.model.core.api.GameObjectUnmodifiable;
import tbooop.model.player.api.UnmodifiablePlayer;
import tbooop.model.dungeon.rooms.api.RoomUnmodifiable;
import tbooop.view.api.View;
import tbooop.view.api.ViewComponent;
import tbooop.view.enemy.EnemyAnimator;
import tbooop.view.player.HealthView;
import tbooop.view.player.PlayerRender;

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

    private volatile Map<GameObjectUnmodifiable, ImageView> gameObjMap = new HashMap<>();
    private final Set<ViewComponent> viewComponents = new HashSet<>();
    private final RoomRenderer roomRenderer;
    private final EnemyAnimator enemyAnimator;

    private boolean isMoving;

    private final Group root;
    private final Controller controller;
    private final InputManager inputManager;
    private volatile Scene scene;
    private volatile Rectangle walkableArea;
    private volatile Stage stage;
    private double stageAspectRatio;

    /**
     * Constructs a new View.
     */
    public ViewImpl() {
        this.root = new Group();
        this.controller = new ControllerImpl(this);
        this.inputManager = new InputManager(controller, this);
        this.enemyAnimator = new EnemyAnimator(gameObjMap);
        this.roomRenderer = new RoomRenderer(this);
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

            if (event.getCode() == Keybinds.UP.getKeyCode()
                    || event.getCode() == Keybinds.DOWN.getKeyCode()
                    || event.getCode() == Keybinds.LEFT.getKeyCode()
                    || event.getCode() == Keybinds.RIGHT.getKeyCode()) {
                this.isMoving = true;
            }

            updateView();
        });

        setWalkableArea();

        final Thread thread = new Thread(() -> {
            controller.mainLoop();
        });
        thread.start();
        stageAspectRatio = stage.getWidth() / stage.getHeight();

        new HealthView(this).drawHeart(walkableArea);
        roomRenderer.init();
    }

    /** {@inheritDoc} */
    @Override
    public void addPlayer(final UnmodifiablePlayer player) {
        final PlayerRender playerRender = new PlayerRender(this, player);
        viewComponents.add(playerRender);
        addGameObjectToView(playerRender.getSprite(), player);
    }

    /** {@inheritDoc} */
    @Override
    public void addGameObject(final GameObjectUnmodifiable gameObject) {
        /*
         * TODO usare una classe con la logica per far si che la scelta della sprite
         * dipenda dal tipo di GameObject!!
         */
        addGameObjectToView(new ImageView(new Image("down2.png")), gameObject);
    }

    private synchronized void attachDebugger(final GameObjectUnmodifiable gameObject) {
        final Circle circle = new Circle();
        circle.setRadius(gameObject.getCollider().getRadius() * scene.getWidth() / BASE_ROOM_W);
        circle.setStroke(Color.BLUE);
        circle.setFill(Color.TRANSPARENT);
        circle.setStrokeWidth(2);
        final ImageView img = gameObjMap.get(gameObject);
        circle.centerXProperty().bind(img.xProperty().add(img.fitWidthProperty().divide(2)));
        circle.centerYProperty().bind(img.yProperty().add(img.fitHeightProperty().divide(2)));
        circle.radiusProperty().bind(Bindings.multiply(gameObject.getCollider().getRadius(),
                Bindings.createDoubleBinding(() -> scene.getWidth() / BASE_ROOM_W, scene.widthProperty())));
        root.getChildren().add(circle);
    }

    /** {@inheritDoc} */
    @Override
    public void update() {

        updateView();
        for (final ViewComponent viewComponent : viewComponents) {
            if (viewComponent instanceof PlayerRender) {
                if (isMoving) {
                    viewComponent.update();
                    this.isMoving = false;
                }
            } else {
                viewComponent.update();
            }
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
    private synchronized void updateView() {
        enemyAnimator.update();
        for (final var entry : gameObjMap.entrySet()) {
            Point2d newPos = worldToScreenPos(entry.getKey().getPosition());
            // subtract half the image size to center the image
            newPos = newPos.subtract(new Point2dImpl(
                    entry.getValue().getFitWidth() / 2,
                    entry.getValue().getFitHeight() / 2));
            final ImageView imgView = entry.getValue();
            imgView.setX(newPos.getX());
            imgView.setY(newPos.getY());
            if (entry.getKey().isDestroyed()) {
                entry.getValue().setVisible(false); // TODO this does not actually remove the projectile
                root.getChildren().remove(entry.getValue());
                // gameObjMap.remove(entry.getKey());
            }
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
    private synchronized Point2d worldToScreenPos(final Point2d worldPos) {
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

    /**
     * Adds a GameObject to the view.
     * 
     * @param imgView the ImageView of the GameObject
     * @param gobj    the GameObject to add
     */
    protected synchronized void addGameObjectToView(final ImageView imgView, final GameObjectUnmodifiable gobj) {
        gameObjMap.put(gobj, imgView);
        imgView.fitWidthProperty()
                .bind(walkableArea.widthProperty()
                        .multiply(imgView.getImage().getWidth() / walkableArea.widthProperty().get()));
        imgView.fitHeightProperty()
                .bind(walkableArea.heightProperty()
                        .multiply(imgView.getImage().getHeight() / walkableArea.heightProperty().get()));
        root.getChildren().add(imgView);
        // attachDebugger(gobj);
    }

    private void setBackgroundImage(final String imageUrl) {
        final ImageView backgroundImage = new ImageView(new Image(imageUrl));
        backgroundImage.fitWidthProperty().bind(scene.widthProperty());
        backgroundImage.fitHeightProperty().bind(scene.heightProperty());
        root.getChildren().add(backgroundImage);
    }

    /** {@inheritDoc} */
    @Override
    public void removeGameObject(final GameObject gameObject) {
        root.getChildren().remove(gameObjMap.get(gameObject));
        gameObjMap.remove(gameObject);
    }

    @Override
    public void changeRoom(final RoomUnmodifiable newRoom) {
        roomRenderer.changeRoom(newRoom);
    }
}