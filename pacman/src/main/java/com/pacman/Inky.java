package com.pacman;

import java.awt.Image;

public class Inky extends Ghost {
    public Inky(Image image, int x, int y, int width, int height, int speed) {
        super(image, x, y, width, height, speed);
    }

    @Override
    public void moveBehaviour() {
        super.moveBehaviour();

        if (direction.equals("LEFT"))
            setImage(ImageLoader.getImage("InkyLeft"));
        else if (direction.equals("RIGHT"))
            setImage(ImageLoader.getImage("InkyRight"));
        else if (direction.equals("UP"))
            setImage(ImageLoader.getImage("InkyUp"));
        else if (direction.equals("DOWN"))
            setImage(ImageLoader.getImage("InkyDown"));
    }
}
