package tbooop.view.enemy;

import java.util.List;

import javafx.scene.image.Image;
import tbooop.view.api.enemy.EnemyFrames;

/**
 * Class that contains getters for the frames of an enemy's animation.
 */
public class EnemyFramesImpl implements EnemyFrames {

    /** {@inheritDoc} */
    @Override
    public List<Image> meleeFrames() {
        return List.of(
            new Image("enemy/melee/melee1.png"),
            new Image("enemy/melee/melee2.png"),
            new Image("enemy/melee/melee3.png"),
            new Image("enemy/melee/melee4.png"),
            new Image("enemy/melee/melee5.png"),
            new Image("enemy/melee/melee6.png"),
            new Image("enemy/melee/melee7.png"),
            new Image("enemy/melee/melee8.png"),
            new Image("enemy/melee/melee9.png"),
            new Image("enemy/melee/melee10.png"),
            new Image("enemy/melee/melee11.png"));
    }

    /** {@inheritDoc} */
    @Override
    public List<Image> shooterFrames() {
        return List.of(
            new Image("enemy/shooter/shooter1.png"),
            new Image("enemy/shooter/shooter2.png"),
            new Image("enemy/shooter/shooter3.png"),
            new Image("enemy/shooter/shooter4.png"),
            new Image("enemy/shooter/shooter5.png"));
    }

    /** {@inheritDoc} */
    @Override
    public List<Image> bouncerFrames() {
        return List.of(
            new Image("enemy/bouncer/bouncer1.png"),
            new Image("enemy/bouncer/bouncer2.png"),
            new Image("enemy/bouncer/bouncer3.png"));
    }

    /** {@inheritDoc} */
    @Override
    public List<Image> crazyFrames() {
        return List.of(
            new Image("enemy/crazy/crazy1.png"),
            new Image("enemy/crazy/crazy2.png"),
            new Image("enemy/crazy/crazy3.png"),
            new Image("enemy/crazy/crazy4.png"));
    }

}
