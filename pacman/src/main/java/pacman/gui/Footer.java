package pacman.gui;

import pacman.Maze;
import pacman.helpers.loaders.ImageLoader;
import pacman.objects.items.Fruit;

import java.awt.*;
import java.util.List;

public class Footer implements Renderable {
    private final Maze maze;

    public Footer(Maze maze) {this.maze = maze;}

    @Override
    public void render(Graphics g, Component component, int offsetX, int offsetY, int windowWidth, int windowHeight) {
        int mazeWidth = Maze.getColumnCount() * Maze.getTileSize();
        int mazeHeight = Maze.getRowCount() * Maze.getTileSize();

        int lives = maze.getPacman().getLives();
        int lifeIconSize = 48;
        int lifeIconSpacing = 5;
        int livesY = offsetY + mazeHeight + 10;
        int livesX = offsetX;

        for (int i = 0; i < lives; i++) {
            g.drawImage(ImageLoader.getImage("PacManIcon"), livesX + i * (lifeIconSize + lifeIconSpacing), livesY,
                        lifeIconSize, lifeIconSize, component);
        }

        int fruitIconSize = 40;
        int fruitIconSpacing = 5;
        int fruitY = livesY;
        List<Fruit> collectedFruits = maze.getCollectedFruits();

        for (int i = 0; i < collectedFruits.size(); i++) {
            Fruit fruit = collectedFruits.get(i);
            int fruitX = offsetX + mazeWidth - (i + 1) * (fruitIconSize + fruitIconSpacing);

            g.drawImage(fruit.getImage(), fruitX, fruitY, fruitIconSize, fruitIconSize, component);
        }
    }
}
