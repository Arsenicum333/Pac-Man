package com.pacman.helpers;

public class CollisionDetector {
    private static final int COLLISION_OFFSET = 8;

    public static boolean collision(GameObject a, GameObject b) {
        return a.getX() < b.getX() + b.getWidth() &&
               a.getX() + a.getWidth() > b.getX() &&
               a.getY() < b.getY() + b.getHeight() &&
               a.getY() + a.getHeight() > b.getY();
    }

    public static boolean offsetCollision(GameObject a, GameObject b) {
        int offset = COLLISION_OFFSET;

        return a.getX() + offset < b.getX() + b.getWidth() - offset &&
               a.getX() + a.getWidth() - offset > b.getX() + offset &&
               a.getY() + offset < b.getY() + b.getHeight() - offset &&
               a.getY() + a.getHeight() - offset > b.getY() + offset;
    }
}
