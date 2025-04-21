package pacman.objects.entities.ghosts;

import java.awt.Image;
import java.util.Random;

import pacman.objects.entities.Entity;

public abstract class Ghost extends Entity implements Ghostable {
    private String[] directions = {"LEFT", "RIGHT", "UP", "DOWN"};
    private String newDirection = "";
    private Random random = new Random();
    private boolean isFrozen = false;

    public Ghost(Image image, int x, int y, int width, int height, int speed) {
        super(image, x, y, width, height, speed);
        direction = "UP";
    }

    @Override
    public void moveBehaviour() {
        if (!canMove(direction)) {
            do {
                newDirection = directions[random.nextInt(directions.length)];
            } while (!canMove(newDirection));
                direction = newDirection;
        }

        if (!isFrozen)
            super.move();
    }

    @Override
    public void resetPositions() {
        super.resetPositions();
        direction = "UP";
    }

    @Override
    public void freezeTemporarily() {
        isFrozen = true;

        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                isFrozen = false;
            }
        }, 5000);
    }

    @Override
    public boolean isInvulnerable() {return false;}

    @Override
    public void setInvulnerable(boolean invulnerable) {}
}
