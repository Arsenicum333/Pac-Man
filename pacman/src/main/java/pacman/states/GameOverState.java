package pacman.states;

import pacman.Game;
import pacman.Maze;
import pacman.managers.ScoreManager;

public class GameOverState implements GameState {
    @Override
    public void handleGameLoop(Game game) {}
    @Override
    public void startGame(Game game) {}

    @Override
    public void restartGame(Game game) {
        Maze maze = Maze.getInstance();
        ScoreManager scoreManager = ScoreManager.getInstance();

        maze.getCollectedFruits().clear();
        maze.getItems().clear();
        maze.generateMaze();
        scoreManager.setScore(0);
        game.setState(new NotStartedState());
        game.getGameLoop().start();
    }

    @Override
    public void togglePause(Game game) {}
}
