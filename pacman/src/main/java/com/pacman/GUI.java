package com.pacman;

import com.pacman.helpers.GameObject;

import javax.swing.*;
import java.awt.*;

public class GUI extends JPanel {
    private static final GUI instance = new GUI();
    private Maze maze;
    private int score = 0;
    private int highScore = 0;

    private GUI() {
        maze = Maze.getInstance();
        setBackground(Color.BLACK);
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

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));

        String scoreText = "Score: " + score;
        String highScoreText = "High Score: " + highScore;
        int scoreTextWidth = g.getFontMetrics().stringWidth(scoreText);
        int highScoreTextWidth = g.getFontMetrics().stringWidth(highScoreText);
        int centerX = panelWidth / 2;
        int scoreX = centerX - scoreTextWidth / 2 - 180;
        int highScoreX = centerX - highScoreTextWidth / 2 + 150;
        int textY = 50;

        g.drawString(scoreText, scoreX, textY);
        g.drawString(highScoreText, highScoreX, textY);
    }

    public void updateHighScore() {
        if (getHighScore() < getScore())
            setHighScore(score);
    }

    public int getScore() {return score;}
    public int getHighScore() {return highScore;}
    public static GUI getInstance() {return instance;}

    public void setScore(int score) {this.score = score;}
    public void setHighScore(int highScore) {this.highScore = highScore;}
}
