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
    private final int normalSpeed;


    public Ghost(Image image, int x, int y, int width, int height, int speed) {
        super(image, x, y, width, height, speed);
        normalSpeed = speed;
        direction = "UP";
    }

    @Override
    public void moveBehaviour() {
        if (isFrozen)
            return;

        for (int step = 0; step < getSpeed(); step++) {
            if (!canMove(direction, 1)) {
                do {
                    newDirection = directions[random.nextInt(directions.length)];
                } while (!canMove(newDirection, 1));
                direction = newDirection;
            }

            if (canMove(direction, 1))
                moveBy(1);
            else
                break;
        }
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
        if (vulnerabilityCount == 0)
            setSpeed(Math.max(1, normalSpeed - 1));

        vulnerabilityCount++;
        setInvulnerable(true);
    }

    public void restoreVulnerability() {
        setInvulnerable(true);
    }

    public void removeVulnerability() {
        vulnerabilityCount = Math.max(0, vulnerabilityCount - 1);
        if (vulnerabilityCount == 0) {
            setSpeed(normalSpeed);
            setInvulnerable(false);
        }
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
