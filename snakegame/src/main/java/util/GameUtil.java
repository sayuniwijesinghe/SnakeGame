package util;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Utility class for common game-related operations.
 */
public class GameUtil {

    /**
     * Loads an image from the specified path.
     *
     * @param path The path to the image resource.
     * @return The loaded Image object or null if the resource is not found.
     */
    public static Image getImage(String path) {
        Image image = null;
        try (InputStream input = GameUtil.class.getClassLoader().getResourceAsStream(path)) {
            if (input != null) {
                image = new Image(input);
            } else {
                System.err.println("Resource not found: " + path);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading image: " + path);
            // Return a default image or throw an exception if cannot find the error
        }

        if (image == null) {
            System.out.println("Resolved path: " + GameUtil.class.getClassLoader().getResource(path));
        }

        return image;
    }

    /**
     * Rotates the given image by the specified degree.
     *
     * @param image  The original Image to be rotated.
     * @param degree The degree of rotation.
     * @return The rotated Image.
     */
    public static Image rotateImage(final Image image, final int degree) {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();

        BufferedImage rotatedImage = new BufferedImage(w, h, bufferedImage.getType());
        Graphics2D graphics2d = rotatedImage.createGraphics();
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
        graphics2d.drawImage(bufferedImage, null, 0, 0);
        graphics2d.dispose();

        return SwingFXUtils.toFXImage(rotatedImage, null);
    }
}
