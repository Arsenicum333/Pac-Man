package pacman.helpers.handlers;

import pacman.helpers.managers.ScoreManager;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowCloseHandler extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        ScoreManager.getInstance().saveHighScore();
        System.exit(0);
    }
}
