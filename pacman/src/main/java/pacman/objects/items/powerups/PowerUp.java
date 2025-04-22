package pacman.objects.items.powerups;

import java.awt.Image;
import java.util.Timer;

import pacman.objects.items.Item;

public abstract class PowerUp extends Item {
    protected int duration;
    protected Timer effectTimer;

    public PowerUp(Image image, int x, int y, int width, int height, int duration) {
        super(image, x, y, width, height);
        this.duration = duration;
        this.effectTimer = new Timer();
    }

    public int getDuration() {return duration;}
}
