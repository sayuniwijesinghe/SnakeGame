package com.example.snakegame;

import javafx.fxml.FXML;
import view.Level2View;
import view.SnakeView;

/**
 * Controller class for managing difficulty levels in the Snake Game.
 */
public class DifficultyLevelController {
    private SnakeView snakeView;
    private Level2View snakeView2;

    /**
     * Handles the Easy difficulty level button click.
     * If the SnakeView is not initialized, it creates a new instance and starts the SnakeView.
     */
    @FXML
    private void Easy() {
        if (snakeView == null) {
            snakeView = new SnakeView();
            snakeView.startSnakeView();
        }
    }

    /**
     * Handles the Hard difficulty level button click.
     * If the Level2View is not initialized, it creates a new instance, starts Level2View,
     * and assigns it to the snakeView2 variable.
     */
    @FXML
    private void Hard() {
        if (snakeView2 == null) {
            Level2View level2View = new Level2View();
            level2View.startLevel2View();
            snakeView2 = level2View;
        }
    }
}
