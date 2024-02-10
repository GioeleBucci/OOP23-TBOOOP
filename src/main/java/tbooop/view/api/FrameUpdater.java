package tbooop.view.api;

import javafx.scene.image.Image;

/**
 * This class's purpose is to tell when a graphical entity's frame should be
 * updated in order to get the proper animation.
 */
public interface FrameUpdater {

    /**
     * Returns the next frame of the animation. If the time passed since the
     * last frame update is too short, this method will return the same frame.
     * @param currentTime the current time
     * @return the next frame
     * @throws IllegalArgumentException if currentTime is negative
     */
    Image getNextFrame(long currentTime);

    /**
     * If the frame has been updated at least once, the time of the latest frame update
     * will be set as equal to the current time given as parameter.
     * @param currentTime the current time
     * @throws IllegalArgumentException if currentTime is negative
     */
    void resetIfUpdated(long currentTime);

}
