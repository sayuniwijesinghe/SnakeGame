package controller;

import com.example.snakegame.HelloApplication;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.SnakeModel;
import model.MySnake;

import java.io.IOException;

/**
 * Controller class for managing interactions between the SnakeModel and the user input in the Snake Game.
 */
public class SnakeController {
    private SnakeModel model;
    private static MySnake mySnake;
    private static boolean gamePaused;
    private static HelloApplication helloApplication;

    /**
     * Constructor for SnakeController.
     *
     * @param model    The SnakeModel associated with the controller.
     * @param mySnake  The instance of MySnake controlled by the user.
     */
    public SnakeController(SnakeModel model, MySnake mySnake) {
        this.model = model;
        this.mySnake = mySnake;
        this.gamePaused = false;
    }

    /**
     * Handles the key press event for controlling the snake.
     *
     * @param e The KeyEvent representing the key press.
     */
    public void handleKeyPress(KeyEvent e) {
        if (!gamePaused) {
            mySnake.keyPressed(e);
        }
    }

    /**
     * Pauses the game.
     */
    public static void pauseGame() {
        gamePaused = true;
        // System.out.println("Game paused");
    }

    /**
     * Resumes the game
     */
    public static void resumeGame() {
        gamePaused = false;
        // System.out.println("Game resumed");
    }

    /**
     * Returns to the main menu
     *
     * @throws IOException If an error occurs during the transition to the main menu.
     */
    public static void mainMenu() throws IOException {
        pauseGame();
        helloApplication = new HelloApplication();
        helloApplication.start(new Stage());
    }

    /**
     * Restarts the game.
     */
    public static void restartGame() {
        mySnake.reset();
        resumeGame();
        // System.out.println("Game restarted");
    }

    /**
     * Checks if the game is currently paused.
     *
     * @return true if the game is paused, false otherwise.
     */
    public boolean isGamePaused() {
        return gamePaused;
    }
}
