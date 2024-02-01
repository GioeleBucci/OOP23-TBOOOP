package tbooop.view;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tbooop.commons.Point2dImpl;
import tbooop.commons.api.Point2d;

/** Renders a Player. */
public class PlayerRender {
    private final Group root = new Group();
    private final Point2d startingPlayerPoint;
    private ImageView playerSprite = new ImageView();

    /** 
     * @param parentRoot the root this attaches to
     */
    public PlayerRender(final Group parentRoot) {
        parentRoot.getChildren().add(this.root);
        startingPlayerPoint = new Point2dImpl(
            parentRoot.getScene().getWidth() / 2, 
            parentRoot.getScene().getHeight() / 2);

        init();
    }

    private void init() {
        this.playerSprite = new ImageView("Player/down/down1.png");
        root.getChildren().add(playerSprite);
        root.getScene().getWindow().getWidth();
        playerSprite.setScaleX(1.5);
        playerSprite.setScaleY(1.5);
        playerSprite.setX(this.startingPlayerPoint.getX());
        playerSprite.setY(this.startingPlayerPoint.getY());
    }

    public void goDown(Point2d newPos) {
        this.playerSprite.setX(newPos.getX());
        this.playerSprite.setY(newPos.getY());
    }
}

