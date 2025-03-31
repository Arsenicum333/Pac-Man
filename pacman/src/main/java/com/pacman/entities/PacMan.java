package com.pacman.entities;

import com.pacman.Maze;
import com.pacman.GUI;
import com.pacman.helpers.GameObject;
import com.pacman.helpers.ImageLoader;
import static com.pacman.helpers.CollisionDetector.*;

import java.awt.Image;

public class PacMan extends Entity {
    private String newDirection = "";
    private int lives = 3;

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

    public void eatItem() {
        Maze maze = Maze.getInstance();
        GUI gui = GUI.getInstance();
        GameObject eatenItem = null;

        for (GameObject dot : maze.getDots()) {
            if (itemCollision(this, dot)) {
                eatenItem = dot;
                gui.setCurrentScore(gui.getCurrentScore() + 10);
                break;
            }
        }

        if (eatenItem != null)
            maze.getDots().remove(eatenItem);

        for (GameObject powerPellet : maze.getPowerPellets()) {
            if (itemCollision(this, powerPellet)) {
                eatenItem = powerPellet;
                gui.setCurrentScore(gui.getCurrentScore() + 50);
                break;
            }
        }

        if (eatenItem != null)
            maze.getPowerPellets().remove(eatenItem);
    }

    public String getNewDirection() {return newDirection;}
    public int getLives() {return lives;}

    public void setNewDirection(String newDirection) {this.newDirection = newDirection;}
    public void setLives(int lives) {this.lives = lives;}
}
