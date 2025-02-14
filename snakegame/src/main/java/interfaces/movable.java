package interfaces;

import javafx.scene.canvas.GraphicsContext;

public interface movable {
    /**
     * Draws the movable object on the provided GraphicsContext.
     *
     * @param gc The GraphicsContext on which the object should be drawn.
     */
    void draw(GraphicsContext gc);
}
