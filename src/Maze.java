import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashSet;

public class Maze {
    private static final int rowCount = 21;
    private static final int columnCount = 19;
    private static final int tileSize = 32;

    private Image Wall;
    private Image Dot;
    private Image PowerPellet;

    private Image PacManDown;
    private Image PacManLeft;
    private Image PacManRight;
    private Image PacManUp;

    private Image BlinkyDown;
    private Image BlinkyLeft;
    private Image BlinkyRight;
    private Image BlinkyUp;

    private Image PinkyDown;
    private Image PinkyLeft;
    private Image PinkyRight;
    private Image PinkyUp;

    private Image InkyDown;
    private Image InkyLeft;
    private Image InkyRight;
    private Image InkyUp;

    private Image ClydeDown;
    private Image ClydeLeft;
    private Image ClydeRight;
    private Image ClydeUp;

    HashSet<GameObject> walls;
    HashSet<GameObject> dots;
    HashSet<GameObject> ghosts;
    GameObject pacman;

    //X = Wall, O = Empty, P = Pac-Man, ' ' = Dot
    //Ghosts: b = Blinky, p = Pinky, i = Inky, c = Clyde
    private String[] tileMap = {
        "XXXXXXXXXXXXXXXXXXX",
        "X        X        X",
        "X XX XXX X XXX XX X",
        "X                 X",
        "X XX X XXXXX X XX X",
        "X    X       X    X",
        "XXXX XXXX XXXX XXXX",
        "OOOX X       X XOOO",
        "XXXX X XXbXX X XXXX",
        "O       ipc       O",
        "XXXX X XXXXX X XXXX",
        "OOOX X       X XOOO",
        "XXXX X XXXXX X XXXX",
        "X        X        X",
        "X XX XXX X XXX XX X",
        "X  X     P     X  X",
        "XX X X XXXXX X X XX",
        "X    X   X   X    X",
        "X XXXXXX X XXXXXX X",
        "X                 X",
        "XXXXXXXXXXXXXXXXXXX"
    };

    Maze() {
        Wall = new ImageIcon(getClass().getResource("./img/Wall.png")).getImage();
        Dot = new ImageIcon(getClass().getResource("./img/Dot.png")).getImage();
        PowerPellet = new ImageIcon(getClass().getResource("./img/PowerPellet.gif")).getImage();

        PacManDown = new ImageIcon(getClass().getResource("./img/PacManDown.gif")).getImage();
        PacManLeft = new ImageIcon(getClass().getResource("./img/PacManLeft.gif")).getImage();
        PacManRight = new ImageIcon(getClass().getResource("./img/PacManRight.gif")).getImage();
        PacManUp = new ImageIcon(getClass().getResource("./img/PacManUp.gif")).getImage();

        BlinkyDown = new ImageIcon(getClass().getResource("./img/BlinkyDown.gif")).getImage();
        BlinkyLeft = new ImageIcon(getClass().getResource("./img/BlinkyLeft.gif")).getImage();
        BlinkyRight = new ImageIcon(getClass().getResource("./img/BlinkyRight.gif")).getImage();
        BlinkyUp = new ImageIcon(getClass().getResource("./img/BlinkyUp.gif")).getImage();

        PinkyDown = new ImageIcon(getClass().getResource("./img/PinkyDown.gif")).getImage();
        PinkyLeft = new ImageIcon(getClass().getResource("./img/PinkyLeft.gif")).getImage();
        PinkyRight = new ImageIcon(getClass().getResource("./img/PinkyRight.gif")).getImage();
        PinkyUp = new ImageIcon(getClass().getResource("./img/PinkyUp.gif")).getImage();

        InkyDown = new ImageIcon(getClass().getResource("./img/InkyDown.gif")).getImage();
        InkyLeft = new ImageIcon(getClass().getResource("./img/InkyLeft.gif")).getImage();
        InkyRight = new ImageIcon(getClass().getResource("./img/InkyRight.gif")).getImage();
        InkyUp = new ImageIcon(getClass().getResource("./img/InkyUp.gif")).getImage();

        ClydeDown = new ImageIcon(getClass().getResource("./img/ClydeDown.gif")).getImage();
        ClydeLeft = new ImageIcon(getClass().getResource("./img/ClydeLeft.gif")).getImage();
        ClydeRight = new ImageIcon(getClass().getResource("./img/ClydeRight.gif")).getImage();
        ClydeUp = new ImageIcon(getClass().getResource("./img/ClydeUp.gif")).getImage();

        generateMaze();
    }

    public void generateMaze() {
        walls = new HashSet<GameObject>();
        dots = new HashSet<GameObject>();
        ghosts = new HashSet<GameObject>();

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < columnCount; c++) {
                String row = tileMap[r];
                char tileMapChar = row.charAt(c);

                int x = c * tileSize;
                int y = r * tileSize;

                if (tileMapChar == 'X') {
                    GameObject wall = new GameObject(Wall, x, y, tileSize, tileSize);
                    walls.add(wall);
                }
                else if (tileMapChar == 'P') {
                    pacman = new GameObject(PacManLeft, x, y, tileSize, tileSize);
                }
                else if (tileMapChar == 'b') {
                    GameObject ghost = new GameObject(BlinkyUp, x, y, tileSize, tileSize);
                    ghosts.add(ghost);
                }
                else if (tileMapChar == 'p') {
                    GameObject ghost = new GameObject(PinkyUp, x, y, tileSize, tileSize);
                    ghosts.add(ghost);
                }
                else if (tileMapChar == 'i') {
                    GameObject ghost = new GameObject(InkyUp, x, y, tileSize, tileSize);
                    ghosts.add(ghost);
                }
                else if (tileMapChar == 'c') {
                    GameObject ghost = new GameObject(ClydeUp, x, y, tileSize, tileSize);
                    ghosts.add(ghost);
                }
                else if (tileMapChar == ' ') {
                    GameObject dot = new GameObject(Dot, x, y, tileSize, tileSize);
                    dots.add(dot);
                }
            }
        }
    }

    public static int getRowCount() {
        return rowCount;
    }

    public static int getColumnCount() {
        return columnCount;
    }

    public static int getTileSize() {
        return tileSize;
    }
}
