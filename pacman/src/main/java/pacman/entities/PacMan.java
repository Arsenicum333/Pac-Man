package pacman.entities;

import pacman.Maze;
import pacman.helpers.GameObject;
import pacman.helpers.ScoreManager;
import pacman.helpers.loaders.ImageLoader;
import pacman.interfaces.Ghostable;
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
                ScoreManager.getInstance().addPoints(10);
                break;
            }
        }

        if (eatenItem != null)
            maze.getDots().remove(eatenItem);

        for (GameObject powerPellet : maze.getPowerPellets()) {
            if (offsetCollision(this, powerPellet)) {
                eatenItem = powerPellet;
                ScoreManager.getInstance().addPoints(50);
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
                maze.getGhosts().forEach(Ghostable::resetPositions);
                resetPositions();
            }
        }
    }

    public int getLives() {return lives;}

    public void setNewDirection(String newDirection) {this.newDirection = newDirection;}
    public void setLives(int lives) {this.lives = lives;}
}
