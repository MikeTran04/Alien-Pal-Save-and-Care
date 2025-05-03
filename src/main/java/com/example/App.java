package com.example;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * JavaFX App
 * 
 * This class is the entry point for the Alien Pet game application.
 * It handles scene management, background music, sound effects, and
 * various user interactions within the game.
 */
public class App extends Application {

    // Main scenes in the application
    private static Scene scene;
    private static Scene shop;
    private static Scene inventory;
    private static Scene newGame;

    // Background music tracks
    private static Media mainMenuMusic;
    private static Media shopMusic;
    private static Media gameplayMusic;
    
    // Sound effects
    private static Media clickSound;
    private static Media eatSound;
    private static Media playSound;
    private static Media sleepSound;
    private static Media finishedSound;

    // Media players for music and sound effects
    private static MediaPlayer mediaPlayer;
    private static MediaPlayer clickSoundPlayer;
    private static MediaPlayer eatSoundPlayer;
    private static MediaPlayer playSoundPlayer;
    private static MediaPlayer sleepSoundPlayer;
    private static MediaPlayer finishedSoundPlayer;

    // Stage and game components
    private static Stage gameStage;
    private static GameplayScene gameplayScene;
    private static double volume;
    private static double SFXVolume;
    private static Settings settings;
    private static NewGameController newGameController;
    private static GameLoop looping;
    private static GameManager gameManager = new GameManager();
    private static SaveFile saveFile;

    /**
     * Starts the JavaFX application.
     *
     * @param stage the primary stage for this application
     * @throws IOException if an FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        settings = new Settings();

        scene = new Scene(loadFXML("mainmenu"));
        gameStage = stage;
        gameStage.setTitle("Alien Pet: Save and Care");
        gameStage.resizableProperty().setValue(Boolean.FALSE);
        gameStage.setScene(scene);
        gameStage.show();

        // Load the shop, inventory, and new game FXML and scenes
        shop = new Scene(loadFXML("shop"));
        inventory = new Scene(loadFXML("inventory"));

        FXMLLoader newGameLoader = new FXMLLoader(App.class.getResource("newgame.fxml"));
        Parent newGameRoot = newGameLoader.load();
        newGame = new Scene(newGameRoot);
        newGameController = newGameLoader.getController();

        gameplayScene = new GameplayScene(gameStage);

        try {
            // Add background music
            mainMenuMusic = new Media(getClass().getResource("/com/example/music/main_menu_music.mp3").toExternalForm());
            shopMusic = new Media(getClass().getResource("/com/example/music/shop_music.mp3").toExternalForm());
            gameplayMusic = new Media(getClass().getResource("/com/example/music/gameplay_music.mp3").toExternalForm());

            // Add sound effects
            clickSound = new Media(getClass().getResource("/com/example/music/click.mp3").toExternalForm());
            eatSound = new Media(getClass().getResource("/com/example/music/eating.mp3").toExternalForm());
            playSound = new Media(getClass().getResource("/com/example/music/woohoo.mp3").toExternalForm());
            sleepSound = new Media(getClass().getResource("/com/example/music/sleeping.mp3").toExternalForm());
            finishedSound = new Media(getClass().getResource("/com/example/music/finished_action.mp3").toExternalForm());

            clickSoundPlayer = new MediaPlayer(clickSound);
            eatSoundPlayer = new MediaPlayer(eatSound);
            playSoundPlayer = new MediaPlayer(playSound);
            sleepSoundPlayer = new MediaPlayer(sleepSound);
            finishedSoundPlayer = new MediaPlayer(finishedSound);

            volume = 50;
            SFXVolume = 50;

            mediaPlayer = new MediaPlayer(mainMenuMusic);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();

            setVolume(volume);
            setSFXVolume(SFXVolume);
        } catch (MediaException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the root scene to the specified FXML file.
     *
     * @param fxml the name of the FXML file (without extension)
     * @throws IOException if the FXML file cannot be loaded
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    /**
     * Loads the main menu scene and stops the game loop if active.
     */
    public static void loadMenu() {
        try {
            mediaPlayer.dispose();
            mediaPlayer = new MediaPlayer(mainMenuMusic);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();
        } catch (MediaException e) {
            e.printStackTrace();
        }

        looping.stop();
        gameManager.saveGame(gameManager.getSaveFile().getSaveFilePath());
        gameStage.setScene(scene);
    }

    /**
     * Loads the new game scene.
     *
     * @param saveFileNum the save file number to associate with the new game
     */
    public static void loadNewGame(int saveFileNum) {
        newGameController.setSaveFileNum(saveFileNum);
        gameStage.setScene(newGame);
    }

    /**
     * Loads the gameplay scene and initializes the game loop with the specified save file.
     *
     * @param saveFileNum the save file number to load
     */
    public static void loadGameplay(int saveFileNum) {
        try {
            mediaPlayer.dispose();
            mediaPlayer = new MediaPlayer(gameplayMusic);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();
        } catch (MediaException e) {
            e.printStackTrace();
        }

        gameManager = new GameManager();
        gameManager.loadGame("src/main/java/com/example/data/sf" + saveFileNum + ".json");
        saveFile = gameManager.getSaveFile();

        gameplayScene.setSaveFile(saveFile);
        gameStage.setScene(GameplayScene.getGameplay());
        looping = new GameLoop(saveFile);
    }

    /**
     * Gets the current save file.
     *
     * @return the current save file
     */
    public static SaveFile getSaveFile() {
        return saveFile;
    }

