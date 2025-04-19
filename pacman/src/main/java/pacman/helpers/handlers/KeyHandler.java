package pacman.helpers.handlers;

import pacman.Game;
import pacman.Maze;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private final Maze maze;
    private final Game game;

    public KeyHandler(Game game, Maze maze) {
        this.game = game;
        this.maze = maze;
    }

    @Override
    public void keyPressed(KeyEvent e) {
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
            if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
                maze.getPacman().setNewDirection("LEFT");

                if (!game.isGameOn())
                    game.setGameOn(true);
            } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                maze.getPacman().setNewDirection("RIGHT");

                if (!game.isGameOn())
                    game.setGameOn(true);
            } else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
                maze.getPacman().setNewDirection("UP");

                if (!game.isGameOn())
                    game.setGameOn(true);
            } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
                maze.getPacman().setNewDirection("DOWN");

                if (!game.isGameOn())
                    game.setGameOn(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
