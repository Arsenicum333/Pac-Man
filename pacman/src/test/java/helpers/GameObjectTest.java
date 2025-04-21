package helpers;

import pacman.helpers.loaders.ImageLoader;
import pacman.objects.GameObject;

import org.junit.jupiter.api.Test;
import javax.swing.ImageIcon;
import java.awt.Image;
import static org.junit.jupiter.api.Assertions.*;

public class GameObjectTest {
    @Test
    public void testConstructorAndGetters() {
        GameObject object = new GameObject(null, 10, 20, 30, 40);
        Image testImage = new ImageIcon().getImage();
        GameObject objectWithImage = new GameObject(testImage, 10, 20, 30, 40);

        assertNull(object.getImage());
        assertEquals(testImage, objectWithImage.getImage());
        assertEquals(10, object.getX());
        assertEquals(20, object.getY());
        assertEquals(30, object.getWidth());
        assertEquals(40, object.getHeight());
        assertEquals(10, object.getStartX());
        assertEquals(20, object.getStartY());
    }

    @Test
    public void testGetImages() {
        System.out.println("Wall: " + ImageLoader.getImage("Wall"));
        assertNotNull(ImageLoader.getImage("Wall"));

        System.out.println("Dot: " + ImageLoader.getImage("Dot"));
        assertNotNull(ImageLoader.getImage("Dot"));

        System.out.println("PowerPellet: " + ImageLoader.getImage("PowerPellet"));
        assertNotNull(ImageLoader.getImage("PowerPellet"));

        System.out.println("PacManUp: " + ImageLoader.getImage("PacManUp"));
        assertNotNull(ImageLoader.getImage("PacManUp"));

        System.out.println("BlinkyDown: " + ImageLoader.getImage("BlinkyDown"));
        assertNotNull(ImageLoader.getImage("BlinkyDown"));

        System.out.println("PinkyLeft: " + ImageLoader.getImage("PinkyLeft"));
        assertNotNull(ImageLoader.getImage("PinkyLeft"));

        System.out.println("InkyRight: " + ImageLoader.getImage("InkyRight"));
        assertNotNull(ImageLoader.getImage("InkyRight"));

        System.out.println("ClydeUp: " + ImageLoader.getImage("ClydeUp"));
        assertNotNull(ImageLoader.getImage("ClydeUp"));

        System.out.println("NonExistentImage: " + ImageLoader.getImage("NonExistentImage"));
        assertNull(ImageLoader.getImage("NonExistentImage"));
    }

    @Test
    public void testSetters() {
        GameObject object = new GameObject(null, 0, 0, 0, 0);
        Image testImage = new ImageIcon().getImage();

        object.setImage(null);
        object.setImage(testImage);
        object.setX(100);
        object.setY(200);
        object.setWidth(50);
        object.setHeight(60);
        object.setStartX(15);
        object.setStartY(25);

        assertNull(object.getImage());
        assertEquals(testImage, object.getImage());
        assertEquals(100, object.getX());
        assertEquals(200, object.getY());
        assertEquals(50, object.getWidth());
        assertEquals(60, object.getHeight());
        assertEquals(15, object.getStartX());
        assertEquals(25, object.getStartY());
    }
}
