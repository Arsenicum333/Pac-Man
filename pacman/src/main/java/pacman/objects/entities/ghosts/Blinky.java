package pacman.objects.entities.ghosts;

import java.awt.Image;

import pacman.loaders.ImageLoader;

public class Blinky extends Ghost {
    public Blinky(Image image, int x, int y, int width, int height, int speed) {
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
            case "LEFT" -> ImageLoader.getImage("BlinkyLeft");
            case "RIGHT" -> ImageLoader.getImage("BlinkyRight");
            case "UP" -> ImageLoader.getImage("BlinkyUp");
            case "DOWN" -> ImageLoader.getImage("BlinkyDown");
            default -> ImageLoader.getImage("BlinkyUp");
        };
    }
}
