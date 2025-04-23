package pacman.objects.items.powerups;

import pacman.objects.entities.PacMan;

import java.awt.Image;
import java.util.TimerTask;

public class Shield extends PowerUp<PacMan> {
    public Shield(Image image, int x, int y, int width, int height, int duration) {
        super(image, x, y, width, height, duration);
    }

    @Override
    public PacMan applyEffect(PacMan pacman) {
        if (!isCollected) {
            isCollected = true;
            pacman.setInvulnerable(true);

            effectTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    pacman.setInvulnerable(false);
                }
            }, duration);
        }

        return pacman;
    }
}
