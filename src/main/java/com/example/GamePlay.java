package com.example;

import java.util.List;

/**
 * Handles the gameplay logic, including interactions with the pet and inventory management.
 * This class is initialized with a {@code SaveFile} to retrieve the pet and inventory data.
 */
public class GamePlay {

    private List<String> inventory; // Inventory for gameplay (converted from SaveFile)
    private Pet pet; // Pet instance for gameplay
    public static int updateCount = 0; // Tracks updates

    /**
     * Constructs a {@code GamePlay} instance using data from the given {@code SaveFile}.
     *
     * @param saveFile the save file containing the initial game state
     */
    public GamePlay(SaveFile saveFile) {
        // Fetch the pet and inventory directly from SaveFile
        pet = saveFile.getPet();

        // Convert SaveFile's string inventory to Item objects if necessary
        inventory = saveFile.getInventory();
    }

    /**
     * Handles button clicks based on the provided action ID.
     *
     * @param actionId the ID of the action to perform
     *                 <ul>
     *                     <li>1: Feed the pet</li>
     *                     <li>2: Play with the pet</li>
     *                     <li>3: Put the pet to sleep</li>
     *                     <li>4: Give a gift to the pet</li>
     *                 </ul>
     */
    public void onButtonClick(int actionId) {
        switch (actionId) {
            case 1:
                feedPet();
                break;
            case 2:
                playWithPet();
                break;
            case 3:
                putPetToSleep();
                break;
            case 4:
                giveGiftToPet();
                break;
            default:
                System.out.println("Invalid action!");
        }
    }

    /**
     * Updates the game state, including pet statistics and mood.
     * The update count increments with each call, and every third update triggers
     * a stats update for the pet.
     */
    public void update() {
        updateCount += 1;
        System.out.println("Game state updated: " + updateCount);
        if (updateCount % 3 == 0) pet.updateStats(); // Update stats every 3 updates
        pet.updateMood();
    }

    /**
     * Adds an item to the inventory.
     *
     * @param item the item to add
     */
    public void addItemToInventory(Item item) {
        inventory.add(item.toString());
    }

    /**
     * Removes an item from the inventory.
     *
     * @param item the item to remove
     */
    public void removeItemFromInventory(Item item) {
        inventory.remove(item.toString());
    }

    /**
     * Feeds the pet using an item from the inventory.
     * Displays a message if no food is available.
     */
    private void feedPet() {
        if (!inventory.isEmpty()) {
            System.out.println("Fed the pet.");
        } else {
            System.out.println("No food available to feed the pet.");
        }
    }

    /**
     * Plays with the pet using an item from the inventory.
     * Displays a message if no toys are available.
     */
    private void playWithPet() {
        if (!inventory.isEmpty()) {
            System.out.println("Played with the pet.");
        } else {
            System.out.println("No toys available to play with the pet.");
        }
    }

    /**
     * Puts the pet to sleep using an item from the inventory.
     * Displays a message if no bedding is available.
     */
    private void putPetToSleep() {
        if (!inventory.isEmpty()) {
            System.out.println("Pet is now sleeping.");
        } else {
            System.out.println("No bedding available for the pet.");
        }
    }

    /**
     * Gives a gift to the pet using an item from the inventory.
     * Displays a message if no items are available to give.
     */
    private void giveGiftToPet() {
        if (!inventory.isEmpty()) {
            System.out.println("Gave a gift to the pet.");
        } else {
            System.out.println("No items available to give as a gift.");
        }
    }
}
