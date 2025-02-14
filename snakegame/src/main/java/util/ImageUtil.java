package util;

import util.GameUtil;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for managing and providing images used in the game.
 */
public class ImageUtil {

    /**
     * A map to store and retrieve images using their respective keys.
     */
    public static Map<String, Image> images = new HashMap<>();

    /**
     * Static block to load and initialize images.
     */
    static {
        // Snake images
        images.put("snake-head-right", GameUtil.getImage("snake-head-right.png"));
        images.put("snake-body", GameUtil.getImage("snake-body.png"));
        images.put("snake-logo", GameUtil.getImage("snake-logo.png"));

        // Obstacle and food images
        images.put("0", GameUtil.getImage("food-kiwi.png"));
        images.put("1", GameUtil.getImage("food-lemon.png"));
        images.put("2", GameUtil.getImage("food-litchi.png"));
        images.put("3", GameUtil.getImage("food-mango.png"));
        images.put("4", GameUtil.getImage("food-apple.png"));
        images.put("5", GameUtil.getImage("food-banana.png"));
        images.put("6", GameUtil.getImage("food-blueberry.png"));
        images.put("7", GameUtil.getImage("food-cherry.png"));
        images.put("8", GameUtil.getImage("food-durian.png"));
        images.put("9", GameUtil.getImage("food-grape.png"));
        images.put("10", GameUtil.getImage("food-grapefruit.png"));
        images.put("11", GameUtil.getImage("food-peach.png"));
        images.put("12", GameUtil.getImage("food-pear.png"));
        images.put("13", GameUtil.getImage("food-orange.png"));
        images.put("14", GameUtil.getImage("food-pineapple.png"));
        images.put("15", GameUtil.getImage("food-strawberry.png"));
        images.put("16", GameUtil.getImage("food-watermelon.png"));
        images.put("17", GameUtil.getImage("food-pitaya.png"));

        // Special items
        images.put("bomb", GameUtil.getImage("speed.png"));
        images.put("stone", GameUtil.getImage("stone.png"));

        // UI and background images
        images.put("UI-background", GameUtil.getImage("UI-background.png"));
        images.put("game-over", GameUtil.getImage("game-over.JPG"));
    }
}
