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
    private boolean isGameOver = false;
    private boolean isGameOn = false;
    private static final int WINDOW_OFFSET_X = 16;
    private static final int WINDOW_OFFSET_Y = 160;

    Game(int gameWidth, int gameHeight) {
        maze = Maze.getInstance();
        gui = GUI.getInstance();

        gui.setPreferredSize(new Dimension(gameWidth, gameHeight));
        gui.addKeyListener(new KeyHandler(maze, this));

        gameLoop = new Timer(13, this);
        gameLoop.start();
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

    public void restartGame() {
        if (isGameOver) {
            maze.generateMaze();
            maze.setScore(0);
            isGameOver = false;
            isGameOn = false;
            gameLoop.start();
        }
    }

    public void endGame() {
        if (maze.getPacman().getLives() <= 0) {
            isGameOver = true;
            gameLoop.stop();
        }
    }

    public void newLevel() {
        if (maze.getDots().isEmpty() && maze.getPowerPellets().isEmpty()) {
            int currentLives = maze.getPacman().getLives();
            maze.generateMaze();
            maze.getPacman().setLives(currentLives);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isGameOver && isGameOn) {
            maze.getPacman().move();
            maze.getPacman().eatItem();
            maze.getPacman().loseLife();
            maze.getBlinky().moveBehaviour();
            maze.getPinky().moveBehaviour();
            maze.getInky().moveBehaviour();
            maze.getClyde().moveBehaviour();
            maze.updateHighScore();
        }

        gui.repaint();

        newLevel();
        endGame();
    }

    public boolean isGameOver() {return isGameOver;}
    public boolean isGameOn() {return isGameOn;}

    public void setGameOver(boolean isGameOver) {this.isGameOver = isGameOver;}
    public void setGameOn(boolean isGameOn) {this.isGameOn = isGameOn;}
}
