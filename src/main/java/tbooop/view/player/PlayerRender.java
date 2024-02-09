package tbooop.view.player;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tbooop.commons.Point2ds;
import tbooop.commons.api.Point2d;
import tbooop.model.player.api.UnmodifiablePlayer;

/** Renders a Player. */
@SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Passing modifiable view elements"
        + "is required to distribute the work load between the various view components.")
public class PlayerRender {

    private final PlayerAnimator playerRenderSprite = new PlayerAnimator();
    private final ImageView playerSprite;
    private final UnmodifiablePlayer player;
    private Point2d lastPosition;
    private Image playerDefault = new Image("player/down/down2.png");

    /**
     * @param view   the root this attaches to.
     * @param player one Unmodifiable Player to set the animation sprite.
     */
    public PlayerRender(final ImageView imgView, final UnmodifiablePlayer player) {
        this.player = player;
        this.playerSprite = imgView;
        this.lastPosition = player.getPosition();
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
                this.playerDefault = new Image("player/down/down2.png");
                break;
            case UP:
                this.playerRenderSprite.goUp(playerSprite);
                this.playerDefault = new Image("player/up/up2.png");
                break;
            case LEFT:
                this.playerRenderSprite.goLeft(playerSprite);
                this.playerDefault = new Image("player/left/left2.png");
                break;
            case RIGHT:
                this.playerRenderSprite.goRight(playerSprite);
                this.playerDefault = new Image("player/right/right2.png");
                break;
            default:
                break;
        }
    }

    /**
     * Updates the player's position and animation.
     */
    public void update() {
        if (player.getPoint2ds().isPresent() && isMoving()){
            move(player.getPoint2ds().get());
        } else {
            playerSprite.setImage(playerDefault);
        }
        this.lastPosition = player.getPosition();
    }

    private boolean isMoving() {
        if (!player.getPosition().equals(this.lastPosition)) {
            return true;
        }
        return false;
    }
}
