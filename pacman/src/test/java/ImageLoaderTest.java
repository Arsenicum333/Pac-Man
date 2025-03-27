import com.pacman.ImageLoader;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.awt.Image;
import static org.junit.jupiter.api.Assertions.*;

class ImageLoaderTest {

    @BeforeAll
    static void setUp() {
        try {
            Class.forName("com.pacman.ImageLoader");
        } catch (ClassNotFoundException e) {
            fail("ImageLoader class not found");
        }
    }

    @Test
    void testGetImage_ValidKeys() {
        String[] keys = {"Wall", "Dot", "PowerPellet",
                         "PacManDown", "PacManLeft", "PacManRight", "PacManUp",
                         "BlinkyDown", "BlinkyLeft", "BlinkyRight", "BlinkyUp",
                         "PinkyDown", "PinkyLeft", "PinkyRight", "PinkyUp",
                         "InkyDown", "InkyLeft", "InkyRight", "InkyUp",
                         "ClydeDown", "ClydeLeft", "ClydeRight", "ClydeUp"};

        for (String key : keys) {
            Image img = ImageLoader.getImage(key);
            assertNotNull(img, "Image for key " + key + " should not be null");
        }
    }

    @Test
    void testGetImage_InvalidKey() {
        assertNull(ImageLoader.getImage("InvalidKey"), "Should return null for non-existent key");
    }
}
