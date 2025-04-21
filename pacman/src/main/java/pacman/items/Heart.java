package pacman.items;

import pacman.entities.PacMan;
import pacman.entities.Entity;

import java.awt.Image;

public class Heart extends Item {
    public Heart(Image image, int x, int y, int width, int height) {
        super(image, x, y, width, height);
    }

    @Override
    public Entity applyEffect(PacMan pacman) {
        if (!isCollected) {
            pacman.setLives(pacman.getLives() + 1);
            isCollected = true;
        }

        return pacman;
    }
}