package pacman.objects.entities.ghosts;

import java.awt.Image;

import pacman.loaders.ImageLoader;

public class Pinky extends Ghost {
    public Pinky(Image image, int x, int y, int width, int height, int speed) {
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
            case "LEFT" -> ImageLoader.getImage("PinkyLeft");
            case "RIGHT" -> ImageLoader.getImage("PinkyRight");
            case "UP" -> ImageLoader.getImage("PinkyUp");
            case "DOWN" -> ImageLoader.getImage("PinkyDown");
            default -> ImageLoader.getImage("PinkyUp");
        };
    }
}
