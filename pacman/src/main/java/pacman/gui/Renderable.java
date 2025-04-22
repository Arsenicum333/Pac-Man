package pacman.gui;

import java.awt.*;

public interface Renderable {
    void render(Graphics g, Component component, int offsetX, int offsetY, int windowWidth, int windowHeight);
}
