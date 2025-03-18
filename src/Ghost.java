import java.awt.Image;

public abstract class Ghost extends Entity {
    public Ghost(Image image, int x, int y, int width, int height) {
        super(image, x, y, width, height);
    }

    public void move(){};
}
