package com.pacman;

import com.pacman.helpers.ImageLoader;
import com.pacman.helpers.KeyHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game implements ActionListener {
    private Maze maze;
    private GUI gui;
    private Timer gameLoop;
    private static final int WINDOW_OFFSET_X = 16;
    private static final int WINDOW_OFFSET_Y = 160;

    Game(int gameWidth, int gameHeight) {
        maze = Maze.getInstance();
        gui = GUI.getInstance();
        gui.setPreferredSize(new Dimension(gameWidth, gameHeight));

        gameLoop = new Timer(13, this);
        gameLoop.start();

        gui.addKeyListener(new KeyHandler(maze));
        gui.setFocusable(true);
    }

    public static void main(String[] args) throws Exception {
        int gameWidth = Maze.getColumnCount() * Maze.getTileSize() + WINDOW_OFFSET_X;
        int gameHeight = Maze.getRowCount() * Maze.getTileSize() + WINDOW_OFFSET_Y;

        Game game = new Game(gameWidth, gameHeight);
        JFrame frame = new JFrame("Pac-Man");
        ImageIcon icon = new ImageIcon(ImageLoader.getImage("PacManIconBorder"));

        frame.setIconImage(icon.getImage());
        frame.setSize(gameWidth, gameHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game.gui);
        game.gui.requestFocus();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        maze.pacman.move();
        maze.pacman.eatItem();
        maze.blinky.moveBehaviour();
        maze.pinky.moveBehaviour();
        maze.inky.moveBehaviour();
        maze.clyde.moveBehaviour();

        gui.updateHighScore();
        gui.repaint();
    }
}
