package tbooop.view.enemy;

import java.util.List;

import javafx.scene.image.Image;
import tbooop.view.api.enemy.BossFrames;

public class BossFramesImpl implements BossFrames {

    @Override
    public List<Image> dukeOfEyesFrames() {
        return List.of(
            new Image("boss/dukeOfEyes/doe1.png"),
            new Image("boss/dukeOfEyes/doe2.png"),
            new Image("boss/dukeOfEyes/doe3.png"),
            new Image("boss/dukeOfEyes/doe4.png")
        );
    }
    
}
