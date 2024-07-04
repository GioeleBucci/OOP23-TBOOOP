package tbooop.view.sound_manager;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundManager {

    private SoundManager() {
    }

    private static class SoundManagerHolder {
        private static final SoundManager INSTANCE = new SoundManager();
    }

    public static SoundManager getInstance() {
        return SoundManagerHolder.INSTANCE;
    }

    public void playSound(Sound sound) {
        Media s = new Media(getClass().getClassLoader().getResource(sound.getUrl()).toString());
        MediaPlayer mediaPlayer = new MediaPlayer(s);
        mediaPlayer.play();
    }
}
