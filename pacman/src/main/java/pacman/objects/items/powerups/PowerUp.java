package pacman.objects.items.powerups;

import pacman.objects.entities.Entity;
import pacman.objects.items.Item;

import java.awt.Image;

public abstract class PowerUp<T extends Entity> extends Item<T> {
    protected int duration;
    private long elapsed;
    private boolean active;

    public PowerUp(Image image, int x, int y, int width, int height, int duration) {
        super(image, x, y, width, height);
        this.duration = duration;
    }

    @Override
    public final T applyEffect(T entity) {
        if (isCollected)
            return entity;

        isCollected = true;
        activate(entity);
        active = true;

        return entity;
    }

    public boolean update(T entity, long elapsedMillis) {
        if (!active)
            return true;

        elapsed += elapsedMillis;
        if (elapsed < duration)
            return false;

        active = false;
        deactivate(entity);
        return true;
    }

    protected void resetTimer() {elapsed = 0;}

    protected abstract void activate(T entity);
    protected abstract void deactivate(T entity);

    public int getDuration() {return duration;}
}
