package com.example;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Controls the New Game screen, allowing users to select a pet, name it, and create a new save file.
 */
public class NewGameController {
    private MediaPlayer mediaPlayer; // MediaPlayer for background music
    private String catSprite = "../../sprites/Cat Sprites/catv2Neutral.png"; // Path to cat sprite
    private String foxSprite = "../../sprites/Fox Sprites/foxNeutral.png"; // Path to fox sprite
    private String otterSprite = "../../sprites/SealAlien_neutral.png"; // Path to seal sprite

    @FXML
    private Button selectCatButton; // Button to select the cat pet
    @FXML
    private Button selectFoxButton; // Button to select the fox pet
    @FXML
    private Button selectOtterButton; // Button to select the seal pet
    @FXML
    private ImageView petImageView; // ImageView to display the selected pet
    @FXML
    private ImageView catImageView; // ImageView for the cat pet
    @FXML
    private ImageView foxImageView; // ImageView for the fox pet
    @FXML
    private ImageView otterImageView; // ImageView for the seal pet

    @FXML
    private AnchorPane namePetPane; // Pane for naming the pet
    @FXML
    private Button exitPetPaneButton; // Button to exit the naming pane
    @FXML
    private Button createNewGame; // Button to create a new game

    @FXML
    private TextField petNameField; // TextField for entering the pet's name
    @FXML
    private Label petNameLabel; // Label displaying the pet's name
    @FXML
    private Button setPetName; // Button to set the pet's name

    private String petType; // The type of pet selected
    public int saveFileNum; // The save file number

    /**
     * Initializes the New Game screen by setting up animations for buttons.
     */
    public void initialize() {
        addButtonAnimations(selectCatButton);
        addButtonAnimations(selectFoxButton);
        addButtonAnimations(selectOtterButton);
        addButtonAnimations(exitPetPaneButton);
        addButtonAnimations(createNewGame);
    }

    /**
     * Sets the save file number for the new game.
     *
     * @param num the save file number
     */
    public void setSaveFileNum(int num) {
        saveFileNum = num;
    }

    /**
     * Adds hover and click animations to the specified button.
     *
     * @param button the button to animate
     */
    private void addButtonAnimations(Button button) {
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
     * Sets the name of the pet and displays it in the UI.
     */
    @FXML
    private void setPetName() {
        petNameLabel.setText(petNameField.getText());
        createNewGame.setVisible(true);
    }

    /**
     * Prepares the screen for naming the pet after selection.
     */
    private void selectPet() {
        selectCatButton.setVisible(false);
        selectFoxButton.setVisible(false);
        selectOtterButton.setVisible(false);
        petNameLabel.setText("Please name your pet");
        petNameField.clear();

        namePetPane.setVisible(true);
        catImageView.setVisible(false);
        foxImageView.setVisible(false);
        otterImageView.setVisible(false);
    }

    /**
     * Exits the pet naming pane and returns to the pet selection screen.
     */
    @FXML
    private void exitNamePetPane() {
        namePetPane.setVisible(false);
        selectCatButton.setVisible(true);
        selectFoxButton.setVisible(true);
        selectOtterButton.setVisible(true);
        catImageView.setVisible(true);
        foxImageView.setVisible(true);
        otterImageView.setVisible(true);
        createNewGame.setVisible(false);
    }

    /**
     * Selects the cat pet and updates the UI accordingly.
     */
    @FXML
    private void selectCat() {
        petImageView.setImage(new Image(getClass().getResourceAsStream(catSprite)));
        petType = "Cat";
        selectPet();
    }

    /**
     * Selects the fox pet and updates the UI accordingly.
     */
    @FXML
    private void selectFox() {
        petImageView.setImage(new Image(getClass().getResourceAsStream(foxSprite)));
        petType = "Fox";
        selectPet();
    }

    /**
     * Selects the seal pet and updates the UI accordingly.
     */
    @FXML
    private void selectOtter() {
        petImageView.setImage(new Image(getClass().getResourceAsStream(otterSprite)));
        petType = "Seal";
        selectPet();
    }

    /**
     * Creates a new game with the selected pet and name, saving it to the specified save file.
     */
    @FXML
    private void createNewGame() {
        System.out.println("creating new game");
        GameManager gameManager = new GameManager();
        gameManager.newGame(saveFileNum, petNameField.getText(), petType);
        String saveFilePath = gameManager.getSaveFile().getSaveFilePath();

        if (saveFilePath.equals("src/main/java/com/example/data/sf1.json")) {
            saveFileNum = 1;
        } else if (saveFilePath.equals("src/main/java/com/example/data/sf2.json")) {
            saveFileNum = 2;
        } else if (saveFilePath.equals("src/main/java/com/example/data/sf3.json")) {
            saveFileNum = 3;
        } else if (saveFilePath.equals("src/main/java/com/example/data/sf4.json")) {
            saveFileNum = 4;
        }

        App.loadGameplay(saveFileNum);
    }
}
