package pacman.objects.items;

import java.awt.Image;

import pacman.objects.entities.Entity;
import pacman.objects.entities.PacMan;

public class Heart extends Item {
    public Heart(Image image, int x, int y, int width, int height) {
        super(image, x, y, width, height);
    }

    @Override
    public Entity applyEffect(Entity entity) {
        if (entity instanceof PacMan pacman && !isCollected) {
            pacman.setLives(pacman.getLives() + 1);
            isCollected = true;
            return pacman;
        }
        return entity;
    }
}