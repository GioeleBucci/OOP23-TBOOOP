package tbooop.view;

import java.util.List;
import java.util.ArrayList;
import javafx.scene.image.Image;

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
            new Image("Player/down/down1.png"), 
            new Image("Player/down/down2.png"), 
            new Image("Player/down/down3.png"), 
            new Image("Player/down/down4.png"));
        upSprite = List.of(
            new Image("Player/up/up1.png"), 
            new Image("Player/up/up2.png"), 
            new Image("Player/up/up3.png"), 
            new Image("Player/up/up4.png"));
        leftSprite = List.of(
            new Image("Player/left/left1.png"), 
            new Image("Player/left/left2.png"), 
            new Image("Player/left/left3.png"), 
            new Image("Player/left/left4.png"));
        rightSprite = List.of(
            new Image("Player/right/right1.png"), 
            new Image("Player/right/right2.png"), 
            new Image("Player/right/right3.png"), 
            new Image("Player/right/right4.png"));
    }

}
