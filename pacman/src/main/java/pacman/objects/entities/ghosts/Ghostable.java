package pacman.objects.entities.ghosts;

import pacman.objects.Positionable;
import pacman.objects.entities.Movable;

import java.awt.Image;

public interface Ghostable extends Movable, Positionable {
    void moveBehaviour();
    void updateFreeze(long elapsedMillis);
    void monitorVulnerability();
    void eatenByPacMan();
    Image resetImage();
}
