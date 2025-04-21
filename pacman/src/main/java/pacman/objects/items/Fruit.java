package pacman.objects.items;

import pacman.helpers.loaders.ImageLoader;
import pacman.helpers.managers.ScoreManager;
import pacman.objects.entities.Entity;
import pacman.objects.entities.PacMan;

import java.awt.Image;
import java.util.Random;

public class Fruit extends Item {
    private String type;
    private int points;
    private static final String[] FRUIT_TYPES = {"Cherry", "Strawberry", "Orange", "Apple", "Melon"};
    private static final int[] FRUIT_POINTS = {200, 400, 600, 800, 1000};
    private static final Random random = new Random();

    public Fruit(Image image, int x, int y, int width, int height, String type, int points) {
        super(image, x, y, width, height);
        this.type = type;
        this.points = points;
    }

    public static Fruit createRandomFruit(int x, int y, int width, int height) {
        int index = random.nextInt(FRUIT_TYPES.length);
        String type = FRUIT_TYPES[index];
        int points = FRUIT_POINTS[index];
        return new Fruit(ImageLoader.getImage(type), x, y, width, height, type, points);
    }

    @Override
    public Entity applyEffect(Entity entity) {
        if (entity instanceof PacMan pacman && !isCollected) {
            ScoreManager.getInstance().addPoints(points);
            isCollected = true;
            return pacman;
        }
        return entity;
    }

    public String getType() {return type;}
}
