package pacman.states;

import pacman.Game;
import pacman.Maze;
import pacman.managers.ScoreManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameOverState implements GameState {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameOverState.class);

    @Override
    public void handleGameLoop(Game game) {}
    @Override
    public void startGame(Game game) {}

    @Override
    public void restartGame(Game game) {
        try {
            Maze maze = Maze.getInstance();
            ScoreManager scoreManager = ScoreManager.getInstance();

            maze.getCollectedFruits().clear();
            maze.getItems().clear();
            maze.generateMaze();
            scoreManager.setScore(0);
            game.setState(new NotStartedState());
            game.getGameLoop().start();
            LOGGER.info("Game restarted from GameOver state");
        } catch (Exception e) {
            LOGGER.error("Failed to restart game from GameOver state", e);
            throw new RuntimeException("Game restart failed", e);
        }
    }

    @Override
    public void togglePause(Game game) {}
}
