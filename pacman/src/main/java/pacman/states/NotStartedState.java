package pacman.states;

import pacman.Game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotStartedState implements GameState {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotStartedState.class);

    @Override
    public void handleGameLoop(Game game) {}

    @Override
    public void startGame(Game game) {
        try {
            game.setState(new PlayingState());
            LOGGER.info("Game started from NotStarted state");
        } catch (Exception e) {
            LOGGER.error("Failed to start game from NotStartedState", e);
            throw new RuntimeException("Game start failed", e);
        }
    }

    @Override
    public void restartGame(Game game) {}
    @Override
    public void togglePause(Game game) {}
}
