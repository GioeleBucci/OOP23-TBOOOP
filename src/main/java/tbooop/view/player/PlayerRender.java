package tbooop.view.player;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import tbooop.commons.Point2dImpl;
import tbooop.commons.Point2ds;
import tbooop.commons.api.Point2d;
import tbooop.view.api.ViewComponent;
import tbooop.view.api.ViewElements;

/** Renders a Player. */
public class PlayerRender extends ViewComponent {
    private static final double PLAYER_SCALE = 1.5;
    private final Group root = new Group();
    private final Point2d startingPlayerPoint;
    private final PlayerRenderSprite playerRenderSprite = new PlayerRenderSprite();
    private ImageView playerSprite = new ImageView();

    /** 
     * @param view the root this attaches to
     */
    public PlayerRender(final ViewElements view) {
        super(view);
        startingPlayerPoint = new Point2dImpl(
            view.getRoot().getScene().getWidth() / 2, 
            view.getRoot().getScene().getHeight() / 2);

        init();
    }

    private void init() {
        this.playerSprite = new ImageView("Player/down/down1.png");
        root.getChildren().add(playerSprite);
        root.getScene().getWindow().getWidth();
        playerSprite.setScaleX(PLAYER_SCALE);
        playerSprite.setScaleY(PLAYER_SCALE);
        playerSprite.setX(this.startingPlayerPoint.getX());
        playerSprite.setY(this.startingPlayerPoint.getY());
    }

    /**
     * Move the sprite in one of four direction.
     * @param direction the direction (UP,DOWN,LEFT,RIGHT).
     */
    public void move(final Point2ds direction) {
        switch (direction) {
            case DOWN:
                this.playerRenderSprite.goDown(playerSprite);
                break;
            case UP:
                this.playerRenderSprite.goUp(playerSprite);
                break;
            case LEFT:
                this.playerRenderSprite.goLeft(playerSprite);
                break;
            case RIGHT:
                this.playerRenderSprite.goRight(playerSprite);
                break;
            default:
                break;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void update() {

    }
}
