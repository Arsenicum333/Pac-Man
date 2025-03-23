import java.util.HashSet;

public class Maze {
    private static final int rowCount = 21;
    private static final int columnCount = 19;
    private static final int tileSize = 32;
    private static Maze instance;

    HashSet<GameObject> walls;
    HashSet<GameObject> dots;
    HashSet<GameObject> powerPellets;
    HashSet<GameObject> ghosts;
    PacMan pacman;

    //X = Wall, O = Empty, P = Pac-Man, ' ' = Dot, E = Energizer (Power Pellet)
    //Ghosts: b = Blinky (Red), p = Pinky (Pink), i = Inky (Cyan), c = Clyde (Orange)
    private String[] tileMap = {
        "XXXXXXXXXXXXXXXXXXX",
        "X        X        X",
        "XEXX XXX X XXX XXEX",
        "X                 X",
        "X XX X XXXXX X XX X",
        "X    X   X   X    X",
        "XXXX XXX X XXX XXXX",
        "OOOX X       X XOOO",
        "XXXX X XOb0X X XXXX",
        "X      XipcX      X",
        "XXXX X XXXXX X XXXX",
        "OOOX X       X XOOO",
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

    Maze() {
        generateMaze();
    }

    public void generateMaze() {
        walls = new HashSet<>();
        dots = new HashSet<>();
        powerPellets = new HashSet<>();
        ghosts = new HashSet<>();

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
        if (tileMapChar == 'X') {
            GameObject wall = new GameObject(ImageLoader.getImage("Wall"), x, y, tileSize, tileSize);
            walls.add(wall);
        } else if (tileMapChar == 'P') {
            pacman = new PacMan(ImageLoader.getImage("PacManLeft"), x, y, tileSize, tileSize);
        } else if (tileMapChar == 'b') {
            GameObject blinky = new GameObject(ImageLoader.getImage("BlinkyUp"), x, y, tileSize, tileSize);
            ghosts.add(blinky);
        } else if (tileMapChar == 'p') {
            GameObject pinky = new GameObject(ImageLoader.getImage("PinkyUp"), x, y, tileSize, tileSize);
            ghosts.add(pinky);
        } else if (tileMapChar == 'i') {
            GameObject inky = new GameObject(ImageLoader.getImage("InkyUp"), x, y, tileSize, tileSize);
            ghosts.add(inky);
        } else if (tileMapChar == 'c') {
            GameObject clyde = new GameObject(ImageLoader.getImage("ClydeUp"), x, y, tileSize, tileSize);
            ghosts.add(clyde);
        } else if (tileMapChar == ' ') {
            GameObject dot = new GameObject(ImageLoader.getImage("Dot"), x, y, tileSize, tileSize);
            dots.add(dot);
        } else if (tileMapChar == 'E') {
            GameObject powerPellet = new GameObject(ImageLoader.getImage("PowerPellet"), x + 6, y + 6, tileSize - 12, tileSize - 12);
            powerPellets.add(powerPellet);
        }
    }

    public boolean checkCollision(GameObject a, GameObject b) {
        return a.getX() < b.getX() + b.getWidth() &&
               a.getX() + a.getWidth() > b.getX() &&
               a.getY() < b.getY() + b.getHeight() &&
               a.getY() + a.getHeight() > b.getY();
    }

    public static int getRowCount() {return rowCount;}
    public static int getColumnCount() {return columnCount;}
    public static int getTileSize() {return tileSize;}
    public static Maze getInstance() {
        if (instance == null)
            instance = new Maze();
        return instance;
    }
}
