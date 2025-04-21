package pacman.objects.items.powerups;

import java.awt.Image;
import java.util.TimerTask;

import pacman.objects.entities.Entity;
import pacman.objects.entities.PacMan;

public class Shield extends PowerUp {
    public Shield(Image image, int x, int y, int width, int height, int duration) {
        super(image, x, y, width, height, duration);
    }

    @Override
    public Entity applyEffect(Entity entity) {
        if (entity instanceof PacMan pacman && !isCollected) {
            isCollected = true;
            pacman.setInvulnerable(true);

            effectTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    pacman.setInvulnerable(false);
                }
            }, duration);

            return pacman;
        }

        return entity;
    }
}
