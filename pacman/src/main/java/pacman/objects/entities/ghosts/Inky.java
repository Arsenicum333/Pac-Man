package pacman.objects.entities.ghosts;

import java.awt.Image;

import pacman.helpers.loaders.ImageLoader;

public class Inky extends Ghost {
    public Inky(Image image, int x, int y, int width, int height, int speed) {
        super(image, x, y, width, height, speed);
    }

    @Override
    public void moveBehaviour() {
        super.moveBehaviour();

        switch (direction) {
            case "LEFT" -> setImage(ImageLoader.getImage("InkyLeft"));
            case "RIGHT" -> setImage(ImageLoader.getImage("InkyRight"));
            case "UP" -> setImage(ImageLoader.getImage("InkyUp"));
            case "DOWN" -> setImage(ImageLoader.getImage("InkyDown"));
            default -> {}
        }
    }
}
