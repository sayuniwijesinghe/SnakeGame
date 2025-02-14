package model;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

/**
 * Represents the Game Over screen with handling of player names and high scores.
 */
public class GameOver {

    private boolean namePromptShown = false;
    private HighScoreManager highScoreManager;

    Stage primaryStage = new Stage();

    /**
     * Constructor for the GameOver class.
     *
     * @param highScoreManager The manager for handling high scores.
     */
    public GameOver(HighScoreManager highScoreManager) {
        this.highScoreManager = highScoreManager;
    }

    /**
     * Handles the game over event and displays the game over image.
     * Prompts the user for their name and handles the score.
     *
     * @param gc           The GraphicsContext on which to draw.
     * @param canvasWidth  The width of the canvas.
     * @param canvasHeight The height of the canvas.
     * @param playerScore  The player's score.
     */
    public void handleGameOver(GraphicsContext gc, double canvasWidth, double canvasHeight, int playerScore) {
        Image gameOverImage = new Image("/game-over.JPG");
        double x = (canvasWidth - gameOverImage.getWidth()) / 2;
        double y = (canvasHeight - gameOverImage.getHeight()) / 2;

        gc.drawImage(gameOverImage, x, y);

        // Now, prompt the user for their name if not shown already
        if (!namePromptShown) {
            Platform.runLater(() -> promptForNameAndHandleScore(playerScore));
            namePromptShown = true; // Set the flag to true after showing the prompt
        }
    }

    /**
     * Prompts the user for their name and handles the score.
     *
     * @param playerScore The player's score.
     */
    private void promptForNameAndHandleScore(int playerScore) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Your Name");
        dialog.setHeaderText(null);
        dialog.setContentText("Please enter your name:");

        // Get the dialog pane and apply some styling
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/text.css").toExternalForm());
        dialogPane.getStyleClass().add("custom-dialog-pane");

        dialog.showAndWait().ifPresent(playerName -> {
            // Add the player's score to the high scores
            highScoreManager.addHighScore(playerName, playerScore);

            // Display the updated high score board
            displayHighScores();
        });
    }

    /**
     * Displays the high scores in a new window using TableView.
     */
    private void displayHighScores() {
        Platform.runLater(() -> {
            // Create a new Stage for the high scores table
            Stage highScoresStage = new Stage();
            highScoresStage.setTitle("High Scores");

            // Create a TableView for the high scores
            TableView<HighScoreEntry> tableView = new TableView<>();

            // Create columns for the table
            TableColumn<HighScoreEntry, String> nameColumn = new TableColumn<>("Player Name");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));

            TableColumn<HighScoreEntry, Integer> scoreColumn = new TableColumn<>("Score");
            scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

            // Add columns to the table
            tableView.getColumns().addAll(nameColumn, scoreColumn);

            // Get the high scores from the HighScoreManager
            List<HighScoreEntry> highScores = highScoreManager.getHighScores();
            ObservableList<HighScoreEntry> data = FXCollections.observableArrayList(highScores);

            // Set the items in the table
            tableView.setItems(data);

            // Sort the table in descending order based on the score
            tableView.getSortOrder().add(scoreColumn);
            scoreColumn.setSortType(TableColumn.SortType.DESCENDING);
            tableView.sort();

            // Create a VBox to hold the table
            VBox vbox = new VBox(tableView);

            // Create a Scene and set it to the Stage
            Scene scene = new Scene(vbox);
            highScoresStage.setScene(scene);

            // Show the Stage
            highScoresStage.show();
        });
    }
}
