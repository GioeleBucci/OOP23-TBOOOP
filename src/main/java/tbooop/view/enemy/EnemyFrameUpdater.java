package tbooop.view.enemy;

import java.util.List;
import java.util.Objects;

import javafx.scene.image.Image;

/**
 * This class's purpose is to tell when an enemy's frame should be
 * updated in order to get the proper animation.
 */
public class EnemyFrameUpdater {

    private final List<Image> frames;
    private long latestUpdate;
    private int frameCount;
    private boolean updated;
    private final double updateFrequency;

    /**
     * Creates an istance of an EnemyFrameUpdater.
     * @param frames the enemy animation's frames
     * @param updateFrequency the update time frequency between frames
     * @throws NullPointerException if frames is null
     */
    protected EnemyFrameUpdater(final List<Image> frames, final double updateFrequency) {
        this.frames = Objects.requireNonNull(frames);
        this.updateFrequency = updateFrequency;
        this.latestUpdate = System.currentTimeMillis();
    }

    /**
     * returns the next frame of the animation. If the time passed since the
     * last frame update is too short, this method will return the same frame.
     * @param currentTime the current time
     * @return the next frame
     */
    protected Image getNextFrame(final long currentTime) {
        if (currentTime - this.latestUpdate > this.updateFrequency) {
            frameCount++;
            this.updated = true;
        }
        if (this.frameCount == this.frames.size()) {
            this.frameCount = 0;
        }
        return this.frames.get(frameCount);
    }

    /**
     * if the frame has been updated at least once, the time of the latest frame update
     * will be set as equal to the current time given as parameter.
     * @param currentTime the current time
     */
    protected void resetIfUpdated(final long currentTime) {
        if (this.updated) {
            this.latestUpdate = currentTime;
            this.updated = false;
        }
    }
}
