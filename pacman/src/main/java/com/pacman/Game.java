package com.pacman;

import com.pacman.helpers.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel implements ActionListener, KeyListener {
    private Maze maze;
    private Timer gameLoop;
    private static final int WINDOW_OFFSET_X = 16;
    private static final int WINDOW_OFFSET_Y = 160;

    Game(int gameWidth, int gameHeight) {
        setPreferredSize(new Dimension(gameWidth, gameHeight));
        setBackground(Color.BLACK);
        maze = Maze.getInstance();

        gameLoop = new Timer(13, this);
        gameLoop.start();

        addKeyListener(this);
        setFocusable(true);
    }
    public static void main(String[] args) throws Exception {
        int gameWidth = Maze.getColumnCount() * Maze.getTileSize() + WINDOW_OFFSET_X;
        int gameHeight = Maze.getRowCount() * Maze.getTileSize() + WINDOW_OFFSET_Y;

        Game game = new Game(gameWidth, gameHeight);
        JFrame frame = new JFrame("Pac-Man");

        frame.setSize(gameWidth, gameHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        game.requestFocus();
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int mazeWidth = Maze.getColumnCount() * Maze.getTileSize();
        int mazeHeight = Maze.getRowCount() * Maze.getTileSize();
        int offsetX = (panelWidth - mazeWidth) / 2;
        int offsetY = (panelHeight - mazeHeight) / 2;

        for (GameObject wall : maze.walls) {
            g.drawImage(wall.getImage(), offsetX + wall.getX(), offsetY + wall.getY(),
                        Maze.getTileSize(), Maze.getTileSize(), this);
        }

        for (GameObject dot : maze.dots) {
            g.drawImage(dot.getImage(), offsetX + dot.getX(), offsetY + dot.getY(),
                        Maze.getTileSize(), Maze.getTileSize(), this);
        }

        for (GameObject powerPellet : maze.powerPellets) {
            g.drawImage(powerPellet.getImage(), offsetX + powerPellet.getX(), offsetY + powerPellet.getY(),
                        Maze.getTileSize() - 10, Maze.getTileSize() - 12, this);
        }

        g.drawImage(maze.pacman.getImage(), offsetX + maze.pacman.getX(), offsetY + maze.pacman.getY(),
                    Maze.getTileSize(), Maze.getTileSize(), this);

        g.drawImage(maze.blinky.getImage(), offsetX + maze.blinky.getX(), offsetY + maze.blinky.getY(),
                    Maze.getTileSize(), Maze.getTileSize(), this);

        g.drawImage(maze.pinky.getImage(), offsetX + maze.pinky.getX(), offsetY + maze.pinky.getY(),
                    Maze.getTileSize(), Maze.getTileSize(), this);

        g.drawImage(maze.inky.getImage(), offsetX + maze.inky.getX(), offsetY + maze.inky.getY(),
                    Maze.getTileSize(), Maze.getTileSize(), this);

        g.drawImage(maze.clyde.getImage(), offsetX + maze.clyde.getX(), offsetY + maze.clyde.getY(),
                    Maze.getTileSize(), Maze.getTileSize(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        maze.pacman.move();
        maze.pacman.eatItem();
        maze.blinky.moveBehaviour();
        maze.pinky.moveBehaviour();
        maze.inky.moveBehaviour();
        maze.clyde.moveBehaviour();

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            maze.pacman.setNewDirection("LEFT");
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            maze.pacman.setNewDirection("RIGHT");
        else if (e.getKeyCode() == KeyEvent.VK_UP)
            maze.pacman.setNewDirection("UP");
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            maze.pacman.setNewDirection("DOWN");

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
