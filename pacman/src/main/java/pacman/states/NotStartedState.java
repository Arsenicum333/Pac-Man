package pacman.states;

import pacman.Game;

public class NotStartedState implements GameState {
    @Override
    public void handleGameLoop(Game game) {}
    @Override
    public void startGame(Game game) {game.setState(new PlayingState());}
    @Override
    public void restartGame(Game game) {}
    @Override
    public void togglePause(Game game) {}
}
