package tbooop.view;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import tbooop.commons.RoomBounds;
import tbooop.view.api.View;

/**
 * The main view.
 */
@SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Passing modifiable view elements"
        + "is required to distribute the work load between the various view components.")
public abstract class AbstractView extends Application implements View {

    /** The base width of the room. */
    public static final double BASE_ROOM_W = 468;
    /** The base height of the room. */
    public static final double BASE_ROOM_H = 311;

    private final Group root = new Group();
    private volatile Scene scene;
    private volatile Rectangle walkableArea;
    private volatile Stage stage;
    private double stageAspectRatio;

    /** {@inheritDoc} */
    @Override
    public synchronized void start(final Stage stage) {
        this.stage = stage;
        stage.setResizable(false);
        scene = new Scene(root, BASE_ROOM_W * 2.0, BASE_ROOM_H * 2.0);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.setTitle("TBOOOP!");
        stage.getIcons().add(new Image("icon/icon.png")); 
        stage.show();
        setBackgroundImage("tileset/room.png");
        setWalkableArea();
        stageAspectRatio = stage.getWidth() / stage.getHeight();
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
    public synchronized double getStageAspectRatio() {
        return this.stageAspectRatio;
    }

    /**
     * The walkable area is the area where the gameobjects can move.
     * 
     * @return the walkable area
     */
    protected Rectangle getWalkableArea() {
        return this.walkableArea;
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

    private void setBackgroundImage(final String imageUrl) {
        final ImageView backgroundImage = new ImageView(new Image(imageUrl));
        backgroundImage.fitWidthProperty().bind(scene.widthProperty());
        backgroundImage.fitHeightProperty().bind(scene.heightProperty());
        root.getChildren().add(backgroundImage);
    }

}
