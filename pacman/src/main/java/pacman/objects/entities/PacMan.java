package pacman.objects.entities;

import pacman.Maze;
import pacman.loaders.ImageLoader;
import pacman.managers.ScoreManager;
import pacman.objects.GameObject;
import pacman.objects.entities.ghosts.Ghostable;
import pacman.objects.items.Bomb;
import pacman.objects.items.Fruit;
import pacman.objects.items.Item;
import static pacman.helpers.CollisionDetector.*;

import java.awt.Image;

public class PacMan extends Entity {
    private String newDirection = "";
    private int lives = 3;
    private boolean canEatGhosts;
    private boolean invulnerable;
    private int ghostScore = 200;
    private int speedBoostCount;
    private int ghostEatingCount;
    private int shieldCount;

    public PacMan(Image image, int x, int y, int width, int height, int speed) {
        super(image, x, y, width, height, speed);
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
        GameObject eatenDot = null;
        Item<PacMan> eatenItem = null;
        Fruit currentFruit = maze.getCurrentFruit();

        for (GameObject dot : maze.getDots()) {
            if (offsetCollision(this, dot)) {
                eatenDot = dot;
                ScoreManager.getInstance().addPoints(10);
                break;
            }
        }

        if (eatenDot != null)
            maze.getDots().remove(eatenDot);

        for (Item<PacMan> item : maze.getItems()) {
            if (!item.isCollected() && offsetCollision(this, item)) {
                eatenItem = item;
                this.applyItemEffect(item);
                break;
            }
        }

        if (eatenItem != null) {
            maze.getItems().remove(eatenItem);
        }

        if (currentFruit != null && !currentFruit.isCollected() && offsetCollision(this, currentFruit)) {
            this.applyItemEffect(currentFruit);
            maze.addCollectedFruit(currentFruit);
            maze.setCurrentFruit(null);
        }
    }

    public void eatGhost() {
        Maze maze = Maze.getInstance();

        if (canEatGhosts) {
            maze.getGhosts().stream()
                .filter(ghost -> offsetCollision(this, ghost))
                .findFirst()
                .ifPresent(ghost -> {
                    ScoreManager.getInstance().addPoints(ghostScore);
                    ghost.eatenByPacMan();
                    ghostScore *= 2;
                });
        }
    }

    public void loseLife() {
        Maze maze = Maze.getInstance();

        boolean hasCollisionWithGhost = maze.getGhosts().stream()
                .anyMatch(ghost -> offsetCollision(this, ghost));
        boolean hasCollisionWithBomb = maze.getItems().stream()
                .anyMatch(item -> item instanceof Bomb && !item.isCollected() && offsetCollision(this, item));

        if (!invulnerable && (hasCollisionWithGhost || hasCollisionWithBomb)) {
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
        if (entity instanceof PacMan pacman) {
            this.lives = pacman.lives;
            this.canEatGhosts = pacman.canEatGhosts;
            this.invulnerable = pacman.invulnerable;
            this.newDirection = pacman.newDirection;
            this.speedBoostCount = pacman.speedBoostCount;
            this.ghostEatingCount = pacman.ghostEatingCount;
            this.shieldCount = pacman.shieldCount;
        }
    }

    public void resetGhostScore() {this.ghostScore = 200;}

    public void addSpeedBoost() {
        speedBoostCount++;
        setSpeed(Maze.getTileSize() / 7);
    }

    public void removeSpeedBoost() {
        speedBoostCount = Math.max(0, speedBoostCount - 1);
        if (speedBoostCount == 0)
            setSpeed(Maze.getTileSize() / 8);
    }

    public void addGhostEating() {
        ghostEatingCount++;
        canEatGhosts = true;
    }

    public void removeGhostEating() {
        ghostEatingCount = Math.max(0, ghostEatingCount - 1);

        if (ghostEatingCount == 0) {
            canEatGhosts = false;
            resetGhostScore();
        }
    }

    public void addShield() {
        shieldCount++;
        invulnerable = true;
    }

    public void removeShield() {
        shieldCount = Math.max(0, shieldCount - 1);

        if (shieldCount == 0)
            invulnerable = false;
    }

    private void applyItemEffect(Item<PacMan> item) {this.updateFromEntity(item.applyEffect(this));}

    public String getNewDirection() {return newDirection;}
    public int getLives() {return lives;}
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
