package model;

import interfaces.movable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import util.GameUtil;
import util.ImageUtil;

import java.awt.*;
import javafx.scene.input.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;

/**
 * Represents the player-controlled snake in the Snake Game.
 * Implements the movable interface to enable drawing on the canvas.
 */
public class MySnake extends SnakeObject implements movable {
    /** The speed of the snake in pixels per frame. */
    private int speed_XY;

    /** The length of the snake. */
    private int length;

    /** The number of parts in each body segment of the snake. */
    private int num;

    /** The current score of the snake. */
    public int score = 0;

    /** The image representing the snake's head facing right. */
    private static final Image IMG_SNAKE_HEAD = ImageUtil.images.get("snake-head-right");

    /** The list of points representing the body parts of the snake. */
    public List<Point> bodyPoints = new LinkedList<>();

    /** The image representing the snake's head with the correct rotation. */
    private Image newImgSnakeHead;

    /** Flags to track the direction the snake is moving. */
    private boolean up, down, left, right = true;

    /** Flag to indicate if the snake has eaten food. */
    private boolean eaten = false;

    /** Initial X coordinate for the snake. */
    private int initialX = 100;

    /** Initial Y coordinate for the snake. */
    private int initialY = 100;

    /** Initial length of the snake. */
    private int initialLength = 1;

    /**
     * Constructor for the MySnake class.
     *
     * @param x     The initial X coordinate of the snake.
     * @param y     The initial Y coordinate of the snake.
     * @param speed The speed of the snake in pixels per frame.
     */
    public MySnake(int x, int y, int speed) {
        this.isAlive = true;
        this.x = x;
        this.y = y;
        this.i = ImageUtil.images.get("snake-body");
        this.w = (int) i.getWidth();
        this.h = (int) i.getHeight();

        this.x = initialX;
        this.y = initialY;

        this.speed_XY = speed;
        this.length = 1;

        this.num = w / speed_XY;
        newImgSnakeHead = IMG_SNAKE_HEAD;
    }
    /**
     * Gets the length of the snake.
     *
     * @return The length of the snake.
     */
    public int getLength() {
        return length;
    }
    /**
     * Changes the length of the snake.
     *
     * @param length The new length of the snake.
     */
    public void changeLength(int length) {
        this.length = length;
    }

    /**
     * Handles key presses to change the direction of the snake.
     *
     * @param e The KeyEvent representing the key press.
     */

    public void keyPressed(KeyEvent e) {

        if (e == null) {
            return;
        }
        switch (e.getCode()) {
            case UP:
                if (!down) {
                    up = true;
                    down = false;
                    left = false;
                    right = false;
                    newImgSnakeHead = GameUtil.rotateImage(IMG_SNAKE_HEAD, -90);
                }
                break;
            case DOWN:
                if (!up) {
                    up = false;
                    down = true;
                    left = false;
                    right = false;
                    newImgSnakeHead = GameUtil.rotateImage(IMG_SNAKE_HEAD, 90);
                }
                break;
            case LEFT:
                if (!right) {
                    up = false;
                    down = false;
                    left = true;
                    right = false;
                    newImgSnakeHead = GameUtil.rotateImage(IMG_SNAKE_HEAD, -180);
                }
                break;
            case RIGHT:
                if (!left) {
                    up = false;
                    down = false;
                    left = false;
                    right = true;
                    newImgSnakeHead = IMG_SNAKE_HEAD;
                }
                break;
            default:
                break;
        }
    }
    /**
     * Moves the snake based on its current direction.
     */
    public void move() {
        if (up) {
            y -= speed_XY;
        } else if (down) {
            y += speed_XY;
        } else if (left) {
            x -= speed_XY;
        } else if (right) {
            x += speed_XY;
        }
    }

    /**
     * Gets the image representing the snake's head with the correct rotation.
     *
     * @return The image representing the snake's head with the correct rotation.
     */

