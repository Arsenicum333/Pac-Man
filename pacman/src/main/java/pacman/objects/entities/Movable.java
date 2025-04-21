package pacman.objects.entities;

public interface Movable {
    void move();
    boolean canMove(String direction);
    void resetPositions();
}
