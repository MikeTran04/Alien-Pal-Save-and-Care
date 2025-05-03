package com.example;

import java.io.IOException;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;

/**
 * Controls the main menu of the application, handling user interactions with the menu buttons
 * and loading save files or starting new games.
 */
public class MainMenuController {
    private MediaPlayer mediaPlayer; // MediaPlayer for handling menu music

    @FXML
    private Button playButton; // Button to load the save files menu
    @FXML
    private Button saveFileOne; // Button representing save file 1
    @FXML
    private Button saveFileTwo; // Button representing save file 2
    @FXML
    private Button saveFileThree; // Button representing save file 3
    @FXML
    private Button saveFileFour; // Button representing save file 4
    @FXML
    private Button exitButton; // Button to exit the application

    @FXML
    private static AnchorPane rootPane; // Root pane for the main menu UI

    private static Stage newGameStage; // Stage for the new game window

    /**
     * Initializes the main menu controller by setting up initial button visibility
     * and adding animations for button interactions.
     */
    public void initialize() {
        // Set initial visibility of save files to hidden
        saveFileOne.setVisible(false);
        saveFileTwo.setVisible(false);
        saveFileThree.setVisible(false);
        saveFileFour.setVisible(false);

        // Add hover and click animations for all buttons
        addButtonAnimations(playButton);
        addButtonAnimations(saveFileOne);
        addButtonAnimations(saveFileTwo);
        addButtonAnimations(saveFileThree);
        addButtonAnimations(saveFileFour);
        addButtonAnimations(exitButton);
    }

    /**
     * Displays the save file buttons and loads the save file states.
     */
    @FXML
    private void loadSaveFiles() {
        // Hide play button and exit button
        playButton.setVisible(false);
        exitButton.setVisible(false);

        // Load save files and set button texts
        saveFileExist(saveFileOne, 1);
        saveFileExist(saveFileTwo, 2);
        saveFileExist(saveFileThree, 3);
        saveFileExist(saveFileFour, 4);

        // Set button actions based on whether the save file exists
        if (saveFileOne.getText().equals("New Game")) {
            saveFileOne.setOnMouseClicked(event -> loadFile(1, "new"));
        } else {
            saveFileOne.setOnMouseClicked(event -> loadFile(1, "load"));
        }
        if (saveFileTwo.getText().equals("New Game")) {
            saveFileTwo.setOnMouseClicked(event -> loadFile(2, "new"));
        } else {
            saveFileTwo.setOnMouseClicked(event -> loadFile(2, "load"));
        }
        if (saveFileThree.getText().equals("New Game")) {
            saveFileThree.setOnMouseClicked(event -> loadFile(3, "new"));
        } else {
            saveFileThree.setOnMouseClicked(event -> loadFile(3, "load"));
        }
        if (saveFileFour.getText().equals("New Game")) {
            saveFileFour.setOnMouseClicked(event -> loadFile(4, "new"));
        } else {
            saveFileFour.setOnMouseClicked(event -> loadFile(4, "load"));
        }

        // Show save file buttons
        saveFileOne.setVisible(true);
        saveFileTwo.setVisible(true);
        saveFileThree.setVisible(true);
        saveFileFour.setVisible(true);
    }

    /**
     * Checks if a save file exists for the given file number and updates the button text accordingly.
     *
     * @param saveFileButton the button to update
     * @param fileNumber     the save file number
     */
    private void saveFileExist(Button saveFileButton, int fileNumber) {
        try {
            String result = GameManager.saveFileExists(fileNumber);
            saveFileButton.setText(result);
        } catch (Exception e) {
            e.printStackTrace();
            saveFileButton.setText("Error loading save file " + fileNumber);
        }
    }

    /**
     * Loads a save file or starts a new game based on the specified action.
     *
     * @param saveFileNum the save file number to load
     * @param action      the action to perform ("load" or "new")
     */
    @FXML
    private void loadFile(int saveFileNum, String action) {
        // Reset visibility of save file buttons and main buttons
        saveFileOne.setVisible(false);
        saveFileTwo.setVisible(false);
        saveFileThree.setVisible(false);
        saveFileFour.setVisible(false);
        playButton.setVisible(true);
        exitButton.setVisible(true);

        if (action.equals("load")) {
            App.loadGameplay(saveFileNum);
        } else {
            App.loadNewGame(saveFileNum);
        }
    }

    /**
     * Adds hover and click animations to a button.
     *
     * @param button the button to animate
     */
    private void addButtonAnimations(Button button) {
        // Hover animation
        button.setOnMouseEntered(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), button);
            st.setToX(1.1);
            st.setToY(1.1);
            st.play();
        });
        button.setOnMouseExited(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), button);
            st.setToX(1.0);
            st.setToY(1.0);
            st.play();
        });

        // Click animation
        button.setOnMousePressed(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(100), button);
            st.setToX(0.9);
            st.setToY(0.9);
            st.play();
        });
        button.setOnMouseReleased(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(100), button);
            st.setToX(1.1);
            st.setToY(1.1);
            st.play();
        });
    }

    /**
     * Exits the application.
     */
    @FXML
    private void exitGame() {
        System.exit(0);
    }

    /**
     * Closes the new game window and reopens the main menu stage.
     */
    public static void loadMenu() {
        newGameStage.close();

        Stage mainMenuStage = (Stage) rootPane.getScene().getWindow();
        mainMenuStage.show();
    }
}
