package com.pacman.entity;

import com.pacman.ImageLoader;

import java.awt.Image;

public class PacMan extends Entity {
    private String newDirection = "";

    public PacMan(Image image, int x, int y, int width, int height, int speed) {
        super(image, x, y, width, height, speed);
    }

    @Override
    public void move() {
        if (!newDirection.isEmpty() && canMove (newDirection))
            direction = newDirection;

        if (canMove(direction))
            super.move();

        if (direction.equals("LEFT"))
            setImage(ImageLoader.getImage("PacManLeft"));
        else if (direction.equals("RIGHT"))
            setImage(ImageLoader.getImage("PacManRight"));
        else if (direction.equals("UP"))
            setImage(ImageLoader.getImage("PacManUp"));
        else if (direction.equals("DOWN"))
            setImage(ImageLoader.getImage("PacManDown"));
    }

    public String getNewDirection() {return newDirection;}

    public void setNewDirection(String newDirection) {this.newDirection = newDirection;}
}
