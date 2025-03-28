package com.pacman.entity.ghost;

import com.pacman.ImageLoader;

import java.awt.Image;

public class Blinky extends Ghost {
    public Blinky(Image image, int x, int y, int width, int height, int speed) {
        super(image, x, y, width, height, speed);
    }

    @Override
    public void moveBehaviour() {
        super.moveBehaviour();

        if (direction.equals("LEFT"))
            setImage(ImageLoader.getImage("BlinkyLeft"));
        else if (direction.equals("RIGHT"))
            setImage(ImageLoader.getImage("BlinkyRight"));
        else if (direction.equals("UP"))
            setImage(ImageLoader.getImage("BlinkyUp"));
        else if (direction.equals("DOWN"))
            setImage(ImageLoader.getImage("BlinkyDown"));
    }
}
