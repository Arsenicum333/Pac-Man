package pacman.helpers;

import pacman.interfaces.Positionable;

public class CollisionDetector {
    private static final int COLLISION_OFFSET = 8;

    public static boolean collision(Positionable a, Positionable b) {
        return a.getX() < b.getX() + b.getWidth() &&
               a.getX() + a.getWidth() > b.getX() &&
               a.getY() < b.getY() + b.getHeight() &&
               a.getY() + a.getHeight() > b.getY();
    }

    public static boolean offsetCollision(Positionable a, Positionable b) {
        return a.getX() + COLLISION_OFFSET < b.getX() + b.getWidth() - COLLISION_OFFSET &&
               a.getX() + a.getWidth() - COLLISION_OFFSET > b.getX() + COLLISION_OFFSET &&
               a.getY() + COLLISION_OFFSET < b.getY() + b.getHeight() - COLLISION_OFFSET &&
               a.getY() + a.getHeight() - COLLISION_OFFSET > b.getY() + COLLISION_OFFSET;
    }
}
