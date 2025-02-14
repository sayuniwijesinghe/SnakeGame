package view;

import com.example.snakegame.SettingsController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import controller.SnakeController;
import model.*;

import util.MusicPlayer;




/**
 * The SnakeView class represents the main view for the Snake Game. It extends the JavaFX Application
 * and includes the necessary components for rendering the game, handling user input, and managing the
 * game loop.
 */
public class SnakeView extends Application {
    private SnakeModel model;
    private SnakeController controller;
    private Canvas canvas;
    private MusicPlayer musicPlayer;
    private MySnake mySnake;
    private Food food;
    private Image background;
    private GameOver gameOver;
    public HighScoreManager highScoreManager;

    /**
     * The main method for launching the JavaFX application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes and starts the SnakeView to display the Snake Game.
     */
    public void startSnakeView() {
        model = new SnakeModel();
        mySnake = model.getMySnake();
        controller = new SnakeController(model, mySnake);
        canvas = new Canvas(870, 560);
        background = new Image("/UI-background.png");
        food = new Food();
        mySnake.setSpeed(3);
        highScoreManager = new HighScoreManager();
        gameOver = new GameOver(highScoreManager);
        SettingsController settingsController = new SettingsController();

        BorderPane root = new BorderPane(canvas);

        SouthPanel southPanel = new SouthPanel();
        southPanel.setSnakeController(controller);
        root.setBottom(southPanel);

        Scene scene = new Scene(root);
        scene.addEventHandler(KeyEvent.ANY, event -> controller.handleKeyPress(event));

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        musicPlayer = new MusicPlayer("src/main/resources/frogger.mp3");
        musicPlayer.play();

        new GameLoop().start();
    }

    /**
     * Overrides the start method of the Application class to call startSnakeView.
     *
     * @param primaryStage The primary stage for the JavaFX application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Call the startSnakeView method when needed
        startSnakeView();
    }

    /**
     * Sets the font size for the SnakeView.
     *
     * @param v The font size.
     */
    public void setFontSize(double v) {
    }

    /**
     * The GameLoop class extends AnimationTimer and represents the game loop for updating
     * the game state and rendering the game continuously.
     */
    class GameLoop extends AnimationTimer {
        private static final double NANOSECONDS_PER_SECOND = 1e9;
        private static final double TARGET_UPS = 60.0; // Adjust this based on your desired updates per second
        private static final double TIME_PER_UPDATE = NANOSECONDS_PER_SECOND / TARGET_UPS;

        private long lastTime = System.nanoTime();
        private double lag = 0.0;

        @Override
        public void handle(long now) {
            if (!controller.isGamePaused()) {
                double elapsed = (now - lastTime) / NANOSECONDS_PER_SECOND;
                lastTime = now;

                lag += elapsed;

                while (lag >= TIME_PER_UPDATE) {
                    model.update();
                    lag -= TIME_PER_UPDATE;
                }

                render();
            }
        }
    }

    /**
     * Renders the game by clearing the canvas, drawing the background, snake, food,
     * and handling the game over state.
     */
    private void render() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(background, 0, 0);

        // Determine the state of the game.
        if (mySnake.isAlive) {
            mySnake.draw(gc); // Draw the snake head
            if (food.isAlive) {
                food.draw(gc);
                food.eaten(mySnake);
            } else {
                food = new Food();
            }
        } else {
            gameOver.handleGameOver(gc, canvas.getWidth(), canvas.getHeight(), mySnake.score);
        }

        drawScore(gc);
    }

    /**
     * Draws the current score on the canvas.
     *
     * @param gc The GraphicsContext used for drawing.
     */
    private void drawScore(GraphicsContext gc) {
        gc.setFont(new Font(Font.getDefault().getName(), 30));
        gc.setFill(Color.MAGENTA);
        gc.fillText("SCORE : " + mySnake.score, 20, 40);
    }
}
