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
                new Image("boss/dukeOfEyes/doe4.png"));
    }

    @Override
    public List<Image> meatyFrames() {
        return List.of(
                new Image("boss/meaty/meaty1.png"),
                new Image("boss/meaty/meaty2.png"),
                new Image("boss/meaty/meaty3.png"),
                new Image("boss/meaty/meaty4.png"));
    }

    @Override
    public List<Image> floatBloatFrames() {
        return List.of(
                new Image("boss/floatBloat/floatbloat1.png"),
                new Image("boss/floatBloat/floatbloat2.png"),
                new Image("boss/floatBloat/floatbloat3.png"));
    }

}
