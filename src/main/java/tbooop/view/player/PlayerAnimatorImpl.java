package tbooop.view.player;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tbooop.commons.api.Direction;
import tbooop.commons.api.Point2d;
import tbooop.model.player.api.UnmodifiablePlayer;
import tbooop.view.api.player.PlayerAnimator;

/** Renders a Player. */
@SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Passing modifiable view elements"
        + "is required to distribute the work load between the various view components.")
public class PlayerAnimatorImpl implements PlayerAnimator {

    private final PlayerRenderer playerRenderSprite = new PlayerRenderer();
    private final ImageView playerSprite;
    private final UnmodifiablePlayer player;
    private Point2d lastPosition;
    private Image playerDefault = new Image("player/down/down2.png");

    /**
     * Constructs a new PlayerRender.
     * @param imgView the ImageView representing the player
     * @param player the UnmodifiablePlayer object representing the player
     */
    public PlayerAnimatorImpl(final ImageView imgView, final UnmodifiablePlayer player) {
        this.player = player;
        this.playerSprite = imgView;
        this.lastPosition = player.getPosition();
    }
    /**
     * Moves the player in the specified direction.
     * 
     * @param direction the direction in which the player should move
     */
    private void move(final Direction direction) {
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
    @Override
    public void update() {
        if (player.getCardinalDirection().isPresent() && isMoving()) {
            move(player.getCardinalDirection().get());
        } else {
            playerSprite.setImage(playerDefault);
        }
        this.lastPosition = player.getPosition();
    }

    private boolean isMoving() {
        return !player.getPosition().equals(this.lastPosition);
    }
}
