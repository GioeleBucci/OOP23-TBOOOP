package tbooop.view.player;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * It's the class for animate the Player.
 */
public class PlayerAnimator {

    private final List<Image> downSprite;
    private final List<Image> upSprite;
    private final List<Image> leftSprite;
    private final List<Image> rightSprite;
    private final PlayerFrameUpdate frameUpdateDown;
    private final PlayerFrameUpdate frameUpdateUp;
    private final PlayerFrameUpdate frameUpdateLeft;
    private final PlayerFrameUpdate frameUpdateRight;

    /**
     * It's the class for animate the Player.
     */
    public PlayerAnimator() {
        downSprite = List.of(
            new Image("player/down/down1.png"), 
            new Image("player/down/down2.png"), 
            new Image("player/down/down3.png"), 
            new Image("player/down/down4.png"));
        upSprite = List.of(
            new Image("player/up/up1.png"), 
            new Image("player/up/up2.png"), 
            new Image("player/up/up3.png"), 
            new Image("player/up/up4.png"));
        leftSprite = List.of(
            new Image("player/left/left1.png"), 
            new Image("player/left/left2.png"), 
            new Image("player/left/left3.png"), 
            new Image("player/left/left4.png"));
        rightSprite = List.of(
            new Image("player/right/right1.png"), 
            new Image("player/right/right2.png"), 
            new Image("player/right/right3.png"), 
            new Image("player/right/right4.png"));

        frameUpdateDown = new PlayerFrameUpdate(downSprite, 100);
        frameUpdateUp = new PlayerFrameUpdate(upSprite, 100);
        frameUpdateLeft = new PlayerFrameUpdate(leftSprite, 100);
        frameUpdateRight = new PlayerFrameUpdate(rightSprite, 100);
    }

    /**
     * Move the player down.
     * @param player 
     */
    public void goDown(final ImageView player) {
        player.setImage(frameUpdateDown.getNextFrame(System.currentTimeMillis()));
        frameUpdateDown.resetIfUpdated(System.currentTimeMillis());
    }

    /**
     * Move the player up.
     * @param player 
     */
    public void goUp(final ImageView player) {
        player.setImage(frameUpdateUp.getNextFrame(System.currentTimeMillis()));
        frameUpdateUp.resetIfUpdated(System.currentTimeMillis());
    }

    /**
     * Move the player left.
     * @param player 
     */
    public void goLeft(final ImageView player) {
        player.setImage(frameUpdateLeft.getNextFrame(System.currentTimeMillis()));
        frameUpdateLeft.resetIfUpdated(System.currentTimeMillis());
    }

    /**
     * Move the player right.
     * @param player 
     */
    public void goRight(final ImageView player) {
        player.setImage(frameUpdateRight.getNextFrame(System.currentTimeMillis()));
        frameUpdateRight.resetIfUpdated(System.currentTimeMillis());
    }
}
