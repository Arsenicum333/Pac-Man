package pacman.gui;

import pacman.Game;
import pacman.Maze;
import pacman.helpers.loaders.FontLoader;
import pacman.helpers.managers.ScoreManager;

import java.awt.*;

public class Header implements Renderable {
    private final Game game;
    private final Image pauseIcon;
    private final Image playIcon;
    private final Rectangle pauseButtonBounds;

    public Header(Game game, Image pauseIcon, Image playIcon, Rectangle pauseButtonBounds) {
        this.game = game;
        this.pauseIcon = pauseIcon;
        this.playIcon = playIcon;
        this.pauseButtonBounds = pauseButtonBounds;
    }

    @Override
    public void render(Graphics g, Component component, int offsetX, int offsetY, int windowWidth, int windowHeight) {
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
        int valueY = labelY + metrics.getHeight() - 5;

        g.drawString(scoreLabel, scoreX, labelY);
        g.drawString(scoreValue, scoreX + (scoreLabelWidth - scoreValueWidth) / 2, valueY);
        g.drawString(highScoreLabel, highScoreX, labelY);
        g.drawString(highScoreValue, highScoreX + (highScoreLabelWidth - highScoreValueWidth) / 2, valueY);

        int pauseX = highScoreX + highScoreLabelWidth / 2 + 200;
        int pauseY = labelY - Maze.getTileSize() / 2;
        pauseButtonBounds.setBounds(pauseX, pauseY, Maze.getTileSize(), Maze.getTileSize());

        Image currentIcon = (game != null && game.isPaused()) ? playIcon : pauseIcon;

        g.drawImage(currentIcon, pauseButtonBounds.x, pauseButtonBounds.y,
                    pauseButtonBounds.width, pauseButtonBounds.height, component);
    }
}
