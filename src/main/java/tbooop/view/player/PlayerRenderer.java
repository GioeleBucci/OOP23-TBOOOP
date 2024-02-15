package tbooop.view.player;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tbooop.view.FrameUpdaterImpl;

/**
 * It's the class for animate the Player.
 */
public class PlayerRenderer {

    private final List<Image> downSprite =  List.of(
        new Image("player/down/down1.png"), 
        new Image("player/down/down2.png"), 
        new Image("player/down/down3.png"), 
        new Image("player/down/down4.png"));
    private final List<Image> upSprite = List.of(
            new Image("player/up/up1.png"), 
            new Image("player/up/up2.png"), 
            new Image("player/up/up3.png"), 
            new Image("player/up/up4.png"));
    private final List<Image> leftSprite = List.of(
            new Image("player/left/left1.png"), 
            new Image("player/left/left2.png"), 
            new Image("player/left/left3.png"), 
            new Image("player/left/left4.png"));
    private final List<Image> rightSprite = List.of(
            new Image("player/right/right1.png"), 
            new Image("player/right/right2.png"), 
            new Image("player/right/right3.png"), 
            new Image("player/right/right4.png"));

    private final FrameUpdaterImpl frameUpdateDown;
    private final FrameUpdaterImpl frameUpdateUp;
    private final FrameUpdaterImpl frameUpdateLeft;
    private final FrameUpdaterImpl frameUpdateRight;

    /**
     * It's the class for animate the Player.
     */
    protected PlayerRenderer() {
        frameUpdateDown = new FrameUpdaterImpl(downSprite, 100);
        frameUpdateUp = new FrameUpdaterImpl(upSprite, 100);
        frameUpdateLeft = new FrameUpdaterImpl(leftSprite, 100);
        frameUpdateRight = new FrameUpdaterImpl(rightSprite, 100);
    }

    /**
     * Moves the player character down and updates the player's image frame.
     * 
     * @param player The ImageView representing the player character.
     */
    protected void goDown(final ImageView player) {
        player.setImage(frameUpdateDown.getNextFrame(System.currentTimeMillis()));
        frameUpdateDown.resetIfUpdated(System.currentTimeMillis());
    }

    /**
     * Moves the player character up and updates the player's image frame.
     * 
     * @param player The ImageView representing the player character.
     */
    protected void goUp(final ImageView player) {
        player.setImage(frameUpdateUp.getNextFrame(System.currentTimeMillis()));
        frameUpdateUp.resetIfUpdated(System.currentTimeMillis());
    }

    /**
     * Moves the player character left and updates the player's image frame.
     * 
     * @param player The ImageView representing the player character.
     */
    protected void goLeft(final ImageView player) {
        player.setImage(frameUpdateLeft.getNextFrame(System.currentTimeMillis()));
        frameUpdateLeft.resetIfUpdated(System.currentTimeMillis());
    }

    /**
     * Moves the player character right and updates the player's image frame.
     * 
     * @param player The ImageView representing the player character.
     */
    protected void goRight(final ImageView player) {
        player.setImage(frameUpdateRight.getNextFrame(System.currentTimeMillis()));
        frameUpdateRight.resetIfUpdated(System.currentTimeMillis());
    }
}
