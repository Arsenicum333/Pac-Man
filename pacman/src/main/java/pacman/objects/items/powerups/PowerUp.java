package pacman.objects.items.powerups;

import pacman.objects.entities.Entity;
import pacman.objects.items.Item;

import java.awt.Image;
import java.util.Timer;

public abstract class PowerUp<T extends Entity> extends Item<T> {
    protected int duration;
    protected Timer effectTimer;

    public PowerUp(Image image, int x, int y, int width, int height, int duration) {
        super(image, x, y, width, height);
        this.duration = duration;
        this.effectTimer = new Timer();
    }

    public int getDuration() {return duration;}
}
