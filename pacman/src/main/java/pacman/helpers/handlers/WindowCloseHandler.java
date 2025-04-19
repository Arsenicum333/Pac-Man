package pacman.helpers.handlers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import pacman.helpers.ScoreManager;

public class WindowCloseHandler extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        ScoreManager.getInstance().saveHighScore();
        System.exit(0);
    }
}
