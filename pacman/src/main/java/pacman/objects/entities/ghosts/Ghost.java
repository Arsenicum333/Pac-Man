package pacman.objects.entities.ghosts;

import java.awt.Image;
import java.util.Random;

import pacman.objects.entities.Entity;

public abstract class Ghost extends Entity implements Ghostable {
    private String[] directions = {"LEFT", "RIGHT", "UP", "DOWN"};
    private String newDirection = "";
    private Random random = new Random();

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

        super.move();
    }

    @Override
    public void resetPositions() {
        super.resetPositions();
        direction = "UP";
    }

    @Override
    public boolean isInvulnerable() {return false;}

    @Override
    public void setInvulnerable(boolean invulnerable) {}
}
