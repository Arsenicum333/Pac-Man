package pacman.states;

import pacman.Game;
import pacman.Maze;
import pacman.helpers.managers.FruitManager;
import pacman.helpers.managers.ScoreManager;
import pacman.interfaces.GameState;

public class GameOverState implements GameState {
    @Override
    public void handleGameLoop(Game game) {}
    @Override
    public void startGame(Game game) {}

    @Override
    public void restartGame(Game game) {
        Maze maze = Maze.getInstance();
        FruitManager fruitManager = FruitManager.getInstance();
        maze.getCollectedFruits().clear();
        maze.generateMaze();
        fruitManager.startFruitCycle();
        ScoreManager.getInstance().setScore(0);
        game.setState(new NotStartedState());
        game.getGameLoop().start();
    }

    @Override
    public void togglePause(Game game) {}
}
