package pacman.decorators;

import pacman.entities.Entity;

public abstract class EntityDecorator extends Entity {
    protected Entity decoratedEntity;

    public EntityDecorator(Entity entity, int speed) {
        super(entity.getImage(), entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight(), speed);
        this.decoratedEntity = entity;
    }

    @Override
    public void move() {decoratedEntity.move();}
    @Override
    public boolean canMove(String direction) {return decoratedEntity.canMove(direction);}
    @Override
    public void resetPositions() {decoratedEntity.resetPositions();}

    @Override
    public int getSpeed() {return decoratedEntity.getSpeed();}
    @Override
    public boolean isInvulnerable() {return decoratedEntity.isInvulnerable();}

    @Override
    public void setSpeed(int speed) {decoratedEntity.setSpeed(speed);}
    @Override
    public void setInvulnerable(boolean invulnerable) {decoratedEntity.setInvulnerable(invulnerable);}
}
