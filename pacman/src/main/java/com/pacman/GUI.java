package com.pacman;

import com.pacman.helpers.GameObject;
import com.pacman.helpers.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class GUI extends JPanel {
    private static final GUI instance = new GUI();
    private Maze maze;

    private GUI() {
        maze = Maze.getInstance();
        setBackground(Color.BLACK);
        setFocusable(true);
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

        for (GameObject wall : maze.getWalls()) {
            g.drawImage(wall.getImage(), offsetX + wall.getX(), offsetY + wall.getY(),
                        Maze.getTileSize(), Maze.getTileSize(), this);
        }

        for (GameObject gate : maze.getGates()) {
            g.drawImage(gate.getImage(), offsetX + gate.getX(), offsetY + gate.getY(),
                        Maze.getTileSize(), Maze.getTileSize(), this);
        }

        for (GameObject dot : maze.getDots()) {
            g.drawImage(dot.getImage(), offsetX + dot.getX(), offsetY + dot.getY(),
                        Maze.getTileSize(), Maze.getTileSize(), this);
        }

        for (GameObject powerPellet : maze.getPowerPellets()) {
            g.drawImage(powerPellet.getImage(), offsetX + powerPellet.getX(), offsetY + powerPellet.getY(),
                        Maze.getTileSize() - 10, Maze.getTileSize() - 12, this);
        }

        g.drawImage(maze.getPacman().getImage(), offsetX + maze.getPacman().getX(), offsetY + maze.getPacman().getY(),
                    Maze.getTileSize(), Maze.getTileSize(), this);

        g.drawImage(maze.getBlinky().getImage(), offsetX + maze.getBlinky().getX(), offsetY + maze.getBlinky().getY(),
                    Maze.getTileSize(), Maze.getTileSize(), this);

        g.drawImage(maze.getPinky().getImage(), offsetX + maze.getPinky().getX(), offsetY + maze.getPinky().getY(),
                    Maze.getTileSize(), Maze.getTileSize(), this);

        g.drawImage(maze.getInky().getImage(), offsetX + maze.getInky().getX(), offsetY + maze.getInky().getY(),
                    Maze.getTileSize(), Maze.getTileSize(), this);

        g.drawImage(maze.getClyde().getImage(), offsetX + maze.getClyde().getX(), offsetY + maze.getClyde().getY(),
                    Maze.getTileSize(), Maze.getTileSize(), this);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));

        String scoreText = "Score: " + maze.getScore();
        String highScoreText = "High Score: " + maze.getHighScore();
        int scoreTextWidth = g.getFontMetrics().stringWidth(scoreText);
        int highScoreTextWidth = g.getFontMetrics().stringWidth(highScoreText);
        int centerX = panelWidth / 2;
        int scoreX = centerX - scoreTextWidth / 2 - 180;
        int highScoreX = centerX - highScoreTextWidth / 2 + 150;
        int textY = 50;

        g.drawString(scoreText, scoreX, textY);
        g.drawString(highScoreText, highScoreX, textY);

        int lives = maze.getPacman().getLives();
        int lifeIconSize = 48;
        int lifeIconSpacing = 10;
        int livesY = offsetY + mazeHeight + 10;
        int livesX = offsetX;

        for (int i = 0; i < lives; i++) {
            g.drawImage(ImageLoader.getImage("PacManIcon"), livesX + i * (lifeIconSize + lifeIconSpacing), livesY,
                        lifeIconSize, lifeIconSize, this);
        }
    }

    public static GUI getInstance() {return instance;}
}
