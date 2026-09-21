package pacman.objects.items.powerups;

import pacman.objects.entities.Entity;
import pacman.objects.items.Item;

import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

public abstract class PowerUp<T extends Entity> extends Item<T> {
    protected int duration;
    protected Timer effectTimer;

    public PowerUp(Image image, int x, int y, int width, int height, int duration) {
        super(image, x, y, width, height);
        this.duration = duration;
        this.effectTimer = new Timer();
    }

    @Override
    public final T applyEffect(T entity) {
        if (isCollected)
            return entity;

        isCollected = true;
        activate(entity);

        effectTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                deactivate(entity);
            }
        }, duration);

        return entity;
    }

    protected abstract void activate(T entity);
    protected abstract void deactivate(T entity);

    public int getDuration() {return duration;}
}
