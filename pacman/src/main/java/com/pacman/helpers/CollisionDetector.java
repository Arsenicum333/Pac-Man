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
        return a.getX() + COLLISION_OFFSET < b.getX() + b.getWidth() - COLLISION_OFFSET &&
               a.getX() + a.getWidth() - COLLISION_OFFSET > b.getX() + COLLISION_OFFSET &&
               a.getY() + COLLISION_OFFSET < b.getY() + b.getHeight() - COLLISION_OFFSET &&
               a.getY() + a.getHeight() - COLLISION_OFFSET > b.getY() + COLLISION_OFFSET;
    }
}
