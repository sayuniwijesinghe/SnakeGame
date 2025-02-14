package com.example.snakegame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * Controller class for the main menu of the Snake Game.
 */
public class HelloController {

    @FXML
    private ImageView backgroundImage;

    /**
     * Initializes the controller. Sets the background image for the main menu.
     */
    public void initialize() {
        // Set the background image
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/gamemenu.png")));
        backgroundImage.setImage(image);
    }

    /**
     * Handles the button click event for the "Difficulty" button.
     * Opens a new window for selecting the difficulty level
     */
    @FXML
    private void DifficultyButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/difficulty-level.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Select Difficulty Level");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the button click event for the "Settings" button
     * Opens a new window for game settings
     */
    @FXML
    private void SettingsButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/settings.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Settings");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
