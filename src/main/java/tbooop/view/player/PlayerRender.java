package tbooop.view.player;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.scene.image.ImageView;
import tbooop.commons.Point2ds;
import tbooop.model.player.api.UnmodifiablePlayer;
import tbooop.view.api.ViewComponent;
import tbooop.view.api.ViewElements;

/** Renders a Player. */
@SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Passing modifiable view elements"
        + "is required to distribute the work load between the various view components.")
public class PlayerRender extends ViewComponent {

    private final PlayerAnimator playerRenderSprite = new PlayerAnimator();
    private final ImageView playerSprite;
    private final UnmodifiablePlayer player;

    /**
     * @param view   the root this attaches to.
     * @param player one Unmodifiable Player to set the animation sprite.
     */
    public PlayerRender(final ViewElements view, final UnmodifiablePlayer player) {
        super(view);
        this.player = player;

        this.playerSprite = new ImageView("Player/down/down1.png");
        addToRoot(playerSprite);
    }

    /** {@inheritDoc} */
    @Override
    public void init() {
    }

    /**
     * Moves the player in the specified direction.
     * 
     * @param direction the direction in which the player should move
     */
    private void move(final Point2ds direction) {
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
     * 
     * @return playerSprite
     */
    public ImageView getSprite() {
        return this.playerSprite;
    }
}
