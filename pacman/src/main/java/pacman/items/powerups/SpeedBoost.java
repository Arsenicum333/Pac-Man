package pacman.items.powerups;

import pacman.entities.Entity;
import pacman.entities.PacMan;
import pacman.decorators.SpeedBoostDecorator;

import java.awt.Image;

public class SpeedBoost extends PowerUp {
    public SpeedBoost(Image image, int x, int y, int width, int height, int duration) {
        super(image, x, y, width, height, duration);
    }

    @Override
    public Entity applyEffect(PacMan pacman) {
        if (!isCollected) {
            pacman.setSpeed(pacman.getSpeed() + 2);
            isCollected = true;
            return new SpeedBoostDecorator(pacman, duration);
        }

        return pacman;
    }
}
