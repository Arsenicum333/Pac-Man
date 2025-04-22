package pacman.handlers;

import pacman.Game;
import pacman.Maze;
import pacman.objects.entities.PacMan;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private final Game game;

    public KeyHandler(Game game) {this.game = game;}

    @Override
    public void keyPressed(KeyEvent e) {
        PacMan pacman = Maze.getInstance().getPacman();
        int key = e.getKeyCode();

        if (game.isGameOver() && key == KeyEvent.VK_SPACE) {
            game.restartGame();
            return;
        }

        if (key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_P) {
            game.togglePause();
            return;
        }

        if (game.isPaused()) return;

        if (!game.isGameOver()) {
            boolean directionSet = false;

            if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
                pacman.setNewDirection("LEFT");
                directionSet = true;
            } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                pacman.setNewDirection("RIGHT");
                directionSet = true;
            } else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
                pacman.setNewDirection("UP");
                directionSet = true;
            } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
                pacman.setNewDirection("DOWN");
                directionSet = true;
            }

            if (directionSet && !game.isPlaying())
                game.startGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
