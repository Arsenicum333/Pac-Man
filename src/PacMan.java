import java.awt.Image;

public class PacMan extends Entity {
    private String newDirection = "";

    public PacMan(Image image, int x, int y, int width, int height) {
        super(image, x, y, width, height);
    }

    @Override
    public void move() {
        if (!newDirection.isEmpty() && canMove (newDirection))
            direction = newDirection;

        if (canMove(direction))
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

    private boolean canMove(String direction) {
        int newX = getX();
        int newY = getY();

        if (direction.equals("LEFT")) {
            newX -= getSpeed();
        } else if (direction.equals("RIGHT")) {
            newX += getSpeed();
        } else if (direction.equals("UP")) {
            newY -= getSpeed();
        } else if (direction.equals("DOWN")) {
            newY += getSpeed();
        }

        for (GameObject wall : Maze.getInstance().walls) {
            if (Maze.getInstance().checkCollision(new GameObject(null, newX, newY, getWidth(), getHeight()), wall)) {
                return false;
            }
        }

        return true;
    }

    public String getNewDirection() {return newDirection;}

    public void setNewDirection(String newDirection) {this.newDirection = newDirection;}
}
