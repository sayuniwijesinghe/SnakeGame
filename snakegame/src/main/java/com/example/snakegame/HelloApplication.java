package com.example.snakegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main application class for the Snake Game.
 */
public class HelloApplication extends Application {

    /**
     * The entry point for the JavaFX application.
     *
     * @param stage The primary stage for the application.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file for the main view
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/hello-view.fxml"));

        // Create a scene with the loaded FXML file and set the stage properties
        Scene scene = new Scene(fxmlLoader.load(), 870, 490);
        stage.setTitle("SNAKE GAME!");
        stage.setScene(scene);

        // Display the primary stage
        stage.show();
    }

    /**
     * The main entry point for the application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Launch the JavaFX application
        launch();
    }
}
