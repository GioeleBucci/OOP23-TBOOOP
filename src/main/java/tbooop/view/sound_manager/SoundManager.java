package tbooop.view.sound_manager;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundManager {

    private SoundManager() {
    }

    private static class SoundManagerHolder {
        private static final SoundManager INSTANCE = new SoundManager();
    }

    /**
     * <b>NOTE</b> When calling this, a null check if you plan to test the class
     * since when testing, the JavaFX toolkit won't be initialised.
     */
    public static SoundManager getInstance() {
        if (javafx.application.Platform.isFxApplicationThread()) {
            return SoundManagerHolder.INSTANCE;
        } else {
            return null;
        }
    }

    public void playSound(Sound sound) {
        Media s = new Media(getClass().getClassLoader().getResource(sound.getUrl()).toString());
        MediaPlayer mediaPlayer = new MediaPlayer(s);
        mediaPlayer.play();
    }
}
