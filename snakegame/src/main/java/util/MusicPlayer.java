package util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * Utility class for playing background music in the game.
 */
public class MusicPlayer {
    /**
     * The filename of the audio file to be played.
     */
    private String filename;

    /**
     * The MediaPlayer object responsible for playing the audio.
     */
    private MediaPlayer mediaPlayer;

    /**
     * Constructs a MusicPlayer with the specified filename.
     *
     * @param filename The filename of the audio file to be played.
     */
    public MusicPlayer(String filename) {
        this.filename = filename;
    }

    /**
     * Starts playing the background music.
     * The audio file is loaded, and the MediaPlayer is configured for continuous looping.
     */
    public void play() {
        try {
            // Create a Media object from the specified filename
            Media media = new Media(new File(filename).toURI().toString());

            // Create a MediaPlayer object and set it to loop indefinitely
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

            // Start playing the audio
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Static method to simplify starting background music without creating a MusicPlayer instance.
     *
     * @param filename The filename of the audio file to be played.
     */
    public static void getMusicPlay(String filename) {
        MusicPlayer musicPlayer = new MusicPlayer(filename);
        musicPlayer.play();
    }
}
