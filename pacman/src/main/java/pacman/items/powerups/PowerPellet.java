package pacman.items.powerups;

import pacman.entities.Entity;
import pacman.entities.PacMan;
import pacman.decorators.PowerPelletDecorator;

import java.awt.Image;
import java.util.TimerTask;

public class PowerPellet extends PowerUp {
    public PowerPellet(Image image, int x, int y, int width, int height, int duration) {
        super(image, x, y, width, height, duration);
    }

    @Override
    public Entity applyEffect(PacMan pacman) {
        if (!isCollected) {
            pacman.setCanEatGhosts(true);
            isCollected = true;

            effectTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    pacman.setCanEatGhosts(false);
                }
            }, duration);

            return new PowerPelletDecorator(pacman, duration);
        }

        return pacman;
    }
}
