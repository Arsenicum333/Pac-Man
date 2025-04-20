package pacman.items;

import pacman.entities.PacMan;
import pacman.entities.Entity;
import pacman.helpers.GameObject;

import java.awt.Image;

public abstract class Item extends GameObject {
    protected boolean isCollected;

    public Item(Image image, int x, int y, int width, int height) {
        super(image, x, y, width, height);
        this.isCollected = false;
    }

    public abstract Entity applyEffect(PacMan pacman);

    public boolean isCollected() {return isCollected;}

    public void setCollected(boolean collected) {this.isCollected = collected;}
}
