package pacman.objects.entities.ghosts;

import java.awt.Image;

import pacman.loaders.ImageLoader;

public class Inky extends Ghost {
    public Inky(Image image, int x, int y, int width, int height, int speed) {
        super(image, x, y, width, height, speed);
    }

    @Override
    public void moveBehaviour() {
        super.moveBehaviour();

        if (!isInvulnerable()) {
            setImage(resetImage());
        }
    }

    @Override
    public Image resetImage() {
        return switch (direction) {
            case "LEFT" -> ImageLoader.getImage("InkyLeft");
            case "RIGHT" -> ImageLoader.getImage("InkyRight");
            case "UP" -> ImageLoader.getImage("InkyUp");
            case "DOWN" -> ImageLoader.getImage("InkyDown");
            default -> ImageLoader.getImage("InkyUp");
        };
    }
}
