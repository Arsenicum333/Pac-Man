package pacman.items.powerups;

import pacman.entities.Entity;
import pacman.entities.PacMan;
import pacman.decorators.ShieldDecorator;

import java.awt.Image;
import java.util.TimerTask;

public class Shield extends PowerUp {
    public Shield(Image image, int x, int y, int width, int height, int duration) {
        super(image, x, y, width, height, duration);
    }

    @Override
    public Entity applyEffect(PacMan pacman) {
        if (!isCollected) {
            pacman.setInvulnerable(true);
            isCollected = true;

            effectTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    pacman.setInvulnerable(false);
                }
            }, duration);

            return new ShieldDecorator(pacman, duration);
        }

        return pacman;
    }
}
