package pacman.states;

import pacman.Game;
import pacman.interfaces.GameState;

public class PausedState implements GameState {
    @Override
    public void handleGameLoop(Game game) {}
    @Override
    public void startGame(Game game) {}
    @Override
    public void restartGame(Game game) {}

    @Override
    public void togglePause(Game game) {
        game.setState(new PlayingState());
        game.getGameLoop().start();
    }
}
