package view;

import controller.SnakeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * The SouthPanel class represents the user interface component at the bottom of the game screen.
 * It contains buttons for pausing, resuming, accessing the main menu, and restarting the game.
 */
public class SouthPanel extends HBox {
    private Button pauseButton;
    private Button resumeButton;
    private Button menuButton;
    private Button restartButton; // Add the restart button

    private SnakeController snakeController;

    /**
     * Initializes the SouthPanel with buttons for pausing, resuming, accessing the main menu, and restarting the game.
     */
    public SouthPanel() {
        pauseButton = new Button("Pause");
        resumeButton = new Button("Resume");
        menuButton = new Button("Main Menu");
        restartButton = new Button("Restart"); // Initialize the restart button

        pauseButton.setPrefSize(100, 40);
        resumeButton.setPrefSize(100, 40);
        menuButton.setPrefSize(100, 40);
        restartButton.setPrefSize(100, 40); // Set the preferred size for the restart button

        setSpacing(30);
        getChildren().addAll(pauseButton, resumeButton, menuButton, restartButton); // Add restart button
        setAlignment(Pos.CENTER);

        // Add action listeners for the buttons
        pauseButton.setOnAction(event -> snakeController.pauseGame());
        resumeButton.setOnAction(event -> snakeController.resumeGame());
        menuButton.setOnAction(event -> {
            try {
                snakeController.mainMenu();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        restartButton.setOnAction(event -> snakeController.restartGame()); // Add action for restart button

        setStyle("-fx-background-color: grey;");
        pauseButton.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        resumeButton.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        menuButton.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        restartButton.setStyle("-fx-border-color: black; -fx-border-width: 2px;"); // Set style for restart button
        setPadding(new Insets(5, 5, 5, 5));
    }

    /**
     * Sets the SnakeController for handling button actions.
     *
     * @param snakeController The SnakeController instance.
     */
    public void setSnakeController(SnakeController snakeController) {
        this.snakeController = snakeController;
    }
}
