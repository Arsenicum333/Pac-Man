package pacman.objects.items;

import pacman.objects.entities.PacMan;

import java.awt.Image;

public class Heart extends Item<PacMan> {
    public Heart(Image image, int x, int y, int width, int height) {
        super(image, x, y, width, height);
    }

    @Override
    public PacMan applyEffect(PacMan pacman) {
        if (!isCollected) {
            pacman.setLives(pacman.getLives() + 1);
            isCollected = true;
        }

        return pacman;
    }
}
