package pacman.states;

import pacman.Game;

public interface GameState {
    void handleGameLoop(Game game);
    void startGame(Game game);
    void restartGame(Game game);
    void togglePause(Game game);
}
