package pacman.helpers;

import pacman.interfaces.Positionable;

import java.awt.Image;

public class GameObject implements Positionable {
    private Image image;
    private int x, y;
    private int width, height;
    private int startX, startY;

    public GameObject(Image image, int x, int y, int width, int height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.startX = x;
        this.startY = y;
    }

    public Image getImage() {return image;}
    @Override
    public int getX() {return x;}
    @Override
    public int getY() {return y;}
    @Override
    public int getWidth() {return width;}
    @Override
    public int getHeight() {return height;}
    public int getStartX() {return startX;}
    public int getStartY() {return startY;}

    public void setImage(Image image) {this.image = image;}
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public void setWidth(int width) {this.width = width;}
    public void setHeight(int height) {this.height = height;}
    public void setStartX(int startX) {this.startX = startX;}
    public void setStartY(int startY) {this.startY = startY;}
}
