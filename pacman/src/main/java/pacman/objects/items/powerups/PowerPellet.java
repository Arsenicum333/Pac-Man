package pacman.objects.items.powerups;

import pacman.Maze;
import pacman.managers.ScoreManager;
import pacman.objects.entities.PacMan;
import pacman.objects.entities.ghosts.Ghost;

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

            Maze.getInstance().getGhosts().forEach(ghostable -> {
                if (ghostable instanceof Ghost ghost) {
                    ghost.setInvulnerable(true);
                }
            });

            effectTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    pacman.setCanEatGhosts(false);
                    pacman.resetGhostScore();

                    Maze.getInstance().getGhosts().forEach(ghostable -> {
                        if (ghostable instanceof Ghost ghost) {
                            ghost.setInvulnerable(false);
                        }
                    });
                }
            }, duration);
        }

        return pacman;
    }
}
