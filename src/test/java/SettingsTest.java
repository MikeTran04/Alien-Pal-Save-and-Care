import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.Settings;

public class SettingsTest {
    
    @Test
    public void testGetBGM() {
        Settings settings = new Settings(50, 0, false, false, null);
        int bgmVolume = settings.getBGM();
        assertEquals(50, bgmVolume, "The BGM volume should be 50");
    }

    @Test
    public void testSetBGM() {
        Settings settings = new Settings();
        settings.setBGM(70);
        int bgmVolume = settings.getBGM();
        assertEquals(70, bgmVolume, "The BGM volume should be 70");
    }

    @Test
    public void testGetSFX() {
        Settings settings = new Settings(0, 30, false, false, null);
        int sfxVolume = settings.getSFX();
        assertEquals(30, sfxVolume, "The SFX volume should be 30");
    }

    @Test
    public void testSetSFX() {
        Settings settings = new Settings();
        settings.setSFX(40);
        int sfxVolume = settings.getSFX();
        assertEquals(40, sfxVolume, "The SFX volume should be 40");
    }

    @Test
    public void testGetParentControl() {
        Settings settings = new Settings(0, 0, true, false, null);
        boolean parentControl = settings.getParentControl();
        assertTrue(parentControl, "Parent control should be enabled");
    }

    @Test
    public void testSetParentalControl() {
        Settings settings = new Settings();
        settings.setParentalControl();
        boolean parentControl = settings.getParentControl();
        assertTrue(parentControl, "Parent control should be enabled");
    }

    @Test
    public void testGetParentPassword() {
        Settings settings = new Settings();
        settings.setParentPassword("secure123");
        String password = settings.getParentPassword();
        assertEquals("secure123", password, "The parent password should be 'secure123'");
    }

    @Test
    public void testCheckParentPassword() {
        Settings settings = new Settings();
        settings.setParentPassword("secure123");
        assertTrue(settings.checkParentPassword("secure123"), "The password should match");
        assertFalse(settings.checkParentPassword("wrongpass"), "The password should not match");
    }

    @Test
    public void testGetTimeLimit() {
        Settings settings = new Settings(0, 0, false, false, "2:30");
        String timeLimit = settings.getTimeLimit();
        assertEquals("2:30", timeLimit, "The time limit should be '2:30'");
    }

    @Test
    public void testIsRevivePet() {
        Settings settings = new Settings(0, 0, false, true, null);
        boolean revivePet = settings.isRevivePet();
        assertTrue(revivePet, "Revive pet should be enabled");
    }
}

