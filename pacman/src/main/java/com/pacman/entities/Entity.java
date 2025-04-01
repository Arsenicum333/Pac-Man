package com.pacman.entities;

import com.pacman.Maze;
import com.pacman.helpers.GameObject;
import static com.pacman.helpers.CollisionDetector.*;

import java.awt.Image;

public abstract class Entity extends GameObject {
    private int speed;
    protected String direction = "";

    public Entity(Image image, int x, int y, int width, int height, int speed) {
        super(image, x, y, width, height);
        this.speed = speed;
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

        if (getX() > 608)
            setX(-32);
        if (getX() < -32)
            setX(608);
    }

    public boolean canMove(String direction) {
        int newX = getX();
        int newY = getY();

        if (direction.equals("LEFT"))
            newX -= speed;
        else if (direction.equals("RIGHT"))
            newX += speed;
        else if (direction.equals("UP"))
            newY -= speed;
        else if (direction.equals("DOWN"))
            newY += speed;

        for (GameObject wall : Maze.getInstance().getWalls()) {
            if (collision(new GameObject(null, newX, newY, getWidth(), getHeight()), wall)) {
                return false;
            }
        }

        return true;
    }

    public int getSpeed() {return speed;}
    public String getDirection() {return direction;}

    public void setSpeed(int speed) {this.speed = speed;}
    public void setDirection(String direction) {this.direction = direction;}
}
