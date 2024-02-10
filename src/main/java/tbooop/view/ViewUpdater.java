package tbooop.view;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.beans.binding.Bindings;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import tbooop.commons.Point2dImpl;
import tbooop.commons.RoomBounds;
import tbooop.commons.api.Point2d;
import tbooop.controller.ControllerImpl;
import tbooop.controller.api.Controller;
import tbooop.model.core.api.GameObject;
import tbooop.model.core.api.GameObjectUnmodifiable;
import tbooop.model.dungeon.rooms.api.RoomUnmodifiable;
import tbooop.model.player.api.UnmodifiablePlayer;
import tbooop.view.api.BaseSpriteProvider;
import tbooop.view.api.ViewComponent;
import tbooop.view.api.enemy.EnemyAnimator;
import tbooop.view.enemy.EnemyAnimatorImpl;
import tbooop.view.pickupables.pickups.PlayerCoinsView;
import tbooop.view.pickupables.pickups.PlayerKeysView;
import tbooop.view.player.HealthView;
import tbooop.view.player.PlayerRender;

/**
 * The ViewUpdater pdates the GUI and communicates with the controller.
 */
public class ViewUpdater extends ViewImpl {

    private static final double MULTIPLIER_SCALE = 0.9;

    private volatile Map<GameObjectUnmodifiable, ImageView> gameObjMap = new HashMap<>();
    private final Set<ViewComponent> viewComponents = new HashSet<>();
    private final RoomRenderer roomRenderer;
    private final EnemyAnimator enemyAnimator;
    private final BaseSpriteProvider spriteLoader = new BaseSpriteProviderImpl();
    private final Controller controller;
    private final InputManager inputManager;
    private boolean isMoving;

    /** Creates an instance of a ViewUpdater.  */
    public ViewUpdater() {
        this.controller = new ControllerImpl(this);
        this.enemyAnimator = new EnemyAnimatorImpl(gameObjMap);
        this.roomRenderer = new RoomRenderer(this);
        this.inputManager = new InputManager(controller, this);
    }

    /** {@inheritDoc} */
    @Override
    public synchronized void start(final Stage stage) {
        super.start(stage);
        // Redirect keyboard events to the input manager

        super.getScene().setOnKeyPressed(e -> inputManager.keyPressed(e.getCode()));
        super.getScene().setOnKeyReleased(e -> inputManager.keyReleased(e.getCode()));

        roomRenderer.init();

        final Thread thread = new Thread(() -> {
            controller.mainLoop();
        });
        thread.start();
    }

    /** {@inheritDoc} */
    @Override
    public void addPlayer(final UnmodifiablePlayer player) {
        final PlayerRender playerRender = new PlayerRender(this, player);
        viewComponents.add(playerRender);
        addGameObjectToView(playerRender.getSprite(), player);
        final HealthView healthView = new HealthView(this, player);
        viewComponents.add(healthView);
        final PlayerKeysView keysView = new PlayerKeysView(this, player);
        viewComponents.add(keysView);
        final PlayerCoinsView coinsView = new PlayerCoinsView(this, player);
        viewComponents.add(coinsView);
    }

    /** {@inheritDoc} */
    @Override
    public void update() {
        inputManager.update();
        updateView();
        for (final ViewComponent viewComponent : viewComponents) {
            if (viewComponent instanceof PlayerRender) {
                // prevent player from being covered by other sprites
                ((PlayerRender) viewComponent).getSprite().toFront();
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
    public void addGameObject(final GameObjectUnmodifiable gameObject) {
        addGameObjectToView(this.spriteLoader.getGameObjectSprite(gameObject), gameObject);
    }

    /** {@inheritDoc} */
    @Override
    public void removeGameObject(final GameObject gameObject) {
        super.getRoot().getChildren().remove(gameObjMap.get(gameObject));
        gameObjMap.remove(gameObject);
    }

    /** {@inheritDoc} */
    @Override
    public void refreshRoom(final RoomUnmodifiable newRoom) {
        roomRenderer.changeRoom(newRoom);
    }

    /** {@inheritDoc} */
    @Override
    public void changeFloor() {
        roomRenderer.changeFloor();
    }

    /**
     * Adds a GameObject to the view.
     * 
     * @param imgView the ImageView of the GameObject
     * @param gobj    the GameObject to add
     */
    protected synchronized void addGameObjectToView(final ImageView imgView, final GameObjectUnmodifiable gobj) {
        gameObjMap.put(gobj, imgView);
        imgView.fitWidthProperty().bind(super.getWalkableArea().widthProperty().multiply(
                imgView.getImage().getWidth() * (super.getScene().getWidth() / BASE_ROOM_W * MULTIPLIER_SCALE)
                        / super.getWalkableArea().widthProperty().get()));
        imgView.fitHeightProperty().bind(super.getWalkableArea().heightProperty().multiply(
                imgView.getImage().getHeight() * (super.getScene().getHeight() / BASE_ROOM_H * MULTIPLIER_SCALE)
                        / super.getWalkableArea().heightProperty().get()));
        super.getRoot().getChildren().add(imgView);
        // attachDebugger(gobj);
    }

    private synchronized void attachDebugger(final GameObjectUnmodifiable gameObject) { //NOPMD temporary method.
        final Circle circle = new Circle();
        circle.setRadius(gameObject.getCollider().getRadius() * super.getScene().getWidth() / BASE_ROOM_W);
        circle.setStroke(Color.BLUE);
        circle.setFill(Color.TRANSPARENT);
        circle.setStrokeWidth(2);
        final ImageView img = gameObjMap.get(gameObject);
        circle.centerXProperty().bind(img.xProperty().add(img.fitWidthProperty().divide(2)));
        circle.centerYProperty().bind(img.yProperty().add(img.fitHeightProperty().divide(2)));
        circle.radiusProperty().bind(Bindings.multiply(gameObject.getCollider().getRadius(),
                Bindings.createDoubleBinding(() -> super.getScene().getWidth() / BASE_ROOM_W, super.getScene().widthProperty())));
        super.getRoot().getChildren().add(circle);
    }

    /** Updates the position of all the sprites. */
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
                super.getRoot().getChildren().remove(entry.getValue());
                // gameObjMap.remove(entry.getKey());
            }
        }
        super.getWalkableArea().setTranslateX(super.getScene().getWidth() / 2 - super.getWalkableArea().getWidth() / 2);
        super.getWalkableArea().setTranslateY(super.getScene().getHeight() / 2 - super.getWalkableArea().getHeight() / 2);
    }

    /**
     * Converts a world position (position of a GameObject in the model)
     * to a screen position (position inside the window).
     * To perform the conversion (for one axis) the following proportion can be used:
     * <p>
     * RoomBounds.WIDTH : scene.getWidth() = gameObject.getPosition().getX() : x
     *
     * @param worldPos the world position of a GameObject
     * @return the screen position
     */
    private synchronized Point2d worldToScreenPos(final Point2d worldPos) {
        final double xWallThickness = (super.getScene().getWidth() - super.getWalkableArea().getWidth()) / 2;
        final double yWallThickness = (super.getScene().getHeight() - super.getWalkableArea().getHeight()) / 2;
        return new Point2dImpl(
                worldPos.getX() * super.getWalkableArea().getWidth() / RoomBounds.WIDTH + xWallThickness,
                worldPos.getY() * super.getWalkableArea().getHeight() / RoomBounds.HEIGHT + yWallThickness);
    }

}
