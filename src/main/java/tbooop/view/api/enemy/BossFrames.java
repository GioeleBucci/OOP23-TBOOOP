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
     * 
     * @return a list of DoE's frames
     */
    List<Image> dukeOfEyesFrames();

    /**
     * getter for Meaty's animation frames.
     * 
     * @return a list of Meaty's frames
     */
    public List<Image> meatyFrames();

    /**
     * getter for FloatBloat's animation frames.
     * 
     * @return a list of FloatBloat's frames
     */
    public List<Image> floatBloatFrames();
}
