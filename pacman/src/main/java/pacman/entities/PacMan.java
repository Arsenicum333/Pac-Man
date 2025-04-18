package pacman.entities;

import pacman.Maze;
import pacman.entities.ghosts.Ghost;
import pacman.helpers.GameObject;
import pacman.helpers.loaders.ImageLoader;

import static pacman.helpers.CollisionDetector.*;

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

        switch (direction) {
            case "LEFT" -> setImage(ImageLoader.getImage("PacManLeft"));
            case "RIGHT" -> setImage(ImageLoader.getImage("PacManRight"));
            case "UP" -> setImage(ImageLoader.getImage("PacManUp"));
            case "DOWN" -> setImage(ImageLoader.getImage("PacManDown"));
            default -> {}
        }
    }

    public void eatItem() {
        Maze maze = Maze.getInstance();
        GameObject eatenItem = null;

        for (GameObject dot : maze.getDots()) {
            if (offsetCollision(this, dot)) {
                eatenItem = dot;
                maze.setScore(maze.getScore() + 10);
                break;
            }
        }

        if (eatenItem != null)
            maze.getDots().remove(eatenItem);

        for (GameObject powerPellet : maze.getPowerPellets()) {
            if (offsetCollision(this, powerPellet)) {
                eatenItem = powerPellet;
                maze.setScore(maze.getScore() + 50);
                break;
            }
        }

        if (eatenItem != null)
            maze.getPowerPellets().remove(eatenItem);
    }

    public void loseLife() {
        Maze maze = Maze.getInstance();

        if (maze.getGhosts().stream().anyMatch(ghost -> offsetCollision(this, ghost))) {
            direction = "";
            newDirection = "";
            lives--;

            if (lives > 0) {
                maze.getGhosts().forEach(Ghost::resetPositions);
                resetPositions();
            }
        }
    }

    public String getNewDirection() {return newDirection;}
    public int getLives() {return lives;}

    public void setNewDirection(String newDirection) {this.newDirection = newDirection;}
    public void setLives(int lives) {this.lives = lives;}
}
