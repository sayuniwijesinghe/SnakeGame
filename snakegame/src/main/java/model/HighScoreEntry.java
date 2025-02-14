package model;

import java.io.Serializable;

/**
 * Represents a high score entry in the Snake Game.
 * Implements Comparable to enable sorting based on the score in descending order.
 */
public class HighScoreEntry implements Comparable<HighScoreEntry>, Serializable {

    /** The name of the player achieving the high score. */
    private String playerName;

    /** The score achieved by the player. */
    private int score;

    /**
     * Constructor for the HighScoreEntry class.
     *
     * @param playerName The name of the player achieving the high score.
     * @param score      The score achieved by the player.
     */
    public HighScoreEntry(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Gets the score achieved by the player.
     *
     * @return The score achieved by the player.
     */
    public int getScore() {
        return score;
    }

    /**
     * Compares two HighScoreEntry objects based on their scores in descending order.
     *
     * @param other The other HighScoreEntry to compare to.
     * @return A negative integer, zero, or a positive integer as this object is less than,
     * equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(HighScoreEntry other) {
        return Integer.compare(other.score, this.score);
    }
}
