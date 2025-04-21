package pacman.items.powerups;

import pacman.entities.Entity;
import pacman.entities.PacMan;
import pacman.decorators.PowerPelletDecorator;

import java.awt.Image;

public class PowerPellet extends PowerUp {
    public PowerPellet(Image image, int x, int y, int width, int height, int duration) {
        super(image, x, y, width, height, duration);
    }

    @Override
    public Entity applyEffect(PacMan pacman) {
        if (!isCollected) {
            pacman.setCanEatGhosts(true);
            isCollected = true;
            return new PowerPelletDecorator(pacman, duration);
        }

        return pacman;
    }
}
