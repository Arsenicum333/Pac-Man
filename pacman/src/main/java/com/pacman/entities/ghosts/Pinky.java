package com.pacman.entities.ghosts;

import com.pacman.helpers.ImageLoader;

import java.awt.Image;

public class Pinky extends Ghost {
    public Pinky(Image image, int x, int y, int width, int height, int speed) {
        super(image, x, y, width, height, speed);
    }

    @Override
    public void moveBehaviour() {
        super.moveBehaviour();

        switch (direction) {
            case "LEFT" -> setImage(ImageLoader.getImage("PinkyLeft"));
            case "RIGHT" -> setImage(ImageLoader.getImage("PinkyRight"));
            case "UP" -> setImage(ImageLoader.getImage("PinkyUp"));
            case "DOWN" -> setImage(ImageLoader.getImage("PinkyDown"));
            default -> {}
        }
    }
}
