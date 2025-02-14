package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;

/**
 * Represents an abstract base class for objects in the Snake Game, providing common properties and methods.
 */
public abstract class SnakeObject {
    /** The x-coordinate of the object. */
    public int x;

    /** The y-coordinate of the object. */
    public int y;

    /** The image associated with the object. */
    Image i;

    /** The width of the object. */
    int w;

    /** The height of the object. */
    int h;

    /** Indicates whether the object is alive or not. */
    public boolean isAlive;

    /**
     * Draws the object on the specified GraphicsContext.
     *
     * @param gc The GraphicsContext on which to draw the object.
     */
    public abstract void draw(GraphicsContext gc);

    /**
     * Gets a Rectangle representing the bounds of the object.
     *
     * @return A Rectangle object representing the bounds of the object.
     */
    public Rectangle getRectangle() {
        return new Rectangle(x, y, w, h);
    }
}
