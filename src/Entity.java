import java.awt.Image;

public abstract class Entity extends GameObject {
    private static int speed = Maze.getTileSize() / 8;
    protected String direction = "";

    public Entity(Image image, int x, int y, int width, int height) {
        super(image, x, y, width, height);
    }

    public void move() {
        if (direction.equals("LEFT")) {
            setX(getX() - speed);
        } else if (direction.equals("RIGHT")) {
            setX(getX() + speed);
        } else if (direction.equals("UP")) {
            setY(getY() - speed);
        } else if (direction.equals("DOWN")) {
            setY(getY() + speed);
        }
    }

    public void rollbackPosition() {
        if (direction.equals("LEFT")) {
            setX(getX() + speed);
        } else if (direction.equals("RIGHT")) {
            setX(getX() - speed);
        } else if (direction.equals("UP")) {
            setY(getY() + speed);
        } else if (direction.equals("DOWN")) {
            setY(getY() - speed);
        }
    }

    public static int getSpeed() {return speed;}
    public String getDirection() {return direction;}

    public void setDirection(String direction) {this.direction = direction;}
}
