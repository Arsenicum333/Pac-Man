package pacman.decorators;

import pacman.entities.Entity;

public class SpeedBoostDecorator extends EntityDecorator {
    public SpeedBoostDecorator(Entity entity, int duration) {
        super(entity, entity.getSpeed());
    }

    @Override
    public int getSpeed() {return decoratedEntity.getSpeed();}
}
