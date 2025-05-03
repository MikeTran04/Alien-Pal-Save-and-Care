package com.example;

/**
 * Represents the main game loop that manages updates to the game logic.
 * The loop runs on a separate thread and ensures consistent updates to the game state.
 */
public class GameLoop {

    private transient boolean running = false;
    private GamePlay play;
    private transient final int TARGET_TIME_BETWEEN_UPDATES = 1000; // Milliseconds
    private GameManager gameManager;
    private String saveFilePath;

    /**
     * Constructs a {@code GameLoop} with the given save file.
     *
     * @param saveFile the save file to initialize the game loop with
     */
    public GameLoop(SaveFile saveFile) {
        this.saveFilePath = saveFile.getSaveFilePath();
        start(saveFile);
    }

    /**
     * Checks if the game loop is currently running.
     *
     * @return {@code true} if the game loop is running; {@code false} otherwise
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Sets the running status of the game loop.
     *
     * @param running {@code true} to start the loop; {@code false} to stop it
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * Retrieves the current {@code GamePlay} instance.
     *
     * @return the current {@code GamePlay} instance
     */
    public GamePlay getPlay() {
        return play;
    }

    /**
     * Sets the current {@code GamePlay} instance.
     *
     * @param play the {@code GamePlay} instance to set
     */
    public void setPlay(GamePlay play) {
        this.play = play;
    }

    /**
     * Starts the game loop and initializes the {@code GamePlay} instance using the given save file.
     *
     * @param saveFile the save file to initialize the game state
     */
    public void start(SaveFile saveFile) {
        running = true;

        // Ensure the save file loaded correctly
        if (saveFile != null) {
            play = new GamePlay(saveFile); // Pass the SaveFile to GamePlay
        } else {
            System.err.println("Failed to load save data. Exiting game loop.");
            running = false;
            return;
        }

        // Start the game loop in a new thread
        Thread gameThread = new Thread(this::run); // Lambda for the run method
        gameThread.start();

        System.out.println("Game loop started on a separate thread.");
    }

    /**
     * Executes the game loop logic on a separate thread.
     * Handles updates to the game state at a consistent rate.
     */
    public void run() {
        long lastTime = System.nanoTime();
        double delta = 0.0;
        final double nsPerUpdate = 1_000_000_000.0 / (1000 / TARGET_TIME_BETWEEN_UPDATES);

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerUpdate;
            lastTime = now;

            while (delta >= 1) {
                update(); // Update game logic
                delta--;
            }
        }
    }

    /**
     * Updates the game logic by delegating to the {@code GamePlay} instance.
     */
    public void update() {
        if (play != null) {
            play.update();
        }
    }

    /**
     * Stops the game loop by setting the running status to {@code false}.
     */
    public void stop() {
        System.out.println("Stopping game loop...");
        running = false;
    }

    /**
     * Retrieves the {@code GameManager} instance associated with the game loop.
     * 
     * @return the {@code GameManager} instance
     */
    public GameManager getGameManager() {
        return this.gameManager;
    }
}
