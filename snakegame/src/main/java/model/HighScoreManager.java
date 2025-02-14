package model;

import model.HighScoreEntry;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages high scores for the Snake Game.
 */
public class HighScoreManager {
    /** The list of high score entries. */
    private List<HighScoreEntry> highScores;

    /** The file path for storing high scores data. */
    private static final String FILE_PATH = "highScores.dat";

    /**
     * Constructor for the HighScoreManager class.
     * Initializes the highScores list by loading existing high scores.
     */
    public HighScoreManager() {
        this.highScores = loadHighScores();
    }

    /**
     * Adds a new high score entry to the list, sorts the list, and saves the updated high scores.
     *
     * @param playerName The name of the player achieving the high score.
     * @param score      The score achieved by the player.
     */
    public void addHighScore(String playerName, int score) {
        HighScoreEntry newEntry = new HighScoreEntry(playerName, score);
        highScores.add(newEntry);
        Collections.sort(highScores);
        saveHighScores();
    }

    /**
     * Gets a copy of the list of high score entries.
     *
     * @return A copy of the list of high score entries.
     */
    public List<HighScoreEntry> getHighScores() {
        return new ArrayList<>(highScores);
    }

    /**
     * Saves the current high scores list to file.
     */
    private void saveHighScores() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(highScores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the high scores from file
     *
     * @return The list of high score entries loaded from the file
     */
    @SuppressWarnings("unchecked")
    private List<HighScoreEntry> loadHighScores() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
                return (List<HighScoreEntry>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }
}
