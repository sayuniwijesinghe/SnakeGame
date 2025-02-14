package model;

import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import util.ImageUtil;

import java.util.List;
import java.util.Random;

/**
 * Represents a Stone object in the Snake Game, acting as an obstacle in the game.
 */
public class Stone extends SnakeObject {
    /** The image associated with the Stone object. */
    private static final Image IMAGE_STONE = ImageUtil.images.get("stone");

    /**
     * Constructs a Stone object with a non-overlapping position with existing stones.
     *
     * @param existingStones The list of existing stones to avoid overlapping positions.
     */
    public Stone(List<Stone> existingStones) {
        this.isAlive = true;
        this.i = IMAGE_STONE;
        this.w = (int) i.getWidth();
        this.h = (int) i.getHeight();
        generateNonOverlappingPosition(existingStones);
    }

    /**
     * Generates a non-overlapping position for the Stone object with existing stones.
     *
     * @param existingStones The list of existing stones to avoid overlapping positions.
     */
    private void generateNonOverlappingPosition(List<Stone> existingStones) {
        Random random = new Random();
        boolean overlap;

        do {
            overlap = false;
            this.x = random.nextInt(650) + 20;
            this.y = random.nextInt(350) + 20;

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
     * Draws the Stone object on the specified GraphicsContext.
     *
     * @param gc The GraphicsContext on which to draw the Stone object.
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(IMAGE_STONE, x, y);
    }

    /**
     * Gets the bounds of the Stone object for collision detection.
     *
     * @return The Bounds object representing the bounds of the Stone object.
     */
    public Bounds getBounds() {
        // Use a square bounding box that is centered on the stone image
        double margin = 10; // Adjust the margin as needed
        double size = Math.min(w - 2 * margin, h - 2 * margin);

        // Calculate the centered x coordinate
        double centerX = x + (w - size) / 2.0;

        return new Rectangle(centerX, y + margin, size, size).getBoundsInParent();
    }
}
