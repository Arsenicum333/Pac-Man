package pacman;

import pacman.gui.MainGUI;
import pacman.handlers.*;
import pacman.loaders.ImageLoader;
import pacman.states.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Game implements ActionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);
    private Maze maze;
    private MainGUI gui;
    private Timer gameLoop;
    private GameState state;
    private static final int WINDOW_OFFSET_X = 16;
    private static final int WINDOW_OFFSET_Y = 160;

    Game(int gameWidth, int gameHeight) {
        try {
            maze = Maze.getInstance();
            gui = MainGUI.getInstance();
            state = new NotStartedState();

            gui.setGame(this);
            gui.setPreferredSize(new Dimension(gameWidth, gameHeight));
            gui.addKeyListener(new KeyHandler(this));
            gui.addMouseListener(new MouseHandler(this, gui.getPauseButtonBounds()));

            gameLoop = new Timer(13, this);
            gameLoop.start();
            LOGGER.info("Game initialized successfully");
        } catch (Exception e) {
            LOGGER.error("Failed to initialize game", e);
            throw new RuntimeException("Game initialization failed", e);
        }
    }

    public static void main(String[] args) {
        try {
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
            frame.addWindowListener(new WindowCloseHandler());
            frame.setVisible(true);
            game.gui.requestFocusInWindow();
        } catch (Exception e) {
            LOGGER.error("Error starting the game", e);
            JOptionPane.showMessageDialog(null, "Failed to start the game", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public void startGame() {state.startGame(this);}
    public void restartGame() {state.restartGame(this);}
    public void togglePause() {state.togglePause(this);}

    public void checkGameOver() {
        if (maze.getPacman().getLives() <= 0) {
            setState(new GameOverState());
            gameLoop.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            state.handleGameLoop(this);
            gui.repaint();
        } catch (Exception ex) {
            LOGGER.warn("Error during game loop execution", ex);
        }
    }

    public Timer getGameLoop() {return gameLoop;}
    public boolean isPlaying() {return state instanceof PlayingState;}
    public boolean isPaused() {return state instanceof PausedState;}
    public boolean isGameOver() {return state instanceof GameOverState;}

    public void setState(GameState state) {this.state = state;}
}
