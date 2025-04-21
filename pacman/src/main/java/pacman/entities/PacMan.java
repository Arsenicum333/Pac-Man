package pacman.entities;

import pacman.Maze;
import pacman.helpers.GameObject;
import pacman.helpers.loaders.ImageLoader;
import pacman.helpers.managers.ScoreManager;
import pacman.interfaces.Ghostable;
import pacman.items.Fruit;
import pacman.items.Item;

import static pacman.helpers.CollisionDetector.*;

import java.awt.Image;

public class PacMan extends Entity {
    private String newDirection = "";
    private int lives = 3;
    private int baseSpeed;
    private boolean canEatGhosts;
    private boolean invulnerable;

    public PacMan(Image image, int x, int y, int width, int height, int speed) {
        super(image, x, y, width, height, speed);
        this.baseSpeed = speed;
        this.canEatGhosts = false;
        this.invulnerable = false;
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

        Fruit currentFruit = maze.getCurrentFruit();
        if (currentFruit != null && !currentFruit.isCollected() && offsetCollision(this, currentFruit)) {
            this.applyItemEffect(currentFruit);
            maze.addCollectedFruit(currentFruit);
            maze.setCurrentFruit(null);
        }
    }

    public void loseLife() {
        Maze maze = Maze.getInstance();

        if (!invulnerable && maze.getGhosts().stream().anyMatch(ghost -> offsetCollision(this, ghost))) {
            direction = "";
            newDirection = "";
            lives--;

            if (lives > 0) {
                maze.getGhosts().forEach(Ghostable::resetPositions);
                resetPositions();
            }
        }
    }

    private void updateFromEntity(Entity entity) {
        this.setX(entity.getX());
        this.setY(entity.getY());
        this.setSpeed(entity.getSpeed());
        this.setDirection(entity.getDirection());
        this.setImage(entity.getImage());
        this.setWidth(entity.getWidth());
        this.setHeight(entity.getHeight());
        if (entity instanceof PacMan pacMan) {
            this.lives = pacMan.lives;
            this.canEatGhosts = pacMan.canEatGhosts;
            this.invulnerable = pacMan.invulnerable;
            this.baseSpeed = pacMan.baseSpeed;
            this.newDirection = pacMan.newDirection;
        }
    }

    private void applyItemEffect(Item item) {this.updateFromEntity(item.applyEffect(this));}

    public int getLives() {return lives;}
    public int getBaseSpeed() {return baseSpeed;}
    @Override
    public boolean isInvulnerable() {return invulnerable;}
    public boolean canEatGhosts() {return canEatGhosts;}

    public void setNewDirection(String newDirection) {this.newDirection = newDirection;}
    public void setLives(int lives) {this.lives = lives;}
    @Override
    public void setSpeed(int speed) {super.setSpeed(speed);}
    @Override
    public void setInvulnerable(boolean invulnerable) {this.invulnerable = invulnerable;}
    public void setCanEatGhosts(boolean canEatGhosts) {this.canEatGhosts = canEatGhosts;}
}
