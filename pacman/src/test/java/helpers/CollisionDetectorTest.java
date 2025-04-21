package helpers;

import pacman.helpers.CollisionDetector;
import pacman.objects.GameObject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CollisionDetectorTest {
    @Test
    public void testCollisionWhenObjectsOverlap() {
        GameObject a = new GameObject(null, 10, 10, 20, 20);
        GameObject b = new GameObject(null, 15, 15, 20, 20);

        assertTrue(CollisionDetector.collision(a, b));
    }

    @Test
    public void testCollisionWhenXTooFarRight() {
        GameObject a = new GameObject(null, 40, 10, 10, 10);
        GameObject b = new GameObject(null, 10, 10, 20, 20);

        assertFalse(CollisionDetector.collision(a, b));
    }

    @Test
    public void testCollisionWhenXTooFarLeft() {
        GameObject a = new GameObject(null, 10, 10, 10, 10);
        GameObject b = new GameObject(null, 30, 10, 20, 20);

        assertFalse(CollisionDetector.collision(a, b));
    }

    @Test
    public void testCollisionWhenYTooFarDown() {
        GameObject a = new GameObject(null, 10, 40, 10, 10);
        GameObject b = new GameObject(null, 10, 10, 20, 20);

        assertFalse(CollisionDetector.collision(a, b));
    }

    @Test
    public void testCollisionWhenYTooFarUp() {
        GameObject a = new GameObject(null, 10, 10, 10, 10);
        GameObject b = new GameObject(null, 10, 30, 20, 20);

        assertFalse(CollisionDetector.collision(a, b));
    }

    @Test
    public void testItemCollisionWhenObjectsOverlap() {
        GameObject entity = new GameObject(null, 10, 10, 20, 20);
        GameObject item = new GameObject(null, 12, 12, 20, 20);

        assertTrue(CollisionDetector.offsetCollision(entity, item));
    }

    @Test
    public void testItemCollisionWhenXTooFarRight() {
        GameObject entity = new GameObject(null, 32, 10, 10, 10);
        GameObject item = new GameObject(null, 10, 10, 20, 20);

        assertFalse(CollisionDetector.offsetCollision(entity, item));
    }

    @Test
    public void testItemCollisionWhenXTooFarLeft() {
        GameObject entity = new GameObject(null, 2, 10, 10, 10);
        GameObject item = new GameObject(null, 20, 10, 20, 20);

        assertFalse(CollisionDetector.offsetCollision(entity, item));
    }

    @Test
    public void testItemCollisionWhenYTooFarDown() {
        GameObject entity = new GameObject(null, 10, 32, 10, 10);
        GameObject item = new GameObject(null, 10, 10, 20, 20);

        assertFalse(CollisionDetector.offsetCollision(entity, item));
    }

    @Test
    public void testItemCollisionWhenYTooFarUp() {
        GameObject entity = new GameObject(null, 10, 2, 10, 10);
        GameObject item = new GameObject(null, 10, 20, 20, 20);

        assertFalse(CollisionDetector.offsetCollision(entity, item));
    }
}
