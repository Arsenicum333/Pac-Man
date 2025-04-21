package pacman.objects.entities.ghosts;

import pacman.objects.Positionable;
import pacman.objects.entities.Movable;

public interface Ghostable extends Movable, Positionable {
    void moveBehaviour();
    void freezeTemporarily();
}
