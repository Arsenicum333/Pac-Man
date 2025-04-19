package pacman.entities;

import pacman.Maze;
import pacman.helpers.GameObject;
import pacman.interfaces.Movable;
import static pacman.helpers.CollisionDetector.*;

import java.awt.Image;

public abstract class Entity extends GameObject implements Movable {
    private int speed;
    protected String direction = "";

    public Entity(Image image, int x, int y, int width, int height, int speed) {
        super(image, x, y, width, height);
        this.speed = speed;
    }

    @Override
    public void move() {
        switch (direction) {
            case "LEFT" -> setX(getX() - speed);
            case "RIGHT" -> setX(getX() + speed);
            case "UP" -> setY(getY() - speed);
            case "DOWN" -> setY(getY() + speed);
            default -> {}
        }

        if (getX() > 600)
            setX(-24);
        if (getX() < -24)
            setX(600);
    }

    @Override
    public boolean canMove(String direction) {
        int newX = getX();
        int newY = getY();

        switch (direction) {
            case "LEFT" -> newX -= speed;
            case "RIGHT" -> newX += speed;
            case "UP" -> newY -= speed;
            case "DOWN" -> newY += speed;
            default -> {}
        }

        for (GameObject wall : Maze.getInstance().getWalls()) {
            if (collision(new GameObject(null, newX, newY, getWidth(), getHeight()), wall))
                return false;
        }

        for (GameObject gate : Maze.getInstance().getGates()) {
            if (collision(new GameObject(null, newX, newY, getWidth(), getHeight()), gate) && direction == "DOWN")
                return false;
        }

        return true;
    }

    @Override
    public void resetPositions() {
        setX(getStartX());
        setY(getStartY());
    }

    public int getSpeed() {return speed;}
    public String getDirection() {return direction;}

    public void setSpeed(int speed) {this.speed = speed;}
    public void setDirection(String direction) {this.direction = direction;}
}
