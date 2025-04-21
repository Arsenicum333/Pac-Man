package pacman;

import pacman.entities.PacMan;
import pacman.entities.ghosts.Blinky;
import pacman.entities.ghosts.Pinky;
import pacman.entities.ghosts.Inky;
import pacman.entities.ghosts.Clyde;
import pacman.helpers.GameObject;
import pacman.helpers.loaders.ImageLoader;
import pacman.interfaces.Ghostable;
import pacman.items.*;

import java.util.HashSet;
import java.util.List;

public class Maze {
    private static final Maze instance = new Maze();
    private static final int rowCount = 21;
    private static final int columnCount = 19;
    private static final int tileSize = 32;

    HashSet<GameObject> walls;
    HashSet<GameObject> gates;
    HashSet<GameObject> dots;
    HashSet<GameObject> powerPellets;
    private PacMan pacman;
    private Blinky blinky;
    private Pinky pinky;
    private Inky inky;
    private Clyde clyde;

    //X = Wall, G = Gate O = Empty, P = Pac-Man, ' ' = Dot, E = Energizer (Power Pellet)
    //b = Blinky (Red), p = Pinky (Pink), i = Inky (Cyan), c = Clyde (Orange)
    //F = Fruit
    private String[] tileMap = {
        "XXXXXXXXXXXXXXXXXXX",
        "X        X        X",
        "XEXX XXX X XXX XXEX",
        "X                 X",
        "X XX X XXXXX X XX X",
        "X    X   X   X    X",
        "XXXX XXX X XXX XXXX",
        "OOOX X   b   X XOOO",
        "XXXX X XGGGX X XXXX",
        "       XipcX       ",
        "XXXX X XXXXX X XXXX",
        "OOOX X   F   X XOOO",
        "XXXX X XXXXX X XXXX",
        "X        X        X",
        "XEXX XXX X XXX XXEX",
        "X  X     P     X  X",
        "XX X X XXXXX X X XX",
        "X    X   X   X    X",
        "X XXXXXX X XXXXXX X",
        "X                 X",
        "XXXXXXXXXXXXXXXXXXX"
    };

    private Maze() {generateMaze();}

    public void generateMaze() {
        walls = new HashSet<>();
        gates = new HashSet<>();
        dots = new HashSet<>();
        powerPellets = new HashSet<>();

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < columnCount; c++) {
                String row = tileMap[r];
                char tileMapChar = row.charAt(c);
                int x = c * tileSize;
                int y = r * tileSize;

                placeObject(tileMapChar, x, y);
            }
        }
    }

    public void placeObject(char tileMapChar, int x, int y) {
        switch (tileMapChar) {
            case 'X' -> walls.add(new GameObject(ImageLoader.getImage("Wall"), x, y, tileSize, tileSize));
            case 'G' -> gates.add(new GameObject(ImageLoader.getImage("Gate"), x, y, tileSize, tileSize));
            case 'P' -> pacman = new PacMan(ImageLoader.getImage("PacManLeft"), x, y, tileSize, tileSize, tileSize / 8);
            case 'b' -> blinky = new Blinky(ImageLoader.getImage("BlinkyUp"), x, y, tileSize, tileSize, tileSize / 11);
            case 'p' -> pinky = new Pinky(ImageLoader.getImage("PinkyUp"), x, y, tileSize, tileSize, tileSize / 11);
            case 'i' -> inky = new Inky(ImageLoader.getImage("InkyUp"), x, y, tileSize, tileSize, tileSize / 11);
            case 'c' -> clyde = new Clyde(ImageLoader.getImage("ClydeUp"), x, y, tileSize, tileSize, tileSize / 11);
            case ' ' -> dots.add(new GameObject(ImageLoader.getImage("Dot"), x, y, tileSize, tileSize));
            case 'E' -> powerPellets.add(new GameObject(ImageLoader.getImage("PowerPellet"), x + 6, y + 6, tileSize - 12, tileSize - 12));
            default -> {}
        }
    }

    public void newLevel() {
        if (dots.isEmpty() && powerPellets.isEmpty()) {
            int currentLives = pacman.getLives();
            generateMaze();
            pacman.setLives(currentLives);
        }
    }

    public static int getRowCount() {return rowCount;}
    public static int getColumnCount() {return columnCount;}
    public static int getTileSize() {return tileSize;}
    public PacMan getPacman() {return pacman;}
    public Blinky getBlinky() {return blinky;}
    public Pinky getPinky() {return pinky;}
    public Inky getInky() {return inky;}
    public Clyde getClyde() {return clyde;}
    public List<Ghostable> getGhosts() {return List.of(blinky, pinky, inky, clyde);}
    public HashSet<GameObject> getWalls() {return walls;}
    public HashSet<GameObject> getGates() {return gates;}
    public HashSet<GameObject> getDots() {return dots;}
    public HashSet<GameObject> getPowerPellets() {return powerPellets;}
    public static Maze getInstance() {return instance;}
}
