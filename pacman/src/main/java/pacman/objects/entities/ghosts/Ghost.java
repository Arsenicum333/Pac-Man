package pacman.objects.entities.ghosts;

import java.awt.Image;
import java.util.Random;

import pacman.loaders.ImageLoader;
import pacman.objects.entities.Entity;

public abstract class Ghost extends Entity implements Ghostable {
    private String[] directions = {"LEFT", "RIGHT", "UP", "DOWN"};
    private String newDirection = "";
    private Random random = new Random();
    private boolean isFrozen = false;
    private boolean invulnerable = false;
    private boolean blinkState = false;
    private int blinkCounter = 0;
    private int vulnerabilityCount = 0;


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
    public void monitorVulnerability() {
        if (!isInvulnerable()) {
            setImage(resetImage());
            return;
        }

        blinkCounter++;

        if (blinkCounter >= 50) {
            blinkState = !blinkState;
            setImage(blinkState ? ImageLoader.getImage("VulnerableBlue")
                                : ImageLoader.getImage("VulnerableWhite"));
            blinkCounter = 0;
        }
    }

    @Override
    public void eatenByPacMan() {
        vulnerabilityCount = 0;
        setInvulnerable(false);
        setImage(resetImage());
        blinkCounter = 0;
        blinkState = false;
        resetPositions();
        freezeTemporarily();
    }

    @Override
    public boolean isInvulnerable() {return invulnerable;}

    public void addVulnerability() {
        vulnerabilityCount++;
        setInvulnerable(true);
    }

    public void removeVulnerability() {
        vulnerabilityCount = Math.max(0, vulnerabilityCount - 1);
        if (vulnerabilityCount == 0)
            setInvulnerable(false);
    }

    @Override
    public void setInvulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;

        if (invulnerable) {
            blinkState = true;
            blinkCounter = 0;
            setImage(ImageLoader.getImage("VulnerableBlue"));
        } else {
            setImage(resetImage());
        }
    }
}
