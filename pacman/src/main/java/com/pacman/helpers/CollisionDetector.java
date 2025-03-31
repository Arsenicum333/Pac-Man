package com.pacman.helpers;

public class CollisionDetector {
    private static final int COLLISION_OFFSET = 8;

    public static boolean collision(GameObject a, GameObject b) {
        return a.getX() < b.getX() + b.getWidth() &&
               a.getX() + a.getWidth() > b.getX() &&
               a.getY() < b.getY() + b.getHeight() &&
               a.getY() + a.getHeight() > b.getY();
    }

    public static boolean itemCollision(GameObject entity, GameObject item) {
        int offset = COLLISION_OFFSET;

        return entity.getX() + offset < item.getX() + item.getWidth() - offset &&
               entity.getX() + entity.getWidth() - offset > item.getX() + offset &&
               entity.getY() + offset < item.getY() + item.getHeight() - offset &&
               entity.getY() + entity.getHeight() - offset > item.getY() + offset;
    }
}
