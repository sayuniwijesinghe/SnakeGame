package view;

import controller.SnakeController;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.*;
import util.MusicPlayer;

/**
 * The Level2View class represents the view for the second level of the Snake Game.
 * It extends the SnakeView class and includes the necessary components for rendering
 * the game, handling user input, and managing the game loop.
 */
public class Level2View extends SnakeView {
    private SnakeModel model;
    private SnakeController controller;
    private Canvas canvas;
    private MusicPlayer musicPlayer;
    private MySnake mySnake;
    private Food food;
    private Image background;

    private GameOver gameOver;  // Include the GameOver logic in Level2View

    /**
     * The main method for launching the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the Level2View to display the second level of the Snake Game.
     */
    public void startLevel2View() {
        model = new SnakeModel();
        mySnake = model.getMySnake();
        controller = new SnakeController(model, mySnake);
        canvas = new Canvas(870, 560);
        background = new Image("/HardLevelbgp.png");
        food = new Food();
        mySnake.setSpeed(2);

        highScoreManager = new HighScoreManager();
        gameOver = new GameOver(model.getHighScoreManager());  // Pass the HighScoreManager

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

        new Level2View.GameLoop().start();
    }

    /**
     * The GameLoop class extends AnimationTimer and represents the game loop for updating
     * the game state and rendering the game continuously.
     */
    private class GameLoop extends AnimationTimer {
        private static final double NANOSECONDS_PER_SECOND = 1e9;
        private static final double TARGET_UPS = 60.0;
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
     * Renders the game by clearing the canvas, drawing the background, stones, bombs,
     * snake, and food. It also checks for collisions and updates the score.
     */
    private void render() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(background, 0, 0);

        for (Stone stone : model.getStones()) {
            stone.draw(gc);
        }
        for (Speed bomb : model.getBombs()) {
            bomb.draw(gc);
        }

        if (mySnake.isAlive) {
            mySnake.draw(gc);

            if (food.isAlive) {
                food.draw(gc);
                food.eaten(mySnake);
            } else {
                food = new Food();
            }

            mySnake.checkCollisionsWithStones(model.getStones());
            mySnake.checkCollisionsWithBombs(model.getBombs());
        } else {
            // Call handleGameOver when the snake is not alive
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
