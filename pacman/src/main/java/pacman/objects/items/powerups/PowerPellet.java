package pacman.objects.items.powerups;

import pacman.Maze;
import pacman.managers.ScoreManager;
import pacman.objects.entities.PacMan;
import pacman.objects.entities.ghosts.Ghost;

import java.awt.Image;

public class PowerPellet extends PowerUp<PacMan> {
    public PowerPellet(Image image, int x, int y, int width, int height, int duration) {
        super(image, x, y, width, height, duration);
    }

    @Override
    protected void activate(PacMan pacman) {
        ScoreManager.getInstance().addPoints(50);
        pacman.addGhostEating();

        Maze.getInstance().getGhosts().forEach(ghostable -> {
            if (ghostable instanceof Ghost ghost)
                ghost.addVulnerability();
        });
    }

    @Override
    protected void deactivate(PacMan pacman) {
        pacman.removeGhostEating();

        Maze.getInstance().getGhosts().forEach(ghostable -> {
            if (ghostable instanceof Ghost ghost)
                ghost.removeVulnerability();
        });
    }
}
