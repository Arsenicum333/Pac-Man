package pacman.objects.items.powerups;

import pacman.managers.ScoreManager;
import pacman.objects.entities.PacMan;

import java.awt.Image;
import java.util.TimerTask;

public class PowerPellet extends PowerUp<PacMan> {
    public PowerPellet(Image image, int x, int y, int width, int height, int duration) {
        super(image, x, y, width, height, duration);
    }

    @Override
    public PacMan applyEffect(PacMan pacman) {
        if (!isCollected) {
            ScoreManager.getInstance().addPoints(50);
            isCollected = true;
            pacman.setCanEatGhosts(true);

            effectTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    pacman.setCanEatGhosts(false);
                    pacman.resetGhostScore();
                }
            }, duration);
        }

        return pacman;
    }
}
