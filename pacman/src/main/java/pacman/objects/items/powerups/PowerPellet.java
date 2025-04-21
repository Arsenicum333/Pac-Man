package pacman.objects.items.powerups;

import java.awt.Image;
import java.util.TimerTask;

import pacman.helpers.managers.ScoreManager;
import pacman.objects.entities.Entity;
import pacman.objects.entities.PacMan;

public class PowerPellet extends PowerUp {
    public PowerPellet(Image image, int x, int y, int width, int height, int duration) {
        super(image, x, y, width, height, duration);
    }

    @Override
    public Entity applyEffect(Entity entity) {
        if (entity instanceof PacMan pacman && !isCollected) {
            ScoreManager.getInstance().addPoints(50);
            isCollected = true;
            pacman.setCanEatGhosts(true);

            effectTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    pacman.setCanEatGhosts(false);
                    pacman.resetGhostScoreMultiplier();
                }
            }, duration);

            return pacman;
        }

        return entity;
    }
}
