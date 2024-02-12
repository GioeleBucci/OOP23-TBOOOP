package tbooop.view.api.enemy;

import java.util.List;

import javafx.scene.image.Image;

/**
 * Contains getters for the frames that make up the animation of
 * each enemy.
 */
public interface EnemyFrames {

    /**
     * getter for melee's animation frames.
     * @return a list of melee's frames
     */
    List<Image> meleeFrames();

    /**
     * getter for shooter's animation frames.
     * @return a list of shooter's frames
     */
    List<Image> shooterFrames();

    /**
     * getter for bouncer's animation frames.
     * @return a list of bouncer's frames
     */
    List<Image> bouncerFrames();

    /**
     * getter for crazy's animation frames.
     * @return a list of crazy's frames
     */
    List<Image> crazyFrames();

}
