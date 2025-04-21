package pacman.states;

import pacman.Game;
import pacman.Maze;
import pacman.helpers.managers.ScoreManager;
import pacman.interfaces.GameState;
import pacman.interfaces.Ghostable;

public class PlayingState implements GameState {
    @Override
    public void handleGameLoop(Game game) {
        Maze maze = Maze.getInstance();
        maze.getPacman().move();
        maze.getPacman().eatItem();
        maze.getPacman().loseLife();
        maze.getGhosts().forEach(Ghostable::moveBehaviour);
        ScoreManager.getInstance().updateHighScore();
        maze.newLevel();
        game.checkGameOver();
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
