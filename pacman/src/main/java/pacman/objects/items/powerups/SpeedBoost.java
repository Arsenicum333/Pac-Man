package pacman.objects.items.powerups;

import pacman.objects.entities.PacMan;

import java.awt.Image;

public class SpeedBoost extends PowerUp<PacMan> {
    public SpeedBoost(Image image, int x, int y, int width, int height, int duration) {
        super(image, x, y, width, height, duration);
    }

    @Override
    protected void activate(PacMan pacman) {pacman.addSpeedBoost();}

    @Override
    protected void deactivate(PacMan pacman) {pacman.removeSpeedBoost();}
}
