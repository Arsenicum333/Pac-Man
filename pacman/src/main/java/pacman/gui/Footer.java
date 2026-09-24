package pacman.gui;

import pacman.Maze;
import pacman.loaders.FontLoader;
import pacman.loaders.ImageLoader;
import pacman.objects.items.Fruit;

import java.awt.*;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

        g.setColor(Color.WHITE);
        g.setFont(FontLoader.getJoystixMonospaceFont(18f));

        g.drawImage(ImageLoader.getImage("PacManIcon"), livesX, livesY,
                    lifeIconSize, lifeIconSize, component);
        g.drawString("x" + lives, livesX + lifeIconSize + lifeIconSpacing, livesY + 30);

        int fruitIconSize = 40;
        int fruitIconSpacing = 5;
        int fruitY = livesY;
        List<Fruit> collectedFruits = maze.getCollectedFruits();

        Map<String, Fruit> representativeFruits = new LinkedHashMap<>();
        Map<String, Integer> fruitCounts = new LinkedHashMap<>();

            for (Fruit fruit : collectedFruits) {
                representativeFruits.putIfAbsent(fruit.getType(), fruit);
                fruitCounts.merge(fruit.getType(), 1, Integer::sum);
            }

        int rightEdge = offsetX + mazeWidth;

        List<String> sortedFruitTypes = fruitCounts.keySet().stream()
            .sorted(Comparator.comparingInt((String fruitType) ->
                representativeFruits.get(fruitType).getPoints()).reversed())
            .toList();

        for (String fruitType : sortedFruitTypes) {
                String fruitCount = "x" + fruitCounts.get(fruitType);
                int textWidth = g.getFontMetrics().stringWidth(fruitCount);
                int fruitX = rightEdge - textWidth - fruitIconSpacing - fruitIconSize;

                g.drawImage(representativeFruits.get(fruitType).getImage(), fruitX, fruitY,
                            fruitIconSize, fruitIconSize, component);
                g.drawString(fruitCount, fruitX + fruitIconSize + fruitIconSpacing, fruitY + 26);
                rightEdge = fruitX - fruitIconSpacing;
            }
    }
}
