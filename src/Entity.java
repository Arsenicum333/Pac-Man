import java.awt.Image;

public abstract class Entity extends GameObject {
    private static int speed = Maze.getTileSize() / 5;

    public Entity(Image image, int x, int y, int width, int height) {
        super(image, x, y, width, height);
    }

    public abstract void move();

    public static int getSpeed() {return speed;}
}
