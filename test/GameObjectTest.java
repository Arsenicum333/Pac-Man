import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameObjectTest {
    @Test
    public void testConstructorAndGetters() {
        GameObject object = new GameObject(null, 10, 20, 30, 40);
        assertEquals(10, object.getX());
        assertEquals(20, object.getY());
        assertEquals(30, object.getWidth());
        assertEquals(40, object.getHeight());
    }

    @Test
    public void testSetters() {
        GameObject object = new GameObject(null, 0, 0, 0, 0);
        object.setX(100);
        object.setY(200);
        object.setWidth(50);
        object.setHeight(60);

        assertEquals(100, object.getX());
        assertEquals(200, object.getY());
        assertEquals(50, object.getWidth());
        assertEquals(60, object.getHeight());
    }
}
