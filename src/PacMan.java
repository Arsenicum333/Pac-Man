import java.awt.Image;

public class PacMan extends Entity {
    public PacMan(Image image, int x, int y, int width, int height) {
        super(image, x, y, width, height);
    }

    @Override
    public void move() {
        super.move();

        if (direction.equals("LEFT")) {
            setImage(ImageLoader.getImage("PacManLeft"));
        } else if (direction.equals("RIGHT")) {
            setImage(ImageLoader.getImage("PacManRight"));
        } else if (direction.equals("UP")) {
            setImage(ImageLoader.getImage("PacManUp"));
        } else if (direction.equals("DOWN")) {
            setImage(ImageLoader.getImage("PacManDown"));
        }
    }

    @Override
    public void rollbackPosition() {
        super.rollbackPosition();

        if (direction.equals("LEFT")) {
            setImage(ImageLoader.getImage("PacManLeft"));
        } else if (direction.equals("RIGHT")) {
            setImage(ImageLoader.getImage("PacManRight"));
        } else if (direction.equals("UP")) {
            setImage(ImageLoader.getImage("PacManUp"));
        } else if (direction.equals("DOWN")) {
            setImage(ImageLoader.getImage("PacManDown"));
        }
    }
}
