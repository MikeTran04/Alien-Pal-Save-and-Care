package com.example;

/**
 * The Pet class represents a virtual pet with various attributes and behaviours.
 * It tracks the pet's health, sleep, hunger, and happiness levels, along with states 
 * like whether the pet is sick, depressed, or starving. The class includes methods for 
 * interacting with the pet, such as feeding, playing, exercising, and sleeping.
 */
public class Pet { 

  /**
   * The name of the pet.
   */
  private String name;

  /**
   * The type of the pet (e.g., dog, cat).
   */
  private String type;

  /**
   * Indicates if the pet is depressed.
   */
  private boolean isDepressed;

  /**
   * Indicates if the pet is sleeping.
   */
  private boolean isSleeping;

  /**
   * Indicates if the pet is starving.
   */
  private boolean isStarving;

  /**
   * Indicates if the pet is sick.
   */
  private boolean isSick;

  /**
   * Indicates if the pet is obedient.
   */
  private boolean isObey;

  /**
   * Indicates if the pet is dead.
   */
  private boolean isDead;

  /**
   * Represents the health level of the pet.
   */
  private int health;

  /**
   * Represents the sleep level of the pet.
   */
  private int sleep;

  /**
   * Represents the hunger level of the pet.
   */
  private int hunger;

  /**
   * Represents the happiness level of the pet.
   */
  private int happiness;

  /**
   * Reference to the game's main loop for managing runtime behaviours.
   */
  private GameLoop gameLoop;

  /**
   * High threshold for health, sleep, hunger, and happiness levels.
   * Excluded from serialization.
   */
  private transient final int HIGH = 100;

  /**
   * Mid threshold for health, sleep, hunger, and happiness levels.
   * Excluded from serialization.
   */
  private transient final int MID = 50;

  /**
   * Low threshold for health, sleep, hunger, and happiness levels.
   * Excluded from serialization.
   */
  private transient final int LOW = 25;

  /**
   * Reference to the gameplay scene for updating UI elements.
   * Excluded from serialization.
   */
  private transient GameplayScene gameplayScene;

  /**
   * Reference to the main gameplay logic.
   * Excluded from serialization.
   */
  private transient GamePlay gamePlay;
  
  
    /**
     * Default constructor for Gson (required for deserialization).
     */
    public Pet() {
      // Load in static variable for gameplay scene (not best method, but easiest)
      this.gameplayScene = App.getGameplayScene();
  }

  /**
   * Parameterized constructor for manual initialization.
   *
   * @param name        the name of the pet
   * @param type        the type of the pet
   * @param happiness   the happiness level of the pet
   * @param health      the health level of the pet
   * @param sleep       the sleep level of the pet
   * @param hunger      the hunger level of the pet
   * @param isSick      whether the pet is sick
   * @param isDepressed whether the pet is depressed
   * @param isDead      whether the pet is dead
   * @param isObey      whether the pet is obedient
   * @param isStarving  whether the pet is starving
   * @param isSleeping  whether the pet is sleeping
   */
  public Pet(String name, String type, int happiness, int health, int sleep, int hunger, boolean isSick, 
             boolean isDepressed, boolean isDead, boolean isObey, boolean isStarving, boolean isSleeping) {
      this.name = name;
      this.type = type;
      this.health = health;
      this.happiness = happiness;
      this.sleep = sleep;
      this.hunger = hunger;
      this.isSick = isSick;
      this.isDepressed = isDepressed;
      this.isDead = isDead;
      this.isObey = isObey;
      this.isSleeping = isSleeping;
      this.isStarving = isStarving;

      // Load in static variable for gameplay scene (not best method, but easiest)
      this.gameplayScene = App.getGameplayScene();
  }

    /**
     * Gets the name of the pet.
     *
     * @return the name of the pet
     */
    public String getName() {
      return name;
  }

  /**
   * Sets the name of the pet.
   *
   * @param name the name to set
   */
  public void setName(String name) {
      this.name = name;
  }

  /**
   * Gets the type of the pet.
   *
   * @return the type of the pet
   */
  public String getType() {
      return type;
  }

  /**
   * Sets the type of the pet.
   *
   * @param type the type to set
   */
  public void setType(String type) {
      this.type = type;
  }

  /**
   * Gets the happiness level of the pet.
   *
   * @return the happiness level of the pet
   */
  public int getHappiness() {
      return happiness;
  }

  /**
   * Sets the happiness level of the pet.
   *
   * @param happiness the happiness level to set
   */
  public void setHappiness(int happiness) {
      this.happiness = happiness;
  }

  /**
   * Gets the sleep level of the pet.
   *
   * @return the sleep level of the pet
   */
  public int getSleep() {
      return sleep;
  }

