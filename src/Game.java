import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game extends JPanel {
    private Maze maze;

    Game(int gameWidth, int gameHeight) {
        setPreferredSize(new Dimension(gameWidth, gameHeight));
        setBackground(Color.BLACK);
        maze = new Maze();
    }
    public static void main(String[] args) throws Exception {
        int gameWidth = (Maze.getColumnCount() * Maze.getTileSize()) + 16;
        int gameHeight = (Maze.getRowCount() * Maze.getTileSize()) + 160;

        Game game = new Game(gameWidth, gameHeight);

        JFrame frame = new JFrame("Pac-Man");
        frame.setSize(gameWidth, gameHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int mazeWidth = Maze.getColumnCount() * Maze.getTileSize();
        int mazeHeight = Maze.getRowCount() * Maze.getTileSize();

        int offsetX = (panelWidth - mazeWidth) / 2;
        int offsetY = (panelHeight - mazeHeight) / 2 + 16;

        for (GameObject wall : maze.walls) {
            g.drawImage(wall.getImage(), offsetX + wall.getX(), offsetY + wall.getY(),
                        Maze.getTileSize(), Maze.getTileSize(), this);
        }

        for (GameObject dot : maze.dots) {
            g.drawImage(dot.getImage(), offsetX + dot.getX(), offsetY + dot.getY(),
                        Maze.getTileSize(), Maze.getTileSize(), this);
        }

        for (GameObject ghost : maze.ghosts) {
            g.drawImage(ghost.getImage(), offsetX + ghost.getX(), offsetY + ghost.getY(),
                        Maze.getTileSize(), Maze.getTileSize(), this);
        }

        g.drawImage(maze.pacman.getImage(), offsetX + maze.pacman.getX(), offsetY + maze.pacman.getY(),
                    Maze.getTileSize(), Maze.getTileSize(), this);
    }
}
