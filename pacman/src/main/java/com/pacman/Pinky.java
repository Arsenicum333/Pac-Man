package com.pacman;

import java.awt.Image;

public class Pinky extends Ghost {
    public Pinky(Image image, int x, int y, int width, int height, int speed) {
        super(image, x, y, width, height, speed);
    }

    @Override
    public void moveBehaviour() {
        super.moveBehaviour();

        if (direction.equals("LEFT"))
            setImage(ImageLoader.getImage("PinkyLeft"));
        else if (direction.equals("RIGHT"))
            setImage(ImageLoader.getImage("PinkyRight"));
        else if (direction.equals("UP"))
            setImage(ImageLoader.getImage("PinkyUp"));
        else if (direction.equals("DOWN"))
            setImage(ImageLoader.getImage("PinkyDown"));
    }
}
