package tbooop.view;

import java.util.List;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tbooop.commons.api.Point2d;

public class PlayerRenderSprite {
    
    private int counterDown;
    private int counterLeft;
    private int counterRight;
    private int counterUp;
    private List<Image> downSprite = new ArrayList<>();
    private List<Image> upSprite = new ArrayList<>();
    private List<Image> leftSprite = new ArrayList<>();
    private List<Image> rightSprite = new ArrayList<>();

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

    public void goDown(ImageView player, Point2d newPos){
        player.setImage(downSprite.get(counterDown));
        player.setX(newPos.getX());
        player.setY(newPos.getY());
        if (counterDown < 3) {
            this.counterDown ++;
        } else {
            this.counterDown = 0;
        }
    }

    public void goUp(ImageView player, Point2d newPos){
        player.setImage(upSprite.get(counterUp));
        player.setX(newPos.getX());
        player.setY(newPos.getY());
        if (counterUp < 3) {
            this.counterUp ++;
        } else {
            this.counterUp = 0;
        }
    }

    public void goLeft(ImageView player, Point2d newPos){
        player.setImage(leftSprite.get(counterLeft));
        player.setX(newPos.getX());
        player.setY(newPos.getY());
        if (counterLeft < 3) {
            this.counterLeft ++;
        } else {
            this.counterLeft = 0;
        }
    }

    public void goRight(ImageView player, Point2d newPos){
        player.setImage(rightSprite.get(counterRight));
        player.setX(newPos.getX());
        player.setY(newPos.getY());
        if (counterRight < 3) {
            this.counterRight ++;
        } else {
            this.counterRight = 0;
        }
    }
}
