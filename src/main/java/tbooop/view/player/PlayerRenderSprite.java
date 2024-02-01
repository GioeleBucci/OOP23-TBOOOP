package tbooop.view.player;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tbooop.commons.api.Point2d;

/**
 * It's the class for animate the Player.
 */
public class PlayerRenderSprite {

    private int counterDown;
    private int counterLeft;
    private int counterRight;
    private int counterUp;
    private final List<Image> downSprite;
    private final List<Image> upSprite;
    private final List<Image> leftSprite;
    private final List<Image> rightSprite;

    /**
     * It's the class for animate the Player.
     */
    public PlayerRenderSprite() {
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
    }

    /**
     * Move the player down.
     * @param player 
     * @param newPos new position.
     */
    public void goDown(final ImageView player, final Point2d newPos) {
        player.setImage(downSprite.get(counterDown));
        player.setX(newPos.getX());
        player.setY(newPos.getY());
        if (counterDown < 3) {
            this.counterDown = this.counterDown + 1;
        } else {
            this.counterDown = 0;
        }
    }

    /**
     * Move the player up.
     * @param player 
     * @param newPos new position.
     */
    public void goUp(final ImageView player, final Point2d newPos) {
        player.setImage(upSprite.get(counterUp));
        player.setX(newPos.getX());
        player.setY(newPos.getY());
        if (counterUp < 3) {
            this.counterUp = this.counterUp + 1;
        } else {
            this.counterUp = 0;
        }
    }

    /**
     * Move the player left.
     * @param player 
     * @param newPos new position.
     */
    public void goLeft(final ImageView player, final Point2d newPos) {
        player.setImage(leftSprite.get(counterLeft));
        player.setX(newPos.getX());
        player.setY(newPos.getY());
        if (counterLeft < 3) {
            this.counterLeft = counterLeft + 1;
        } else {
            this.counterLeft = 0;
        }
    }

    /**
     * Move the player right.
     * @param player 
     * @param newPos new position.
     */
    public void goRight(final ImageView player, final Point2d newPos) {
        player.setImage(rightSprite.get(counterRight));
        player.setX(newPos.getX());
        player.setY(newPos.getY());
        if (counterRight < 3) {
            this.counterRight = this.counterRight + 1;
        } else {
            this.counterRight = 0;
        }
    }
}
