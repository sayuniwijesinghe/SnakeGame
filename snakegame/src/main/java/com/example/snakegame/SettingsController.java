package com.example.snakegame;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Controller class for the settings window of the Snake Game.
 */
public class SettingsController {

    private Stage stage;

    @FXML
    private ChoiceBox<String> themeChoiceBox;
    @FXML
    private StackPane backgroundPane;

    /**
     * Initializes the controller. Sets the default choices for the theme choice box.
     */
    @FXML
    private void initialize() {
        // Set default choices
        themeChoiceBox.setValue("Original");
    }

    /**
     * Handles the theme change button click event.
     * Changes the background color based on the selected theme.
     */
    @FXML
    private void changeTheme() {
        // Implement theme change logic
        String selectedTheme = themeChoiceBox.getValue();

        switch (selectedTheme) {
            case "Original":
                backgroundPane.setStyle("-fx-background-color: #FFFFFF;"); // White background
                break;
            case "Green":
                backgroundPane.setStyle("-fx-background-color: #00FF00;"); // Green background
                break;
            case "Yellow":
                backgroundPane.setStyle("-fx-background-color: #FFFF00;"); // Yellow background
                break;
            case "Blue":
                backgroundPane.setStyle("-fx-background-color: #0000FF;"); // Blue background
                break;
        }
    }

    /**
     * Handles the submit button click event.
     * Invokes the theme change method.
     */
    @FXML
    private void handleSubmit() {
        changeTheme();
    }
}
