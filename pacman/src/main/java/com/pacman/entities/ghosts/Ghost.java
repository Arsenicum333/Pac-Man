package com.pacman.entities.ghosts;

import com.pacman.entities.Entity;

import java.awt.Image;
import java.util.Random;

public class Ghost extends Entity {
    private String[] directions = {"LEFT", "RIGHT", "UP", "DOWN"};
    private String newDirection = "";
    private Random random = new Random();

    public Ghost(Image image, int x, int y, int width, int height, int speed) {
        super(image, x, y, width, height, speed);
        direction = "UP";
    }

    public void moveBehaviour() {
        if (!canMove(direction)) {
            do {
                newDirection = directions[random.nextInt(directions.length)];
            } while (!canMove(newDirection));
                direction = newDirection;
        }

        super.move();
    }
}
