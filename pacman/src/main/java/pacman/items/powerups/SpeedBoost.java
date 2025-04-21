package pacman.items.powerups;

import pacman.entities.Entity;
import pacman.entities.PacMan;
import pacman.decorators.SpeedBoostDecorator;

import java.awt.Image;
import java.util.TimerTask;

public class SpeedBoost extends PowerUp {
    public SpeedBoost(Image image, int x, int y, int width, int height, int duration) {
        super(image, x, y, width, height, duration);
    }

    @Override
    public Entity applyEffect(PacMan pacman) {
        if (!isCollected) {
            pacman.setSpeed(pacman.getSpeed() + 2);
            isCollected = true;

            effectTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    pacman.setSpeed(pacman.getBaseSpeed());
                }
            }, duration);

            return new SpeedBoostDecorator(pacman, duration);
        }

        return pacman;
    }
}
