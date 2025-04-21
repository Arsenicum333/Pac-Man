package pacman.items;

import pacman.entities.PacMan;
import pacman.entities.Entity;

import java.awt.Image;

public class Bomb extends Item {
    public Bomb(Image image, int x, int y, int width, int height) {
        super(image, x, y, width, height);
    }

    @Override
    public Entity applyEffect(PacMan pacman) {
        if (!isCollected) {
            pacman.loseLife();
            isCollected = true;
        }

        return pacman;
    }
}