package pacman.decorators;

import pacman.entities.PacMan;
import pacman.entities.Entity;

public class PowerPelletDecorator extends EntityDecorator {
    public PowerPelletDecorator(Entity entity, int duration) {
        super(entity, entity.getSpeed());
    }

    public boolean canEatGhosts() {
        if (decoratedEntity instanceof PacMan)
            return ((PacMan) decoratedEntity).canEatGhosts();

        return false;
    }
}
