package pacman.states;

import pacman.Game;
import pacman.Maze;
import pacman.managers.ScoreManager;
import pacman.objects.entities.ghosts.Ghostable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayingState implements GameState {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayingState.class);

    @Override
    public void handleGameLoop(Game game) {
        try {
            Maze maze = Maze.getInstance();
            ScoreManager scoreManager = ScoreManager.getInstance();

            maze.getPacman().move();
            maze.getPacman().eatItem();
            maze.getPacman().eatGhost();
            maze.getPacman().loseLife();
            maze.getGhosts().forEach(Ghostable::moveBehaviour);
            maze.getGhosts().forEach(Ghostable::monitorVulnerability);
            scoreManager.updateHighScore();
            maze.newLevel();
            game.checkGameOver();
        } catch (Exception e) {
            LOGGER.error("Error during game loop in PlayingState", e);
        }
    }

    @Override
    public void startGame(Game game) {}
    @Override
    public void restartGame(Game game) {}

    @Override
    public void togglePause(Game game) {
        game.setState(new PausedState());
        game.getGameLoop().stop();
    }
}
