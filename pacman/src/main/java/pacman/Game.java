package pacman;

import pacman.helpers.handlers.KeyHandler;
import pacman.helpers.handlers.MouseHandler;
import pacman.helpers.handlers.WindowCloseHandler;
import pacman.helpers.loaders.ImageLoader;
import pacman.states.NotStartedState;
import pacman.states.PlayingState;
import pacman.states.PausedState;
import pacman.states.GameOverState;
import pacman.interfaces.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game implements ActionListener {
    private Maze maze;
    private GUI gui;
    private Timer gameLoop;
    private GameState state;
    private static final int WINDOW_OFFSET_X = 16;
    private static final int WINDOW_OFFSET_Y = 160;

    Game(int gameWidth, int gameHeight) {
        maze = Maze.getInstance();
        gui = GUI.getInstance();
        state = new NotStartedState();

        gui.setGame(this);
        gui.setPreferredSize(new Dimension(gameWidth, gameHeight));
        gui.addKeyListener(new KeyHandler(this));
        gui.addMouseListener(new MouseHandler(this, gui.getPauseButtonBounds()));

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
        frame.addWindowListener(new WindowCloseHandler());
        frame.setVisible(true);
        game.gui.requestFocusInWindow();
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
        state.handleGameLoop(this);
        gui.repaint();
    }

    public Timer getGameLoop() {return gameLoop;}
    public boolean isGameOver() {return state instanceof GameOverState;}
    public boolean isPlaying() {return state instanceof PlayingState;}
    public boolean isPaused() {return state instanceof PausedState;}

    public void setState(GameState state) {this.state = state;}
}
