import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Item;
import com.example.Pet;


class PetTest {
    private Pet pet;

    @BeforeEach
    public void setUp() {
        // Initialize the Pet object with a mock GameplayScene using dependency injection
        pet = new Pet("Buddy", "Dog", 50, 80, 60, 40, false, false, false, true, false, false) {
            @Override
            public void updateMood() {
                // Override updateMood to avoid calling null GameplayScene methods
                System.out.println("updateMood called for testing purposes.");
            }
        };
    }

    @Test
    public void testGetName() {
        assertEquals("Buddy", pet.getName(), "Pet name should be Buddy");
    }

    @Test
    public void testSetName() {
        pet.setName("Max");
        assertEquals("Max", pet.getName(), "Pet name should be Max");
    }

    @Test
    public void testGetType() {
        assertEquals("Dog", pet.getType(), "Pet type should be Dog");
    }

    @Test
    public void testSetType() {
        pet.setType("Cat");
        assertEquals("Cat", pet.getType(), "Pet type should be Cat");
    }

    @Test
    public void testGetHappiness() {
        assertEquals(50, pet.getHappiness(), "Pet happiness should be 50");
    }

    @Test
    public void testSetHappiness() {
        pet.setHappiness(75);
        assertEquals(75, pet.getHappiness(), "Pet happiness should be 75");
    }

    @Test
    public void testGetSleep() {
        assertEquals(60, pet.getSleep(), "Pet sleep should be 60");
    }

    @Test
    public void testSetSleep() {
        pet.setSleep(85);
        assertEquals(85, pet.getSleep(), "Pet sleep should be 85");
    }

    @Test
    public void testGetHunger() {
        assertEquals(40, pet.getHunger(), "Pet hunger should be 40");
    }

    @Test
    public void testSetHunger() {
        pet.setHunger(55);
        assertEquals(55, pet.getHunger(), "Pet hunger should be 55");
    }

    @Test
    public void testGetHealth() {
        assertEquals(80, pet.getHealth(), "Pet health should be 80");
    }

    @Test
    public void testSetHealth() {
        pet.setHealth(90);
        assertEquals(90, pet.getHealth(), "Pet health should be 90");
    }

    @Test
    public void testGetIsStarving() {
        assertFalse(pet.getIsStarving(), "Pet should not be starving");
    }

    @Test
    public void testSetIsStarving() {
        pet.setIsStarving(true);
        assertTrue(pet.getIsStarving(), "Pet should be starving");
    }

    @Test
    public void testGetIsSleeping() {
        assertFalse(pet.getIsSleeping(), "Pet should not be sleeping");
    }

    @Test
    public void testSetIsSleeping() {
        pet.setIsSleeping(true);
        assertTrue(pet.getIsSleeping(), "Pet should be sleeping");
    }

    @Test
    public void testGetIsDepressed() {
        assertFalse(pet.getIsDepressed(), "Pet should not be depressed");
    }

    @Test
    public void testSetIsDepressed() {
        pet.setIsDepressed(true);
        assertTrue(pet.getIsDepressed(), "Pet should be depressed");
    }

    @Test
    public void testGetIsObey() {
        assertTrue(pet.getIsObey(), "Pet should obey");
    }

    @Test
    public void testSetIsObey() {
        pet.setIsObey(false);
        assertFalse(pet.getIsObey(), "Pet should not obey");
    }

    @Test
    public void testGetIsSick() {
        assertFalse(pet.getIsSick(), "Pet should not be sick");
    }

    @Test
    public void testGetIsDead() {
        assertFalse(pet.getIsDead(), "Pet should not be dead");
    }

    @Test
    public void testSetIsDead() {
        pet.setIsDead(true);
        assertTrue(pet.getIsDead(), "Pet should be dead");
    }

    @Test
    public void testFeedPet() {
        Item food = new Item(1, "Food", "A tasty meal for your pet.", "food", 10, 20, 30, 10);
        pet.setIsObey(true);
        pet.feed(food);

        assertEquals(70, pet.getHappiness(), "Happiness should increase by food modifier");
        assertEquals(70, pet.getHunger(), "Hunger should increase by food modifier");
        assertEquals(70, pet.getSleep(), "Sleep should increase by food modifier");
    }

    @Test
    public void testPlayWithPet() {
        Item toy = new Item(3, "Toy", "A fun toy for your pet.", "toy", 20, 15, 5, 10);
        pet.play(toy);

        assertEquals(65, pet.getHappiness(), "Happiness should increase by toy modifier");
        assertEquals(35, pet.getHunger(), "Hunger should decrease by toy modifier");
    }

    @Test
    public void testUpdateStats() {
        pet.setHunger(50);
        pet.setSleep(50);
        pet.setHappiness(50);

        pet.updateStats();

        assertEquals(45, pet.getHunger(), "Hunger should decrease by 5");
        assertEquals(45, pet.getSleep(), "Sleep should decrease by 5");
        assertEquals(45, pet.getHappiness(), "Happiness should decrease by 5");
    }
}
