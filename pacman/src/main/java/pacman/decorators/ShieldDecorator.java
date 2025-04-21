package pacman.decorators;

import pacman.entities.Entity;

public class ShieldDecorator extends EntityDecorator {
    public ShieldDecorator(Entity entity, int duration) {
        super(entity, entity.getSpeed());
    }

    @Override
    public boolean isInvulnerable() {return true;}
}
