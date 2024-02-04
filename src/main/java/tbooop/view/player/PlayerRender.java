package tbooop.view.player;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import tbooop.commons.Point2dImpl;
import tbooop.commons.Point2ds;
import tbooop.commons.api.Point2d;
import tbooop.model.player.api.UnmodifiablePlayer;
import tbooop.view.api.ViewComponent;
import tbooop.view.api.ViewElements;

/** Renders a Player. */
public class PlayerRender extends ViewComponent {
    private final Point2d startingPlayerPoint;
    private final PlayerRenderSprite playerRenderSprite = new PlayerRenderSprite();
    private ImageView playerSprite = new ImageView();
    private UnmodifiablePlayer player;
    private final Rectangle walkableArea;
    /** 
     * @param view the root this attaches to.
     * @param player one Unmodifiable Player to set the animation sprite.
     * @param walkableArea the game field.
     */
    public PlayerRender(final ViewElements view, final UnmodifiablePlayer player, final Rectangle walkableArea) {
        super(view);
        startingPlayerPoint = new Point2dImpl(
            view.getRoot().getScene().getWidth() / 2, 
            view.getRoot().getScene().getHeight() / 2);

        this.player = player;
        this.walkableArea = walkableArea;
        init();
    }

    private void init() {
        this.playerSprite = new ImageView("Player/down/down1.png");
        addToRoot(playerSprite);

        /*playerSprite.fitWidthProperty()
            .bind(walkableArea
            .widthProperty()
            .multiply(playerSprite.getImage().getWidth() / walkableArea.widthProperty().get()));

        playerSprite.fitHeightProperty()
            .bind(walkableArea
            .heightProperty()
            .multiply(playerSprite.getImage().getHeight() / walkableArea.heightProperty().get()));
        */
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
        if (player.getPoint2ds().isPresent()) {
            move(player.getPoint2ds().get());
        }
    }

    /**
     * for get the PlayerSprite.
     * @return playerSprite
     */
    public ImageView getSprite() {
        return this.playerSprite;
    }
}
