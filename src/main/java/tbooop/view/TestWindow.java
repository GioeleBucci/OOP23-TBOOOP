package tbooop.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import tbooop.commons.HealthImpl;
import tbooop.commons.Point2dImpl;
import tbooop.commons.RoomBounds;
import tbooop.commons.api.Point2d;
import tbooop.model.core.api.GameObject;
import tbooop.model.core.api.GameTag;
import tbooop.model.enemy.impl.EnemyFactoryImpl;
import tbooop.model.pickupables.pickups.impl.Heart;
import tbooop.model.pickupables.pickups.impl.PickupsLogic;
import tbooop.model.player.impl.PlayerImpl;

public class TestWindow extends Application {

    private Map<GameObject, ImageView> map = new HashMap<>();

    private static final double WINDOW_WIDTH = 728;
    private static final double WINDOW_HEIGHT = WINDOW_WIDTH * 9 / 16;
    private double WINDOW_SIZE_CHANGE = WINDOW_WIDTH / 10;
    private final Group root = new Group();
    private Scene scene;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);

        // Create a scene
        scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        // TEST
        GameObject gobj = new PlayerImpl(Point2dImpl.ZERO, new HealthImpl(4), 4);
        gobj.setPosition(new Point2dImpl(RoomBounds.WIDTH / 3, RoomBounds.HEIGHT / 3));
        System.out.println("world position: " + gobj.getPosition());
        System.out.println("screen position: " + worldToScreenPos(gobj.getPosition()));

        addToMap("down2.png", gobj);

        // Add event handlers for key presses
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.PLUS) {
                primaryStage.setWidth(primaryStage.getWidth() + WINDOW_SIZE_CHANGE);
                primaryStage.setHeight(primaryStage.getWidth() * 9 / 16);
            } else if (event.getCode() == KeyCode.MINUS) {
                primaryStage.setWidth(primaryStage.getWidth() - WINDOW_SIZE_CHANGE);
                primaryStage.setHeight(primaryStage.getWidth() * 9 / 16);
            } else if (event.getCode() == KeyCode.F) {
                primaryStage.setFullScreen(!primaryStage.isFullScreen());
            } else if (event.getCode() == KeyCode.W) {
                gobj.setPosition(gobj.getPosition().add(new Point2dImpl(0, -10)));
            } else if (event.getCode() == KeyCode.S) {
                gobj.setPosition(gobj.getPosition().add(new Point2dImpl(0, 10)));
            } else if (event.getCode() == KeyCode.A) {
                gobj.setPosition(gobj.getPosition().add(new Point2dImpl(-10, 0)));
            } else if (event.getCode() == KeyCode.D) {
                gobj.setPosition(gobj.getPosition().add(new Point2dImpl(10, 0)));
            }
            updateMap();
        });

        // Set the scene on the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Window");
        primaryStage.show();
    }

    private void updateMap() {
        for (var entry : map.entrySet()) {
            Point2d newPos = worldToScreenPos(entry.getKey().getPosition());
            entry.getValue().setTranslateX(newPos.getX() - entry.getValue().getX());
            entry.getValue().setTranslateY(newPos.getY() - entry.getValue().getY());
        }
    }

    private Point2d worldToScreenPos(Point2d worldPos) {
        // RoomBounds.WIDTH : scene.getWidth() = gameObject.getPosition().getX() : x
        // (coord on screen)
        return new Point2dImpl(
                worldPos.getX() * scene.getWidth() / RoomBounds.WIDTH,
                worldPos.getY() * scene.getHeight() / RoomBounds.HEIGHT);
    }

    private void addToMap(String pathToImg, GameObject gobj) {

        // Create an image view
        Image img = new Image(pathToImg);
        ImageView imgView = new ImageView(img);

        map.put(gobj, imgView);

        // // Set the translation
        // imgView.setX(position.getX());
        // imgView.setY(position.getY());

        // Bind the image view's position to the scene's size

        Point2d startPos = worldToScreenPos(gobj.getPosition());

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
}