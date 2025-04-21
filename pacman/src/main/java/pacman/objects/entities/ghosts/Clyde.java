package pacman.objects.entities.ghosts;

import java.awt.Image;

import pacman.helpers.loaders.ImageLoader;

public class Clyde extends Ghost {
    public Clyde(Image image, int x, int y, int width, int height, int speed) {
        super(image, x, y, width, height, speed);
    }

    @Override
    public void moveBehaviour() {
        super.moveBehaviour();

        switch (direction) {
            case "LEFT" -> setImage(ImageLoader.getImage("ClydeLeft"));
            case "RIGHT" -> setImage(ImageLoader.getImage("ClydeRight"));
            case "UP" -> setImage(ImageLoader.getImage("ClydeUp"));
            case "DOWN" -> setImage(ImageLoader.getImage("ClydeDown"));
            default -> {}
        }
    }
}
