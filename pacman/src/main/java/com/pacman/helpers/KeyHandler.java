package com.pacman.helpers;

import com.pacman.Maze;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private final Maze maze;

    public KeyHandler(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> maze.getPacman().setNewDirection("LEFT");
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> maze.getPacman().setNewDirection("RIGHT");
            case KeyEvent.VK_UP, KeyEvent.VK_W -> maze.getPacman().setNewDirection("UP");
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> maze.getPacman().setNewDirection("DOWN");
            default -> {}
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
