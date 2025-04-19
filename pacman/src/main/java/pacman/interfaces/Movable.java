package pacman.interfaces;

public interface Movable {
    void move();
    boolean canMove(String direction);
    void resetPositions();
}
