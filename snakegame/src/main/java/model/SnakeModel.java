package model;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents the model of the Snake Game, including the snake, food, stones, bombs, and high scores.
 */
public class SnakeModel {
    private List<Point2D> snakeBody;
    private MySnake mySnake;
    private Food food;
    private List<Stone> stones;
    private List<Speed> bombs;
    private HighScoreManager highScoreManager;

    /**
     * Constructs a new SnakeModel
     * Initializes the snake, food, stones, bombs, and high score manager.
     */
    public SnakeModel() {
        snakeBody = new LinkedList<>();
        mySnake = new MySnake(100, 100, 3);
        food = new Food();
        stones = new LinkedList<>();
        bombs = new ArrayList<>();

        // Generate initial stones
        for (int i = 0; i < 3; i++) {
            stones.add(new Stone(stones));
        }

        // Generate initial bombs
        for (int i = 0; i < 2; i++) {
            bombs.add(new Speed(stones));
        }
    }

    /**
     * Gets the current body of the snake.
     *
     * @return The list of points representing the snake's body.
     */
    public List<Point2D> getSnakeBody() {
        return snakeBody;
    }

    /**
     * Gets the snake object in the model.
     *
     * @return The MySnake object representing the snake.
     */
    public MySnake getMySnake() {
        return mySnake;
    }

    /**
     * Updates the model by moving the snake, handling food consumption, and checking collisions.
     */
    public void update() {
        mySnake.move();
        food.eaten(mySnake);
        mySnake.checkCollisionsWithStones(stones);
        mySnake.checkCollisionsWithBombs(bombs);
    }

    /**
     * Gets the list of stones in the model.
     *
     * @return The list of Stone objects representing the stones.
     */
    public List<Stone> getStones() {
        return stones;
    }

    /**
     * Gets the list of bombs in the model.
     *
     * @return The list of Speed objects representing the bombs.
     */
    public List<Speed> getBombs() {
        return bombs;
    }

    /**
     * Gets the high score manager associated with the model.
     *
     * @return The HighScoreManager object handling high scores.
     */
    public HighScoreManager getHighScoreManager() {
        return highScoreManager;
    }
}
