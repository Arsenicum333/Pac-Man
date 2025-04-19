package pacman.states;

import pacman.Game;
import pacman.Maze;
import pacman.helpers.ScoreManager;
import pacman.interfaces.GameState;

public class GameOverState implements GameState {
    @Override
    public void handleGameLoop(Game game) {}
    @Override
    public void startGame(Game game) {}

    @Override
    public void restartGame(Game game) {
        Maze maze = Maze.getInstance();
        maze.generateMaze();
        ScoreManager.getInstance().setScore(0);
        game.setState(new NotStartedState());
        game.getGameLoop().start();
    }

    @Override
    public void togglePause(Game game) {}
}
