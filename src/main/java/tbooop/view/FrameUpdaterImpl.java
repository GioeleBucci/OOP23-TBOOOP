package tbooop.view;

import java.util.List;
import java.util.Objects;

import javafx.scene.image.Image;
import tbooop.view.api.FrameUpdater;

import java.util.ArrayList;

/**
 * This class's purpose is to tell when a graphical entity's frame should be
 * updated in order to get the proper animation.
 */
public class FrameUpdaterImpl implements FrameUpdater {

    private final List<Image> frames;
    private long latestUpdate;
    private int frameCount;
    private boolean updated;
    private final double updateFrequency;

    /**
     * Creates an istance of a FrameUpdaterImpl.
     * @param frames the animation's frames
     * @param updateFrequency the update time frequency between frames
     * @throws NullPointerException if frames is null
     */
    public FrameUpdaterImpl(final List<Image> frames, final double updateFrequency) {
        this.frames = new ArrayList<>(Objects.requireNonNull(frames));
        this.updateFrequency = updateFrequency;
        this.latestUpdate = System.currentTimeMillis();
    }

    /** {@inheritDoc} */
    @Override
    public Image getNextFrame(final long currentTime) {
        this.checkCurrentTime(currentTime);
        if (currentTime - this.latestUpdate > this.updateFrequency && !updated) {
            frameCount++;
            this.updated = true;
        }
        if (this.frameCount == this.frames.size()) {
            this.frameCount = 0;
        }
        return this.frames.get(frameCount);
    }

    /** {@inheritDoc} */
    @Override
    public void resetIfUpdated(final long currentTime) {
        this.checkCurrentTime(currentTime);
        if (this.updated) {
            this.latestUpdate = currentTime;
            this.updated = false;
        }
    }

    private void checkCurrentTime(final long currentTime) {
        if (currentTime < 0) {
            throw new IllegalArgumentException("currentTime can't be negative");
        }
    }

}
