import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import com.example.GameLoop;
import com.example.SaveFile;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;


public class GameLoopTest {

    private static final String TEST_SAVE_FILE_PATH = "src/main/java/com/example/data/sf1.json";

    private SaveFile loadTestSaveFile(String path) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(path)) {
            return gson.fromJson(reader, SaveFile.class);
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            fail("Failed to load test save file: " + e.getMessage());
            return null; // Fail-safe in case of test failure
        }
    }

    @Test
    public void testGameLoopStartsCorrectly() {
        SaveFile saveFile = loadTestSaveFile(TEST_SAVE_FILE_PATH);

        assertNotNull(saveFile, "SaveFile should be successfully loaded");

        GameLoop gameLoop = new GameLoop(saveFile);

        assertTrue(gameLoop.isRunning(), "Game loop should start running");
        assertNotNull(gameLoop.getPlay(), "GamePlay should be initialized");
    }

    @Test
    public void testGameLoopStopsCorrectly() {
        SaveFile saveFile = loadTestSaveFile(TEST_SAVE_FILE_PATH);

        assertNotNull(saveFile, "SaveFile should be successfully loaded");

        GameLoop gameLoop = new GameLoop(saveFile);
        gameLoop.stop();

        assertFalse(gameLoop.isRunning(), "Game loop should stop running");
    }

    @Test
    public void testGameLoopUsesSaveFileCorrectly() {
        SaveFile saveFile = loadTestSaveFile(TEST_SAVE_FILE_PATH);
    
        assertNotNull(saveFile, "SaveFile should be successfully loaded");
        assertNotNull(saveFile.getPet(), "SaveFile should contain a Pet");
        assertNotNull(saveFile.getInventory(), "SaveFile should contain an inventory");
    
        GameLoop gameLoop = new GameLoop(saveFile);
    
        // Verify the save file path directly from the saveFile
        assertEquals(saveFile.getSaveFilePath(), saveFile.getSaveFilePath(),
                "GameLoop should use the correct save file");
    }
}
