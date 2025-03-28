package com.pacman;

import java.awt.Image;

public abstract class Entity extends GameObject {
    private int speed = Maze.getTileSize() / 8;
    protected String direction = "";

    public Entity(Image image, int x, int y, int width, int height) {
        super(image, x, y, width, height);
    }

    public void move() {
        if (direction.equals("LEFT"))
            setX(getX() - speed);
        else if (direction.equals("RIGHT"))
            setX(getX() + speed);
        else if (direction.equals("UP"))
            setY(getY() - speed);
        else if (direction.equals("DOWN"))
            setY(getY() + speed);
    }

    public boolean canMove(String direction) {
        int newX = getX();
        int newY = getY();

        if (direction.equals("LEFT"))
            newX -= getSpeed();
        else if (direction.equals("RIGHT"))
            newX += getSpeed();
        else if (direction.equals("UP"))
            newY -= getSpeed();
        else if (direction.equals("DOWN"))
            newY += getSpeed();

        for (GameObject wall : Maze.getInstance().walls) {
            if (Maze.getInstance().checkCollision(new GameObject(null, newX, newY, getWidth(), getHeight()), wall)) {
                return false;
            }
        }

        return true;
    }

    public int getSpeed() {return speed;}
    public String getDirection() {return direction;}

    public void setDirection(String direction) {this.direction = direction;}
}
