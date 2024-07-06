package tbooop.view.sound_manager;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The MusicPlayer class represents a player for background music in a game or
 * application.
 * It provides methods to play default music and special room music, as well as
 * the ability to change the current track.
 */
public class MusicPlayer {

    private static final String TITLE_MUSIC = "music/title.mp3";
    private static final String DEFAULT_MUSIC = "music/floor.mp3";
    private static final String SPECIAL_ROOM_MUSIC = "music/special_room.mp3";
    private static final String BOSS_MUSIC = "music/boss.mp3";
    private static final String DEAD_MUSIC = "music/dead.mp3";

    private MediaPlayer mediaPlayer;

    private MusicPlayer() {
        setMediaPlayer(TITLE_MUSIC, .25);
    }

    private static class MusicPlayerHolder {
        private static final MusicPlayer INSTANCE = new MusicPlayer();
    }

    public static MusicPlayer getInstance() {
        return MusicPlayerHolder.INSTANCE;
    }

    /**
     * Plays the title music track.
     */
    public void playTitleMusic() {
        changeTrack(TITLE_MUSIC, .25);
    }

    /**
     * Plays the default music track.
     */
    public void playDefaultMusic() {
        changeTrack(DEFAULT_MUSIC, 1.4);
    }

    /**
     * Plays the special room music track.
     */
    public void playSpecialRoomMusic() {
        changeTrack(SPECIAL_ROOM_MUSIC, .7);
    }

    /**
     * Plays the special room music track.
     */
    public void playBossMusic() {
        changeTrack(BOSS_MUSIC, .85);
    }

    /**
     * Plays the dead music track.
     */
    public void playDeadMusic() {
        changeTrack(DEAD_MUSIC, .6);
    }

    /**
     * Changes the current music track to the specified track.
     * If the specified track is already playing, nothing happens.
     * If a different track is playing, the current track is stopped and the new
     * track is played.
     * 
     * @param newTrack the URL of the new music track
     * @param volume   the volume of the new track
     */
    private void changeTrack(final String newTrack, final double volume) {
        if (mediaPlayer.getMedia().getSource().equals(getMediaFromURL(newTrack))) {
            return;
        }
        mediaPlayer.stop();
        setMediaPlayer(newTrack, volume);
    }

    /**
     * Sets the media player to play the specified track.
     * 
     * @param track  the URL of the music track to play
     * @param volume the volume of the track
     */
    private void setMediaPlayer(final String track, double volume) {
        if (volume < 0 || volume > 2) {
            throw new IllegalArgumentException("Volume must be between 0 and 2");
        }
        mediaPlayer = new MediaPlayer(
                new Media(getMediaFromURL(track)));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(volume);
        mediaPlayer.play();
    }

    /**
     * Gets the media URL from the specified resource URL.
     * 
     * @param url the resource URL of the media
     * @return the media URL
     */
    private String getMediaFromURL(final String url) {
        return getClass().getClassLoader().getResource(url).toString();
    }
}
