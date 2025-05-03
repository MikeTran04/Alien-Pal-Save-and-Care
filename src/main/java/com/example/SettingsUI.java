package com.example;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Represents the user interface for the settings menu.
 * 
 * Provides sliders for adjusting music and sound effects volumes, 
 * and buttons to navigate to the tutorial, parental controls, or main menu.
 * Also includes a close button to exit the settings menu.
 */
public class SettingsUI implements EventHandler<ActionEvent> {
    private final static Pane settings = new Pane();

    // References to other UI components
    public static ParentalControlsUI pControlsUI;
    public static TutorialUI tutorialUI;

    // Settings data object
    private Settings settingsData;

    // Slider size for volume controls
    int sliderSize = 180;

    // UI elements for music and SFX volume controls
    Text mVolText = new Text("Music Volume");
    Slider mVolSlider = new Slider(0, 100, 50);

    Text sfxVolText = new Text("SFX Volume");
    Slider sfxVolSlider = new Slider(0, 100, 50);

    // Buttons for various settings options
    Button tutorialButton = new Button();
    Button pControlsButton = new Button();
    Button mainMenuButton = new Button();
    Button closeSettingsButton = new Button();

    // Constructor for initializing the settings UI
    public SettingsUI() {
        // Initialize other UI components
        pControlsUI = new ParentalControlsUI();
        tutorialUI = new TutorialUI();

        // Setup the settings UI layout and elements
        setupSettingsUI();
    }

    // Configures the layout and functionality of the settings UI
    private void setupSettingsUI() {
        // Create a transparent black background
        Rectangle black = new Rectangle(700.0, 550.0, Color.BLACK);
        black.opacityProperty().setValue(0.5);

        // Create a white background for the settings menu
        Rectangle bg = new Rectangle(200.0, 300.0, Color.WHITE);
        bg.setLayoutX(250);
        bg.setLayoutY(100);
        bg.setStrokeType(StrokeType.OUTSIDE);
        bg.setStroke(Color.BLACK);

        // Title text for the settings menu
        Text title = new Text("Settings");
        title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 18));
        title.setLayoutX(245 + (title.getLayoutBounds().getWidth()));
        title.setLayoutY(120);

        // Configure the music volume slider and text
        mVolText.setFont(Font.font("Comic Sans MS", FontWeight.LIGHT, FontPosture.REGULAR, 14));
        mVolText.setLayoutX(260);
        mVolText.setLayoutY(160);
        setupSlider(mVolSlider, 180);
        mVolSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                // Update music volume in the app (and potentially settingsData)
                App.setVolume((double) newValue);
            }
        });

        // Configure the SFX volume slider and text
        sfxVolText.setFont(Font.font("Comic Sans MS", FontWeight.LIGHT, FontPosture.REGULAR, 14));
        sfxVolText.setLayoutX(260);
        sfxVolText.setLayoutY(220);
        setupSlider(sfxVolSlider, 240);
        sfxVolSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                // Update SFX volume in the app
                App.setSFXVolume((double) newValue);
            }
        });

        // Configure buttons for tutorial, parental controls, and main menu
        setupSettingsButton(tutorialButton, "Tutorial", 270);
        setupSettingsButton(pControlsButton, "Parental Controls", 310);
        setupSettingsButton(mainMenuButton, "Save and Quit", 350);

        // Configure the close button
        closeSettingsButton.setText("X");
        closeSettingsButton.setLayoutX(427);
        closeSettingsButton.setLayoutY(100);
        closeSettingsButton.setOnAction(this);

        // Add all components to the settings pane
        settings.getChildren().add(black);
        settings.getChildren().add(bg);
        settings.getChildren().add(title);
        settings.getChildren().add(mVolText);
        settings.getChildren().add(mVolSlider);
        settings.getChildren().add(sfxVolText);
        settings.getChildren().add(sfxVolSlider);
        settings.getChildren().add(tutorialButton);
        settings.getChildren().add(pControlsButton);
        settings.getChildren().add(mainMenuButton);
        settings.getChildren().add(closeSettingsButton);

        // Initially set the settings menu off-screen
        settings.setLayoutY(-1000);
    }

    // Configures a button with specific text and layout position
    private void setupSettingsButton(Button btn, String txt, int yPos) {
        int buttonWidth = 140;
        int buttonHeight = 30;

        btn.setText(txt);
        btn.setFont(Font.font("Comic Sans MS", FontWeight.LIGHT, FontPosture.REGULAR, 12));
        btn.setPrefSize(buttonWidth, buttonHeight);
        btn.setLayoutX(280);
        btn.setLayoutY(yPos);
        btn.setOnAction(this);
    }

    // Configures a slider with a specific layout position
    private void setupSlider(Slider slider, int yPos) {
        slider.setPrefWidth(sliderSize);
        slider.setLayoutX(260);
        slider.setLayoutY(yPos);
    }

    // Returns the settings pane
    public Pane getSettings() {
        return settings;
    }

    // Opens the settings menu by moving it into view
    public void openSettings() {
        settings.setLayoutY(-15);
    }

    // Closes the settings menu by moving it off-screen
    private void closeSettings() {
        settings.setLayoutY(-1000);
    }

    // Opens the tutorial UI
    private void openTutorial() {
        closeSettings();
        // Bring the tutorial pane into view
        tutorialUI.getTutorial().setLayoutX(0);
    }

    // Opens the parental controls UI
    private void openParentalControls() {
        closeSettings();
        if (!pControlsUI.getPassword().equals("")) {
            // Show the parental controls login screen
            pControlsUI.getLoginScreen().setLayoutX(0);
        }
        // Reset the parental controls UI state
        pControlsUI.closePassResetMenu();
        pControlsUI.clearPassText();
        pControlsUI.getParentalControls().setLayoutY(0);
    }

    // Loads the main menu and saves the current game state
    private void loadMainMenu() {
        // Save the current game state
        // GameManager.saveGame();
        // Load the main menu scene
        App.loadMenu();
        closeSettings();
    }

    @Override
    public void handle(ActionEvent event) {
        // Play a click sound for all button actions
        App.playClickSound();

        // Handle button actions
        if (event.getSource() == tutorialButton) {
            openTutorial();
        } else if (event.getSource() == pControlsButton) {
            openParentalControls();
        } else if (event.getSource() == mainMenuButton) {
            loadMainMenu();
        } else if (event.getSource() == closeSettingsButton) {
            closeSettings();
        }
    }
}
