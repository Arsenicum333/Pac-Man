package com.pacman.entities.ghosts;

import com.pacman.helpers.ImageLoader;

import java.awt.Image;

public class Blinky extends Ghost {
    public Blinky(Image image, int x, int y, int width, int height, int speed) {
        super(image, x, y, width, height, speed);
    }

    @Override
    public void moveBehaviour() {
        super.moveBehaviour();

        switch (direction) {
            case "LEFT" -> setImage(ImageLoader.getImage("BlinkyLeft"));
            case "RIGHT" -> setImage(ImageLoader.getImage("BlinkyRight"));
            case "UP" -> setImage(ImageLoader.getImage("BlinkyUp"));
            case "DOWN" -> setImage(ImageLoader.getImage("BlinkyDown"));
            default -> {}
        }
    }
}
