package pacman.gui;

import pacman.Game;
import pacman.helpers.loaders.FontLoader;

import java.awt.*;

public class GameStateGUI implements Renderable {
    private final Game game;

    public GameStateGUI(Game game) {this.game = game;}

    @Override
    public void render(Graphics g, Component component, int offsetX, int offsetY, int windowWidth, int windowHeight) {
        if (game == null) return;

        if (!game.isPlaying() || game.isPaused() || game.isGameOver()) {
            g.setColor(new Color(0, 0, 0, 0.5f));
            g.fillRect(0, 0, windowWidth, windowHeight);
        }

        if (game.isGameOver()) {
            g.setFont(FontLoader.getJoystixMonospaceFont(60f));
            g.setColor(Color.RED);

            String gameOverText = "GAME OVER";
            int gameOverWidth = g.getFontMetrics().stringWidth(gameOverText);

            g.drawString(gameOverText, windowWidth / 2 - gameOverWidth / 2, windowHeight / 2 + 5);

            g.setFont(FontLoader.getJoystixMonospaceFont(22f));
            g.setColor(Color.WHITE);

            String restartText = "PRESS SPACE TO RESTART";
            int restartWidth = g.getFontMetrics().stringWidth(restartText);

            g.drawString(restartText, windowWidth / 2 - restartWidth / 2, windowHeight / 2 + 72);
        } else if (game.isPaused()) {
            g.setFont(FontLoader.getJoystixMonospaceFont(60f));
            g.setColor(Color.YELLOW);

            String pauseText = "PAUSED";
            int pauseWidth = g.getFontMetrics().stringWidth(pauseText);

            g.drawString(pauseText, windowWidth / 2 - pauseWidth / 2, windowHeight / 2 + 5);
        } else if (!game.isPlaying()) {
            g.setFont(FontLoader.getJoystixMonospaceFont(22f));
            g.setColor(Color.YELLOW);

            String startText = "PRESS ARROW KEY OR WASD TO START";
            int startWidth = g.getFontMetrics().stringWidth(startText);

            g.drawString(startText, windowWidth / 2 - startWidth / 2, windowHeight / 2 + 8);
        }
    }
}
