import java.awt.Image;

public class GameObject {
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
    }

    public Image getImage() {return image;}
    public int getX() {return x;}
    public int getY() {return y;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public int getStartX() {return startX;}
    public int getStartY() {return startY;}
}