  /**
   * Sets the sleep level of the pet.
   *
   * @param sleep the sleep level to set
   */
  public void setSleep(int sleep) {
      this.sleep = sleep;
  }

  /**
   * Gets the hunger level of the pet.
   *
   * @return the hunger level of the pet
   */
  public int getHunger() {
      return hunger;
  }

  /**
   * Sets the hunger level of the pet.
   *
   * @param hunger the hunger level to set
   */
  public void setHunger(int hunger) {
      this.hunger = hunger;
  }

  /**
   * Gets the health level of the pet.
   *
   * @return the health level of the pet
   */
  public int getHealth() {
      return health;
  }

  /**
   * Sets the health level of the pet.
   *
   * @param health the health level to set
   */
  public void setHealth(int health) {
      this.health = health;
  }

  /**
   * Indicates whether the pet is starving.
   *
   * @return true if the pet is starving, false otherwise
   */
  public boolean getIsStarving() {
      return isStarving;
  }

  /**
   * Sets the starving state of the pet.
   *
   * @param isStarving whether the pet is starving
   */
  public void setIsStarving(boolean isStarving) {
      this.isStarving = isStarving;
  }

  /**
   * Indicates if the pet is sleeping.
   *
   * @return true if the pet is sleeping, false otherwise
   */
  public boolean getIsSleeping() {
    return isSleeping;
  }

  /**
  * Sets the sleeping state of the pet.
  *
  * @param isSleeping whether the pet is sleeping
  */
  public void setIsSleeping(boolean isSleeping) {
    this.isSleeping = isSleeping;
  }

  /**
  * Indicates if the pet is depressed.
  *
  * @return true if the pet is depressed, false otherwise
  */
  public boolean getIsDepressed() {
    return isDepressed;
  }

  /**
  * Sets the depressed state of the pet.
  *
  * @param isDepressed whether the pet is depressed
  */
  public void setIsDepressed(boolean isDepressed) {
    this.isDepressed = isDepressed;
  }

  /**
  * Indicates if the pet is obedient.
  *
  * @return true if the pet is obedient, false otherwise
  */
  public boolean getIsObey() {
    return isObey;
  }

  /**
  * Sets the obedient state of the pet.
  *
  * @param isObey whether the pet is obedient
  */
  public void setIsObey(boolean isObey) {
    this.isObey = isObey;
  }

  /**
  * Indicates if the pet is sick.
  *
  * @return true if the pet is sick, false otherwise
  */
  public boolean getIsSick() {
    return isSick;
  }

  /**
  * Sets the sick state of the pet. If the pet is sick, a vet button is shown in the UI.
  *
  * @param isSick whether the pet is sick
  */
  public void setIsSick(boolean isSick) {
    this.isSick = isSick;
    if (this.isSick) {
        gameplayScene.showVetButton();
    }
  }

  /**
  * Indicates if the pet is dead.
  *
  * @return true if the pet is dead, false otherwise
  */
  public boolean getIsDead() {
    return isDead;
  }

  /**
  * Sets the dead state of the pet.
  *
  * @param isDead whether the pet is dead
  */
  public void setIsDead(boolean isDead) {
    this.isDead = isDead;
    // Trigger game over logic
  }

  /**
  * Feeds the pet using the specified item.
  *
  * @param item the item to use for feeding
  */
  public void feed(Item item) {
    if (item.getType().equals("food") && this.isObey) {
        this.happiness = Math.min(this.happiness + item.getHappinessMod(), HIGH);
        this.sleep = Math.min(this.sleep + item.getSleepMod(), HIGH);
        this.hunger = Math.min(this.hunger + item.getHungerMod(), HIGH);
        updateMood();
    }
    if (!this.isObey) {
        warningMsg();
    }
  }

  /**
  * Allows the pet to play using the specified item.
  *
  * @param item the item to use for playing
  */
  public void play(Item item) {
    if (item.getType().equals("toy")) {
        this.happiness = Math.min(this.happiness + item.getHappinessMod(), HIGH);
        this.sleep = Math.max(this.sleep - item.getSleepMod(), 0);
        this.hunger = Math.max(this.hunger - item.getHungerMod(), 0);
        updateMood();
    }
  }

  /**
  * Makes the pet sleep using the specified item. Sleeping removes the sick debuff.
  *
  * @param item the item to use for sleeping
  */
  public void sleep(Item item) {
    if (item.getType().equals("bed") && this.isObey) {
        sleepMsg();
        if (GamePlay.updateCount % 5 == 0) {
            this.happiness = Math.min(this.happiness + item.getHappinessMod(), HIGH);
            this.sleep = Math.min(this.sleep + item.getSleepMod(), HIGH);
            this.hunger = Math.min(this.hunger + item.getHungerMod(), HIGH);
        }
        this.isSick = false;
        updateMood();
    }
    if (!this.isObey) {
        warningMsg();
    }
  }

