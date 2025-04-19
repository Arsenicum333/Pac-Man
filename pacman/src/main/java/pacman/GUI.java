package pacman;

import pacman.helpers.GameObject;
import pacman.helpers.ScoreManager;
import pacman.helpers.loaders.FontLoader;
import pacman.helpers.loaders.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class GUI extends JPanel {
    private static final GUI instance = new GUI();
    private Maze maze;
    private Game game;
    private Image pauseIcon;
    private Image playIcon;
    private Rectangle pauseButtonBounds;

    private GUI() {
        maze = Maze.getInstance();

        setBackground(Color.BLACK);
        setFocusable(true);

        pauseIcon = ImageLoader.getImage("Pause");
        playIcon = ImageLoader.getImage("Play");
        pauseButtonBounds = new Rectangle(0, 0, Maze.getTileSize(), Maze.getTileSize());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int windowWidth = getWidth();
        int windowHeight = getHeight();
        int mazeWidth = Maze.getColumnCount() * Maze.getTileSize();
        int mazeHeight = Maze.getRowCount() * Maze.getTileSize();
        int offsetX = (windowWidth - mazeWidth) / 2;
        int offsetY = (windowHeight - mazeHeight) / 2;

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
        g.setFont(FontLoader.getJoystixMonospaceFont(24f));

        String scoreLabel = "SCORE";
        String scoreValue = String.valueOf(ScoreManager.getInstance().getScore());
        String highScoreLabel = "HIGH SCORE";
        String highScoreValue = String.valueOf(ScoreManager.getInstance().getHighScore());
        FontMetrics metrics = g.getFontMetrics();

        int scoreLabelWidth = metrics.stringWidth(scoreLabel);
        int scoreValueWidth = metrics.stringWidth(scoreValue);
        int highScoreLabelWidth = metrics.stringWidth(highScoreLabel);
        int highScoreValueWidth = metrics.stringWidth(highScoreValue);
        int centerX = windowWidth / 2;
        int scoreX = centerX - scoreLabelWidth / 2 - 180;
        int highScoreX = centerX - highScoreLabelWidth / 2 + 50;
        int labelY = 30;
        int valueY = labelY + metrics.getHeight() -5;

        g.drawString(scoreLabel, scoreX, labelY);
        g.drawString(scoreValue, scoreX + (scoreLabelWidth - scoreValueWidth) / 2, valueY);
        g.drawString(highScoreLabel, highScoreX, labelY);
        g.drawString(highScoreValue, highScoreX + (highScoreLabelWidth - highScoreValueWidth) / 2, valueY);

        int pauseX = highScoreX + highScoreLabelWidth / 2 + 200;
        int pauseY = labelY - Maze.getTileSize() / 2;
        pauseButtonBounds.setBounds(pauseX, pauseY, Maze.getTileSize(), Maze.getTileSize());

        Image currentIcon = (game != null && game.isPaused()) ? playIcon : pauseIcon;

        g.drawImage(currentIcon, pauseButtonBounds.x, pauseButtonBounds.y,
                    pauseButtonBounds.width, pauseButtonBounds.height, this);

        if (game != null) {
            int lives = maze.getPacman().getLives();
            int lifeIconSize = 48;
            int lifeIconSpacing = 10;
            int livesY = offsetY + mazeHeight + 10;
            int livesX = offsetX;

            for (int i = 0; i < lives; i++) {
                g.drawImage(ImageLoader.getImage("PacManIcon"), livesX + i * (lifeIconSize + lifeIconSpacing), livesY,
                            lifeIconSize, lifeIconSize, this);
            }

            if (!game.isGameOn() || game.isPaused() || game.isGameOver()) {
                g.setColor(new Color(0, 0, 0, 0.5f));
                g.fillRect(0, 0, windowWidth, windowHeight);
            }

            if (!game.isGameOn()) {
                g.setFont(FontLoader.getJoystixMonospaceFont(22f));
                g.setColor(Color.YELLOW);

                String startText = "PRESS ARROW KEY OR WASD TO START";
                int startWidth = g.getFontMetrics().stringWidth(startText);

                g.drawString(startText, centerX - startWidth / 2, windowHeight / 2 + 8);
            } else if (game.isPaused()) {
                g.setFont(FontLoader.getJoystixMonospaceFont(60f));
                g.setColor(Color.YELLOW);

                String pauseText = "PAUSED";
                int pauseWidth = g.getFontMetrics().stringWidth(pauseText);

                g.drawString(pauseText, centerX - pauseWidth / 2, windowHeight / 2 + 5);
            } else if (game.isGameOver()) {
                g.setFont(FontLoader.getJoystixMonospaceFont(60f));
                g.setColor(Color.RED);

                String gameOverText = "GAME OVER";
                int gameOverWidth = g.getFontMetrics().stringWidth(gameOverText);

                g.drawString(gameOverText, centerX - gameOverWidth / 2, windowHeight / 2 + 5);

                g.setFont(FontLoader.getJoystixMonospaceFont(22f));
                g.setColor(Color.WHITE);

                String restartText = "PRESS SPACE TO RESTART";
                int restartWidth = g.getFontMetrics().stringWidth(restartText);

                g.drawString(restartText, centerX - restartWidth / 2, windowHeight / 2 + 72);
            }
        }
    }

    public Rectangle getPauseButtonBounds() {return pauseButtonBounds;}
    public static GUI getInstance() {return instance;}

    public void setGame(Game game) {this.game = game;}
}
