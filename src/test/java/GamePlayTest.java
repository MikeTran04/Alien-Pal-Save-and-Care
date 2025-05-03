import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.GamePlay;
import com.example.Pet;
import com.example.SaveFile;

class GamePlayTest {
    private GamePlay gamePlay;
    private SaveFile mockSaveFile;
    private Pet mockPet;

    @BeforeEach
    public void setUp() {
        // Create a mock SaveFile and Pet
        mockSaveFile = new SaveFile();
        mockPet = new Pet("Buddy", "Dog", 50, 80, 60, 40, false, false, false, true, false, false);
        mockSaveFile.setPet(mockPet);
        mockSaveFile.setInventory(new ArrayList<>(Arrays.asList("Food", "Toy", "Bed")));

        // Initialize GamePlay instance
        gamePlay = new GamePlay(mockSaveFile);
    }

    @Test
    public void testOnButtonClickFeed() {
        gamePlay.onButtonClick(1);
        // No direct assertions since feedPet() prints a message
        // This test ensures the method runs without exceptions
    }

    @Test
    public void testOnButtonClickPlay() {
        gamePlay.onButtonClick(2);
        // No direct assertions since playWithPet() prints a message
        // This test ensures the method runs without exceptions
    }

    @Test
    public void testOnButtonClickSleep() {
        gamePlay.onButtonClick(3);
        // No direct assertions since putPetToSleep() prints a message
        // This test ensures the method runs without exceptions
    }

    @Test
    public void testOnButtonClickGiveGift() {
        gamePlay.onButtonClick(4);
        // No direct assertions since giveGiftToPet() prints a message
        // This test ensures the method runs without exceptions
    }
}
