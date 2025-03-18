import java.awt.Image;

public class PacMan extends Entity {
    private String direction;

    public PacMan(Image image, int x, int y, int width, int height) {
        super(image, x, y, width, height);
        this.direction = "LEFT";
    }

    @Override
    public void move() {
        if (direction.equals("LEFT")) {
            setX(getX() - getSpeed());
            setImage(ImageLoader.getImage("PacManLeft"));
        } else if (direction.equals("RIGHT")) {
            setX(getX() + getSpeed());
            setImage(ImageLoader.getImage("PacManRight"));
        } else if (direction.equals("UP")) {
            setY(getY() - getSpeed());
            setImage(ImageLoader.getImage("PacManUp"));
        } else if (direction.equals("DOWN")) {
            setY(getY() + getSpeed());
            setImage(ImageLoader.getImage("PacManDown"));
        }
    }

    public void setDirection(String direction) {this.direction = direction;}

    public String getDirection() {return direction;}
}
