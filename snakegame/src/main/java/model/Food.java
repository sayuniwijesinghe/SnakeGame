package model;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import util.ImageUtil;

import java.util.Random;

/**
 * Represents a food object in the Snake Game.
 */
public class Food extends SnakeObject {

    /** Indicates whether the snake is alive or consumed by the snake. */
    public boolean isAlive;
    private Image i;

    /**
     * Constructor for the Food class.
     * Initializes the food object with a random position and image.
     */
    public Food() {
        this.isAlive = true;

        this.i = ImageUtil.images.get(String.valueOf(new Random().nextInt(17)));

        this.w = (int) i.getWidth();
        this.h = (int) i.getHeight();

        // Offset increased to ensure it doesn't go out of frame
        this.x = (int) (Math.random() * (870 - w + 20));
        this.y = (int) (Math.random() * (560 - h - 60));
    }

    /**
     * Checks if the food is eaten by the snake and updates the snake's length and score.
     *
     * @param mySnake The snake object to check for collision with the food.
     */
    public void eaten(MySnake mySnake) {
        if (mySnake.getBounds().intersects(this.getBounds()) && isAlive && mySnake.isAlive) {
            this.isAlive = false;
            mySnake.changeLength(mySnake.getLength() + 1);
            mySnake.score += 10;
        }
    }

    /**
     * Gets the bounds of the food object.
     *
     * @return The bounds of the food as a Rectangle.
     */
    public Bounds getBounds() {
        return new Rectangle(x, y, w, h).getBoundsInParent();
    }

    /**
     * Draws the food on the specified GraphicsContext.
     *
     * @param gc The GraphicsContext on which to draw the food.
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(i, x, y);
    }
}
