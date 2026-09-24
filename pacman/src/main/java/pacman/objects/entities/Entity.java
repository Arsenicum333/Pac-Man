package pacman.objects.entities;

import pacman.Maze;
import pacman.objects.GameObject;
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
        moveBy(speed);
    }

    protected void moveBy(int distance) {
        switch (direction) {
            case "LEFT" -> setX(getX() - distance);
            case "RIGHT" -> setX(getX() + distance);
            case "UP" -> setY(getY() - distance);
            case "DOWN" -> setY(getY() + distance);
            default -> {}
        }

        if (getX() > 600)
            setX(-24);
        if (getX() < -24)
            setX(600);
    }

    @Override
    public boolean canMove(String direction) {
        return canMove(direction, speed);
    }

    protected boolean canMove(String direction, int distance) {
        int newX = getX();
        int newY = getY();

        switch (direction) {
            case "LEFT" -> newX -= distance;
            case "RIGHT" -> newX += distance;
            case "UP" -> newY -= distance;
            case "DOWN" -> newY += distance;
            default -> {}
        }

        for (GameObject wall : Maze.getInstance().getWalls()) {
            if (collision(new GameObject(null, newX, newY, getWidth(), getHeight()), wall))
                return false;
        }

        for (GameObject gate : Maze.getInstance().getGates()) {
            if (collision(new GameObject(null, newX, newY, getWidth(), getHeight()), gate) && "DOWN".equals(direction))
                return false;
        }

        return true;
    }

    @Override
    public void resetPositions() {
        setX(getStartX());
        setY(getStartY());
    }

    public String getDirection() {return direction;}
    public int getSpeed() {return speed;}
    public abstract boolean isInvulnerable();

    public void setDirection(String direction) {this.direction = direction;}
    public void setSpeed(int speed) {this.speed = speed;}
    public abstract void setInvulnerable(boolean invulnerable);
}
