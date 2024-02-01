package tbooop.view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import tbooop.commons.HealthImpl;
import tbooop.commons.Point2dImpl;
import tbooop.commons.api.Health;
import tbooop.commons.api.Point2d;
import tbooop.model.player.api.Player;
import tbooop.model.player.impl.PlayerImpl;

/** Renders a Player. */
public class PlayerRender {
    private final Group root = new Group();
    private final Point2d startingPlayerPoint;

    /** 
     * @param parentRoot the root this attaches to
     */
    public PlayerRender(final Group parentRoot) {
        parentRoot.getChildren().add(this.root);
        startingPlayerPoint = new Point2dImpl(
            parentRoot.getScene().getHeight() / 2, 
            parentRoot.getScene().getWidth() / 2);

        init();
    }

    private void init() {
        final Health hp = new HealthImpl(5);
        final Player player = new PlayerImpl(startingPlayerPoint, hp, 2);
        final Circle cliclePlayer = new Circle(player.getCollider().getRadius(), Color.ALICEBLUE);
        cliclePlayer.setCenterX(startingPlayerPoint.getX());
        cliclePlayer.setCenterY(startingPlayerPoint.getY());
        this.root.getChildren().add(cliclePlayer);
    }
}

