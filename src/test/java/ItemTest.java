import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.example.Item;

public class ItemTest {

    @Test
    public void testItemConstructorAndGetters() {
        // Arrange
        Item item = new Item(1, "Magic Potion", "Increases happiness", "potion", 50, 20, 0, 10);

        // Act & Assert
        assertEquals(1, item.getId(), "Item ID should be 1");
        assertEquals("Magic Potion", item.getName(), "Item name should be 'Magic Potion'");
        assertEquals("Increases happiness", item.getDescription(), "Item description should be 'Increases happiness'");
        assertEquals("potion", item.getType(), "Item type should be 'potion'");
        assertEquals(50, item.getPrice(), "Item price should be 50");
        assertEquals(20, item.getHappinessMod(), "Item happiness modifier should be 20");
        assertEquals(0, item.getHungerMod(), "Item hunger modifier should be 0");
        assertEquals(10, item.getSleepMod(), "Item sleep modifier should be 10");
    }
}
