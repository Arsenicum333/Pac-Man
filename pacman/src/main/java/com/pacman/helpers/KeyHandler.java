package com.pacman.helpers;

import com.pacman.Maze;
import com.pacman.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private final Maze maze;
    private final Game game;

    public KeyHandler(Maze maze, Game game) {
        this.maze = maze;
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (game.isGameOver() && e.getKeyCode() == KeyEvent.VK_SPACE) {
            game.restartGame();
            return;
        }

        if (!game.isGameOver()) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    maze.getPacman().setNewDirection("LEFT");
                    if (!game.isGameOn())
                        game.setGameOn(true);
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    maze.getPacman().setNewDirection("RIGHT");
                    if (!game.isGameOn())
                        game.setGameOn(true);
                    break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    maze.getPacman().setNewDirection("UP");
                    if (!game.isGameOn())
                        game.setGameOn(true);
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    maze.getPacman().setNewDirection("DOWN");
                    if (!game.isGameOn())
                        game.setGameOn(true);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
