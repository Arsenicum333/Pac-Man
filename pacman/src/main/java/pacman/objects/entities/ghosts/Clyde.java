package pacman.objects.entities.ghosts;

import java.awt.Image;

import pacman.loaders.ImageLoader;

public class Clyde extends Ghost {
    public Clyde(Image image, int x, int y, int width, int height, int speed) {
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
        switch (direction) {
            case "LEFT": return ImageLoader.getImage("ClydeLeft");
            case "RIGHT": return ImageLoader.getImage("ClydeRight");
            case "UP": return ImageLoader.getImage("ClydeUp");
            case "DOWN": return ImageLoader.getImage("ClydeDown");
            default: return ImageLoader.getImage("ClydeUp");
        }
    }
}
