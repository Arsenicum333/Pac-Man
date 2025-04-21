package pacman.items.powerups;

import pacman.items.Item;

import java.awt.Image;

public abstract class PowerUp extends Item {
    protected int duration;

    public PowerUp(Image image, int x, int y, int width, int height, int duration) {
        super(image, x, y, width, height);
        this.duration = duration;
    }

    public int getDuration() { return duration; }
}
