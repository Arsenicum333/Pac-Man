package com.pacman;

import java.awt.Image;
import java.util.Random;

public abstract class Ghost extends Entity {
    private String[] directions = {"LEFT", "RIGHT", "UP", "DOWN"};
    private String newDirection = "";
    private Random random = new Random();

    public Ghost(Image image, int x, int y, int width, int height) {
        super(image, x, y, width, height);
    }

    // public void moveBehaviour();
}
