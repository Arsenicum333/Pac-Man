package pacman.handlers;

import pacman.Game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
    private final Game game;
    private final Rectangle pauseButtonBounds;

    public MouseHandler(Game game, Rectangle pauseButtonBounds) {
        this.game = game;
        this.pauseButtonBounds = pauseButtonBounds;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (game != null && pauseButtonBounds.contains(e.getPoint()))
            game.togglePause();
    }
}
