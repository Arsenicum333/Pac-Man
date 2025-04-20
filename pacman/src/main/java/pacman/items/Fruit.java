package pacman.items;

import pacman.entities.PacMan;
import pacman.entities.Entity;
import pacman.helpers.ScoreManager;

import java.awt.Image;

public class Fruit extends Item {
    private String type;
    private int points;

    public Fruit(Image image, int x, int y, int width, int height, String type, int points) {
        super(image, x, y, width, height);
        this.type = type;
        this.points = points;
    }

    @Override
    public Entity applyEffect(PacMan pacman) {
        if (!isCollected) {
            ScoreManager.getInstance().addPoints(points);
            isCollected = true;
            // AchievementManager.getInstance().collectFruit(type);
        }

        return pacman;
    }

    public String getType() {return type;}
}
