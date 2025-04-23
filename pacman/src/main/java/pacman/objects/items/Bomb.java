package pacman.objects.items;

import pacman.objects.entities.PacMan;

import java.awt.Image;

public class Bomb extends Item<PacMan> {
    public Bomb(Image image, int x, int y, int width, int height) {
        super(image, x, y, width, height);
    }

    @Override
    public PacMan applyEffect(PacMan pacman) {
        if (!isCollected) {
            pacman.loseLife();
            isCollected = true;
        }

        return pacman;
    }
}
