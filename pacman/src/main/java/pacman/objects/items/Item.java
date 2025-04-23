package pacman.objects.items;

import pacman.objects.GameObject;
import pacman.objects.entities.Entity;

import java.awt.Image;

public abstract class Item<T extends Entity> extends GameObject {
    protected boolean isCollected;

    public Item(Image image, int x, int y, int width, int height) {
        super(image, x, y, width, height);
        this.isCollected = false;
    }

    public abstract T applyEffect(T entity);

    public boolean isCollected() {return isCollected;}

    public void setCollected(boolean collected) {this.isCollected = collected;}
}
