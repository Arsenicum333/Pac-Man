package pacman.objects.items.powerups;

import pacman.Maze;
import pacman.objects.entities.Entity;
import pacman.objects.entities.PacMan;

import java.awt.Image;
import java.util.TimerTask;

public class SpeedBoost extends PowerUp {
    public SpeedBoost(Image image, int x, int y, int width, int height, int duration) {
        super(image, x, y, width, height, duration);
    }

    @Override
    public Entity applyEffect(Entity entity) {
        if (entity instanceof PacMan pacman && !isCollected) {
            isCollected = true;
            pacman.setSpeed(Maze.getTileSize() / 7);

            effectTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    pacman.setSpeed(Maze.getTileSize() / 8);
                }
            }, duration);

            return pacman;
        }

        return entity;
    }
}
