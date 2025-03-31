package com.pacman.entities.ghosts;

import com.pacman.helpers.ImageLoader;

import java.awt.Image;

public class Clyde extends Ghost {
    public Clyde(Image image, int x, int y, int width, int height, int speed) {
        super(image, x, y, width, height, speed);
    }

    @Override
    public void moveBehaviour() {
        super.moveBehaviour();

        if (direction.equals("LEFT"))
            setImage(ImageLoader.getImage("ClydeLeft"));
        else if (direction.equals("RIGHT"))
            setImage(ImageLoader.getImage("ClydeRight"));
        else if (direction.equals("UP"))
            setImage(ImageLoader.getImage("ClydeUp"));
        else if (direction.equals("DOWN"))
            setImage(ImageLoader.getImage("ClydeDown"));
    }
}