    public Image getNewImgSnakeHead() {
        return newImgSnakeHead;
    }
    /**
     * Draws the snake on the given GraphicsContext.
     *
     * @param gc The GraphicsContext on which to draw the snake.
     */
    @Override
    public void draw(GraphicsContext gc) {
        outOfBounds();
        eatBody();

        bodyPoints.add(new Point(x, y));

        if (bodyPoints.size() == (this.length + 1) * num) {
            bodyPoints.remove(0);
        }

        if (eaten) {
            // Add new body parts to the snake
            for (int i = 0; i < num; i++) {
                bodyPoints.add(new Point(x, y));
            }
            eaten = false;
        }

        gc.drawImage(newImgSnakeHead, x, y);
        drawBody(gc);

        move();
    }
    /**
     * Checks if the snake has collided with its own body.
     */
    public void eatBody() {
        for (Point point : bodyPoints) {
            for (Point point2 : bodyPoints) {
                if (point.equals(point2) && point != point2) {
                    this.isAlive = false;
                }
            }
        }
    }
    /**
     * Draws the body of the snake on the given GraphicsContext.
     *
     * @param gc The GraphicsContext on which to draw the body.
     */
    public void drawBody(GraphicsContext gc) {
        int length = bodyPoints.size() - 1 - num;

        for (int i = length; i >= num; i -= num) {
            Point point = bodyPoints.get(i);
            gc.drawImage(this.i, point.x, point.y);
        }
    }


    /**
     * Checks if the snake is out of bounds and sets isAlive to false if it is.
     */
    private void outOfBounds() {
        boolean xOut = (x <= 0 || x >= (870 - w));
        boolean yOut = (y <= 0 || y > (560 - h));

        if (xOut || yOut) {
            isAlive = false;
        }
    }
    /**
     * Gets the bounds of the snake for collision detection.
     *
     * @return The Bounds of the snake.
     */
    public Bounds getBounds() {
        return new Rectangle(x, y, w, h).getBoundsInParent();
    }
    /**
     * Sets the speed of the snake.
     *
     * @param speed The new speed of the snake in pixels per frame.
     */
    public void setSpeed(int speed) {
        this.speed_XY = speed;
    }

    /**
     * Checks for collisions between the snake and a list of stones.
     * If a collision is detected, sets the isAlive flag to false.
     *
     * @param stones The list of stones to check for collisions.
     */
    public void checkCollisionsWithStones(List<Stone> stones) {
        for (Stone stone : stones) {
            Bounds snakeBounds = getBounds();
            Bounds stoneBounds = stone.getBounds();
            // System.out.println("Snake Bounds: " + snakeBounds);
            //System.out.println("Stone Bounds: " + stoneBounds);


            if (snakeBounds.intersects(stoneBounds)) {
                //  System.out.println("Collision detected");
                // Collision detected with a stone, set isAlive to false
                this.isAlive = false;
                break;
            }
        }
    }

    /**
     * Resets the snake to its initial state.
     * Sets the position, length, and direction of the snake to their initial values.
     */
    public void reset() {
        this.x = initialX;
        this.y = initialY;
        this.length = initialLength;
        this.isAlive = true;
        this.bodyPoints.clear();

        // Reset direction and rotation of the snake head
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = true;
        this.newImgSnakeHead = IMG_SNAKE_HEAD;
    }

    /**
     * Checks for collisions between the snake and a list of bombs.
     * If a collision is detected, sets the snake's speed to a specified value (e.g., 3).
     *
     * @param bombs The list of bombs to check for collisions.
     */
    public void checkCollisionsWithBombs(List<Speed> bombs) {
        for (Speed bomb : bombs) {
            Bounds snakeBounds = getBounds();
            Bounds bombBounds = bomb.getBounds();

            if (snakeBounds.intersects(bombBounds)) {
                setSpeed(3);
            }
        }
    }


}