    /**
     * Loads the gameplay scene without restarting the game loop.
     */
    public static void loadGameplayScreen() {
        try {
            mediaPlayer.dispose();
            mediaPlayer = new MediaPlayer(gameplayMusic);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();
        } catch (MediaException e) {
            e.printStackTrace();
        }
        gameStage.setScene(GameplayScene.getGameplay());
    }

    /**
     * Loads the shop scene and updates the balance label.
     */
    public static void loadShop() {
        try {
            mediaPlayer.dispose();
            mediaPlayer = new MediaPlayer(shopMusic);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();

            FXMLLoader loader = new FXMLLoader(App.class.getResource("shop.fxml"));
            Parent shopRoot = loader.load();
            ShopController shopController = loader.getController();
            shopController.getBalance();
            shop.setRoot(shopRoot);
        } catch (IOException | MediaException e) {
            e.printStackTrace();
        }

        gameStage.setScene(shop);
    }

    /**
     * Loads the inventory scene in a new modal window.
     */
    public static void loadInventory() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/example/inventory.fxml"));
            Parent inventoryRoot = loader.load();

            InventoryController inventoryController = loader.getController();
            inventoryController.setInventory(saveFile.getSaveFileNum());

            Stage inventoryStage = new Stage();
            inventoryStage.initModality(Modality.APPLICATION_MODAL);
            inventoryStage.setTitle("Inventory");
            inventoryStage.setScene(new Scene(inventoryRoot));

            Scene currentScene = gameStage.getScene();
            if (currentScene != null) {
                currentScene.getRoot().setEffect(new GaussianBlur(10));
            }

            inventoryStage.setOnHidden(event -> {
                if (currentScene != null) {
                    currentScene.getRoot().setEffect(null);
                }
            });

            inventoryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the primary game stage.
     *
     * @return the primary {@code Stage} of the application
     */
    public static Stage getGameStage() {
        return gameStage;
    }

    /**
     * Retrieves the gameplay scene object.
     *
     * @return the {@code GameplayScene} object
     */
    public static GameplayScene getGameplayScene() {
        return gameplayScene;
    }

    /**
     * Gets the current background music volume.
     *
     * @return the background music volume as a double value
     */
    public static double getVol() {
        return mediaPlayer.getVolume();
    }

    /**
     * Gets the current sound effects (SFX) volume.
     *
     * @return the sound effects volume as a double value
     */
    public static double getSFXVol() {
        return clickSoundPlayer.getVolume();
    }

    /**
     * Sets the background music volume.
     *
     * @param vol the volume level as a percentage (0-100)
     */
    public static void setVolume(double vol) {
        volume = vol / 100;
        mediaPlayer.setVolume(volume);
    }

    /**
     * Sets the sound effects (SFX) volume.
     *
     * @param vol the volume level as a percentage (0-100)
     */
    public static void setSFXVolume(double vol) {
        SFXVolume = vol / 100;

        clickSoundPlayer.setVolume(SFXVolume);
        eatSoundPlayer.setVolume(SFXVolume);
        playSoundPlayer.setVolume(SFXVolume);
        sleepSoundPlayer.setVolume(SFXVolume);
        finishedSoundPlayer.setVolume(SFXVolume);
    }

    /**
     * Retrieves the current settings object.
     *
     * @return the {@code Settings} object
     */
    public static Settings getSettings() {
        return settings;
    }

    /**
     * Plays the click sound effect.
     */
    public static void playClickSound() {
        try {
            clickSoundPlayer.dispose();
            clickSoundPlayer = new MediaPlayer(clickSound);
            clickSoundPlayer.setVolume(SFXVolume);
            clickSoundPlayer.play();
        } catch (MediaException e) {
            e.printStackTrace();
        }
        clickSoundPlayer.play();
    }

    /**
     * Plays the eating sound effect.
     */
    public static void playEatSound() {
        try {
            eatSoundPlayer.dispose();
            eatSoundPlayer = new MediaPlayer(eatSound);
            eatSoundPlayer.setVolume(SFXVolume);
            eatSoundPlayer.play();
        } catch (MediaException e) {
            e.printStackTrace();
        }
        eatSoundPlayer.play();
    }

    /**
     * Plays the sound effect for playing with the pet.
     */
    public static void playPlayingSound() {
        try {
            playSoundPlayer.dispose();
            playSoundPlayer = new MediaPlayer(playSound);
            playSoundPlayer.setVolume(SFXVolume);
            playSoundPlayer.play();
        } catch (MediaException e) {
            e.printStackTrace();
        }
        playSoundPlayer.play();
    }

    /**
     * Plays the sleeping sound effect.
     */
    public static void playSleepSound() {
        try {
            sleepSoundPlayer.dispose();
            sleepSoundPlayer = new MediaPlayer(sleepSound);
            sleepSoundPlayer.setVolume(SFXVolume);
            sleepSoundPlayer.play();
        } catch (MediaException e) {
            e.printStackTrace();
        }
        sleepSoundPlayer.play();
    }

    /**
     * Plays the sound effect for a completed action.
     */
    public static void playFinishedActionSound() {
        try {
            finishedSoundPlayer.dispose();
            finishedSoundPlayer = new MediaPlayer(finishedSound);
            finishedSoundPlayer.setVolume(SFXVolume);
            finishedSoundPlayer.play();
        } catch (MediaException e) {
            e.printStackTrace();
        }
        finishedSoundPlayer.play();
    }

    /**
     * Loads an FXML file and returns the root {@code Parent} object.
     *
     * @param fxml the name of the FXML file (without the ".fxml" extension)
     * @return the root {@code Parent} object of the loaded FXML
     * @throws IOException if the FXML file cannot be loaded
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * The main entry point of the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}