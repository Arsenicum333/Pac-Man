package pacman.gui;

import pacman.Maze;
import pacman.objects.GameObject;
import pacman.objects.entities.PacMan;
import pacman.objects.items.Item;
import pacman.objects.items.powerups.PowerPellet;

import java.awt.*;

public class MazeGUI implements Renderable {
    private final Maze maze;

    public MazeGUI(Maze maze) {this.maze = maze;}

    @Override
    public void render(Graphics g, Component component, int offsetX, int offsetY, int windowWidth, int windowHeight) {
        for (GameObject wall : maze.getWalls()) {
            g.drawImage(wall.getImage(), offsetX + wall.getX(), offsetY + wall.getY(),
                        Maze.getTileSize(), Maze.getTileSize(), component);
        }

        for (GameObject gate : maze.getGates()) {
            g.drawImage(gate.getImage(), offsetX + gate.getX(), offsetY + gate.getY(),
                        Maze.getTileSize(), Maze.getTileSize(), component);
        }

        for (GameObject dot : maze.getDots()) {
            g.drawImage(dot.getImage(), offsetX + dot.getX(), offsetY + dot.getY(),
                        Maze.getTileSize(), Maze.getTileSize(), component);
        }

        for (Item<PacMan> item : maze.getItems()) {
            if (!item.isCollected()) {
                int width = Maze.getTileSize();
                int height = Maze.getTileSize();

                if (item instanceof PowerPellet) {
                    width = Maze.getTileSize() - 12;
                    height = Maze.getTileSize() - 12;
                }

                g.drawImage(item.getImage(), offsetX + item.getX(), offsetY + item.getY(),
                            width, height, component);
            }
        }

        if (maze.getCurrentFruit() != null && !maze.getCurrentFruit().isCollected()) {
            Item<PacMan> fruit = maze.getCurrentFruit();

            g.drawImage(fruit.getImage(), offsetX + fruit.getX(), offsetY + fruit.getY(),
                        Maze.getTileSize() - 6, Maze.getTileSize() - 6, component);
        }

        g.drawImage(maze.getPacman().getImage(), offsetX + maze.getPacman().getX(), offsetY + maze.getPacman().getY(),
                    Maze.getTileSize(), Maze.getTileSize(), component);

        g.drawImage(maze.getBlinky().getImage(), offsetX + maze.getBlinky().getX(), offsetY + maze.getBlinky().getY(),
                    Maze.getTileSize(), Maze.getTileSize(), component);

        g.drawImage(maze.getPinky().getImage(), offsetX + maze.getPinky().getX(), offsetY + maze.getPinky().getY(),
                    Maze.getTileSize(), Maze.getTileSize(), component);

        g.drawImage(maze.getInky().getImage(), offsetX + maze.getInky().getX(), offsetY + maze.getInky().getY(),
                    Maze.getTileSize(), Maze.getTileSize(), component);

        g.drawImage(maze.getClyde().getImage(), offsetX + maze.getClyde().getX(), offsetY + maze.getClyde().getY(),
                    Maze.getTileSize(), Maze.getTileSize(), component);
    }
}
