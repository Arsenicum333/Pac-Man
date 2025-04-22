package pacman.gui;

import pacman.Game;
import pacman.Maze;
import pacman.helpers.loaders.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class MainGUI extends JPanel {
    private static final MainGUI instance = new MainGUI();
    private Maze maze;
    private Game game;
    private Image pauseIcon;
    private Image playIcon;
    private Rectangle pauseButtonBounds;
    private List<Renderable> renderables;

    private MainGUI() {
        maze = Maze.getInstance();

        setBackground(Color.BLACK);
        setFocusable(true);

        pauseIcon = ImageLoader.getImage("Pause");
        playIcon = ImageLoader.getImage("Play");
        pauseButtonBounds = new Rectangle(0, 0, Maze.getTileSize(), Maze.getTileSize());

        renderables = new ArrayList<>();
        renderables.add(new MazeGUI(maze));
        renderables.add(new Header(game, pauseIcon, playIcon, pauseButtonBounds));
        renderables.add(new Footer(maze));
        renderables.add(new GameStateGUI(game));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int windowWidth = getWidth();
        int windowHeight = getHeight();
        int mazeWidth = Maze.getColumnCount() * Maze.getTileSize();
        int mazeHeight = Maze.getRowCount() * Maze.getTileSize();
        int offsetX = (windowWidth - mazeWidth) / 2;
        int offsetY = (windowHeight - mazeHeight) / 2;

        for (Renderable renderable : renderables)
            renderable.render(g, this, offsetX, offsetY, windowWidth, windowHeight);
    }

    public Rectangle getPauseButtonBounds() {return pauseButtonBounds;}
    public static MainGUI getInstance() {return instance;}

    public void setGame(Game game) {
        this.game = game;
        renderables = new ArrayList<>();
        renderables.add(new MazeGUI(maze));
        renderables.add(new Header(game, pauseIcon, playIcon, pauseButtonBounds));
        renderables.add(new Footer(maze));
        renderables.add(new GameStateGUI(game));
    }
}