  /**
  * Sends the pet to the vet to heal. Increases health by 30.
  */
  public void goToVet() {
    this.isSick = false;
    this.health = Math.min(this.health + 30, HIGH);
  }

  /**
  * Revives the pet if the game settings allow revival. Resets all major stats.
  */
  public void revivePet() {
    if (App.getSettings().isRevivePet() && getIsDead()) {
        health = HIGH;
        happiness = HIGH - LOW;
        sleep = HIGH - LOW;
        hunger = HIGH - LOW;
        isSick = false;
        isDepressed = false;
        isDead = false;
        isObey = true;
        isSleeping = false;
        isStarving = false;
    }
  }

  /**
  * Exercises the pet to improve its health. Reduces sleep and hunger.
  */
  public void exercise() {
    if (this.isObey) {
        this.health = Math.min(this.health + 15, HIGH);
        this.sleep = Math.max(this.sleep - 10, 0);
        this.hunger = Math.max(this.hunger - 10, 0);
        updateMood();
    } else {
        warningMsg();
    }
  }

  /**
  * Updates the pet's stats over time. Sleep decreases faster if the pet is sick.
  */
  public void updateStats() {
    hunger -= 5;
    sleep -= isSick ? 10 : 5;
    happiness -= 5;
    System.out.println("hunger: " + hunger);
    System.out.println("sleep: " + sleep);
    System.out.println("happiness: " + happiness);
    updateMood();
  }

  /**
  * Updates the mood of the pet based on its current stats. Changes states like
  * depressed, starving, or sick.
  */
  public void updateMood() {
    if (this.happiness <= LOW) {
        this.isDepressed = true;
        this.isObey = false;
        depressingState();
    } else {
        if (this.happiness >= MID) this.isObey = true;
        this.isDepressed = false;
    }

    if (this.hunger <= LOW) {
        this.isStarving = true;
        starvingState();
    } else this.isStarving = false;

    if (this.sleep == 0) {
        this.isSleeping = true;
        sleepingState();
    } else this.isSleeping = false;

    if (this.health < MID && this.sleep < MID && this.hunger < MID) {
        this.isSick = true;
    }

    gameplayScene.updateHealth(this.health);
    gameplayScene.updateHunger(this.hunger);
    gameplayScene.updateHappiness(this.happiness);
    gameplayScene.updateSleep(this.sleep);
  }

  /**
  * Handles the state when the pet is depressed. If happiness reaches 0, ends the game.
  */
  public void depressingState() {
    if (happiness == 0) gameLoop.setRunning(false);
  }

  /**
  * Handles the state when the pet is starving. Health decreases over time.
  */
  public void starvingState() {
    while (this.hunger == 0) {
        if (GamePlay.updateCount % 5 == 0) this.health -= 1;
        if (this.health == 0) {
            deadMsg();
        }
    }
  }

  /**
  * Handles the state when the pet is sleeping. Increases sleep over time and removes sick debuff.
  */
  public void sleepingState() {
    this.health -= LOW;
    if (this.health <= 0) {
        deadMsg();
    }
    sleepMsg();
    while (this.sleep < 20) {
        if (GamePlay.updateCount % 2 == 0) this.sleep += 2;
    }
    this.isSick = false;
    updateMood();
  }

  /**
  * Handles the state when the pet is sick. Decreases health, hunger, and happiness.
  */
  public void sickState() {
    this.health -= 10;
    this.hunger -= 10;
    this.happiness -= 10;
    warningMsg();
  }

  /**
  * Sets the game loop for managing runtime behaviours.
  *
  * @param gameLoop the game loop to set
  */
  public void setGameLoop(GameLoop gameLoop) {
    this.gameLoop = gameLoop;
  }

  /**
  * Displays a message indicating the pet is dead and ends the game.
  */
  public void deadMsg() {
    this.isDead = true;
    System.out.println("Your pet is DEAD");
    gameLoop.setRunning(false);
  }

  /**
  * Displays a message indicating the pet is sleeping.
  */
  public void sleepMsg() {
    System.out.println("Your pet is SLEEPING. DO NOT DISTURB!!! Did you know: a good sleep can make you feel better?");
  }

  /**
  * Displays warning messages based on the pet's current state (e.g., depressed or sick).
  */
  public void warningMsg() {
    if (this.isDepressed)
        System.out.println("Warning msg: your pet is depressed, pls cure it by increasing happiness before it's too late...");
    else if (!this.isDepressed && !this.isObey)
        System.out.println("Warning msg: your pet is still low on happiness after depression");

    if (this.isSick)
        System.out.println("Oh no.. Your pet is sick. Decrease in sleepiness will now x2. Good luck!");
  }
}