package tbooop.view.api.enemy;

import javafx.scene.image.Image;
import java.util.List;

/**
 * Contains getters for the frames that make up the animation of
 * each boss.
 */
public interface BossFrames {
        /**
     * getter for DoE's animation frames.
     * @return a list of melee's frames
     */
    List<Image> dukeOfEyesFrames();
}
