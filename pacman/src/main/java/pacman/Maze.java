package pacman;

import pacman.helpers.loaders.ImageLoader;
import pacman.helpers.managers.FruitManager;
import pacman.helpers.managers.ItemManager;
import pacman.objects.GameObject;
import pacman.objects.entities.PacMan;
import pacman.objects.entities.ghosts.Blinky;
import pacman.objects.entities.ghosts.Clyde;
import pacman.objects.entities.ghosts.Ghostable;
import pacman.objects.entities.ghosts.Inky;
import pacman.objects.entities.ghosts.Pinky;
import pacman.objects.items.*;
import pacman.objects.items.powerups.PowerPellet;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Maze {
    private static final Maze instance = new Maze();
    private static final int rowCount = 21;
    private static final int columnCount = 19;
    private static final int tileSize = 32;

    HashSet<GameObject> walls;
    HashSet<GameObject> gates;
    HashSet<GameObject> dots;

    private List<Fruit> collectedFruits;
    private FruitManager fruitManager;
    private ItemManager itemManager;

    private PacMan pacman;
    private Blinky blinky;
    private Pinky pinky;
    private Inky inky;
    private Clyde clyde;

    //X = Wall, G = Gate, ' ' = Dot, O = Empty, P = Pac-Man
    //b = Blinky (Red), p = Pinky (Pink), i = Inky (Cyan), c = Clyde (Orange)
    //I = Random Item (Heart, Speed Boost or Shield), E = Energizer (Power Pellet)
    //F = Random Fruit (Cherry, Strawberry, Orange, Apple or Melon), B = Bomb
    private String[] tileMap = {
        "XXXXXXXXXXXXXXXXXXX",
        "X        X        X",
        "XEXX XXX X XXX XXEX",
        "X        I        X",
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
        "X        B        X",
        "XXXXXXXXXXXXXXXXXXX"
    };

    private Maze() {
        collectedFruits = new ArrayList<>();
        fruitManager = FruitManager.getInstance();
        itemManager = ItemManager.getInstance();
        generateMaze();
    }

    public void generateMaze() {
        walls = new HashSet<>();
        gates = new HashSet<>();
        dots = new HashSet<>();

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < columnCount; c++) {
                String row = tileMap[r];
                char tileMapChar = row.charAt(c);
                int x = c * tileSize;
                int y = r * tileSize;

                if (tileMapChar == 'F') {
                    fruitManager.setSpawnCoordinates(x + 3, y + 3);
                    fruitManager.startFruitCycle();
                } else if (tileMapChar == 'I') {
                    itemManager.setSpawnCoordinates(x, y);
                    itemManager.spawnRandomItem();
                } else {
                    placeObject(tileMapChar, x, y);
                }
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
            case 'E' -> itemManager.getItems().add(new PowerPellet(ImageLoader.getImage("PowerPellet"), x + 6, y + 6, tileSize - 12, tileSize - 12, 5000));
            case 'B' -> itemManager.getItems().add(new Bomb(ImageLoader.getImage("Bomb"), x, y, tileSize, tileSize));
            default -> {}
        }
    }

    public void newLevel() {
        if (dots.isEmpty()) {
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
    public HashSet<Item> getItems() {return itemManager.getItems();}
    public Fruit getCurrentFruit() {return fruitManager.getCurrentFruit();}
    public List<Fruit> getCollectedFruits() {return collectedFruits;}
    public static Maze getInstance() {return instance;}

    public void setCurrentFruit(Fruit fruit) {fruitManager.setCurrentFruit(fruit);}
    public void addCollectedFruit(Fruit fruit) {this.collectedFruits.add(fruit);}
}
