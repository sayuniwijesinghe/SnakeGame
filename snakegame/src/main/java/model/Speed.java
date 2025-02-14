package model;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import util.ImageUtil;

import java.util.List;
import java.util.Random;

/**
 * Represents a Speed object in the Snake Game, which acts as a bomb affecting the snake's speed.
 */
public class Speed extends SnakeObject {
    /** The image associated with the Speed object. */
    private static final Image IMAGE_BOMB = ImageUtil.images.get("bomb");

    /**
     * Constructs a Speed object with a non-overlapping position with existing stones.
     *
     * @param existingStones The list of existing stones to avoid overlapping positions.
     */
    public Speed(List<Stone> existingStones) {
        this.isAlive = true;
        this.i = IMAGE_BOMB;
        this.w = (int) i.getWidth();
        this.h = (int) i.getHeight();
        generateNonOverlappingPosition(existingStones);
    }

    /**
     * Generates a non-overlapping position for the Speed object with existing stones.
     *
     * @param existingStones The list of existing stones to avoid overlapping positions.
     */
    private void generateNonOverlappingPosition(List<Stone> existingStones) {
        Random random = new Random();
        boolean overlap;

        do {
            overlap = false;
            this.x = random.nextInt(800) + 20;
            this.y = random.nextInt(500) + 20;

            Bounds newBounds = getBounds();

            for (Stone stone : existingStones) {
                Bounds existingBounds = stone.getBounds();
                if (newBounds.intersects(existingBounds)) {
                    overlap = true;
                    break;
                }
            }
        } while (overlap);
    }

    /**
     * Draws the Speed object on the specified GraphicsContext.
     *
     * @param gc The GraphicsContext on which to draw the Speed object.
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(IMAGE_BOMB, x, y);
    }

    /**
     * Gets the bounds of the Speed object for collision detection.
     *
     * @return The Bounds object representing the bounds of the Speed object.
     */
    public Bounds getBounds() {
        // Square bounding box that is centered on the bomb image
        double margin = 10; // Margin as needed
        double size = Math.min(w - 2 * margin, h - 2 * margin);

        // Calculate the centered x coordinate
        double centerX = x + (w - size) / 2.0;

        return new Rectangle(centerX, y + margin, size, size).getBoundsInParent();
    }
}
