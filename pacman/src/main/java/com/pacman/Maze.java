package com.pacman;

import com.pacman.entities.PacMan;
import com.pacman.entities.ghosts.Blinky;
import com.pacman.entities.ghosts.Clyde;
import com.pacman.entities.ghosts.Inky;
import com.pacman.entities.ghosts.Pinky;
import com.pacman.helpers.GameObject;
import com.pacman.helpers.ImageLoader;

import java.util.HashSet;

public class Maze {
    private static final Maze instance = new Maze();
    private static final int rowCount = 21;
    private static final int columnCount = 19;
    private static final int tileSize = 32;

    HashSet<GameObject> walls;
    HashSet<GameObject> gates;
    HashSet<GameObject> dots;
    HashSet<GameObject> powerPellets;
    protected PacMan pacman;
    protected Blinky blinky;
    protected Pinky pinky;
    protected Inky inky;
    protected Clyde clyde;

    //X = Wall, G = Gate O = Empty, P = Pac-Man, ' ' = Dot, E = Energizer (Power Pellet)
    //b = Blinky (Red), p = Pinky (Pink), i = Inky (Cyan), c = Clyde (Orange)
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
        if (tileMapChar == 'X') {
            GameObject wall = new GameObject(ImageLoader.getImage("Wall"), x, y, tileSize, tileSize);
            walls.add(wall);
        } else if (tileMapChar == 'G') {
            GameObject gate = new GameObject(ImageLoader.getImage("Gate"), x, y, tileSize, tileSize);
            gates.add(gate);
        } else if (tileMapChar == 'P') {
            pacman = new PacMan(ImageLoader.getImage("PacManLeft"), x, y, tileSize, tileSize, tileSize / 8);
        } else if (tileMapChar == 'b') {
            blinky = new Blinky(ImageLoader.getImage("BlinkyUp"), x, y, tileSize, tileSize, tileSize / 11);
        } else if (tileMapChar == 'p') {
            pinky = new Pinky(ImageLoader.getImage("PinkyUp"), x, y, tileSize, tileSize, tileSize / 11);
        } else if (tileMapChar == 'i') {
            inky = new Inky(ImageLoader.getImage("InkyUp"), x, y, tileSize, tileSize, tileSize / 11);
        } else if (tileMapChar == 'c') {
            clyde = new Clyde(ImageLoader.getImage("ClydeUp"), x, y, tileSize, tileSize, tileSize / 11);
        } else if (tileMapChar == ' ') {
            GameObject dot = new GameObject(ImageLoader.getImage("Dot"), x, y, tileSize, tileSize);
            dots.add(dot);
        } else if (tileMapChar == 'E') {
            GameObject powerPellet = new GameObject(ImageLoader.getImage("PowerPellet"), x + 6, y + 6, tileSize - 12, tileSize - 12);
            powerPellets.add(powerPellet);
        }
    }

    public static int getRowCount() {return rowCount;}
    public static int getColumnCount() {return columnCount;}
    public static int getTileSize() {return tileSize;}
    public static Maze getInstance() {return instance;}
    public PacMan getPacman() {return pacman;}
    public Blinky getBlinky() {return blinky;}
    public Pinky getPinky() {return pinky;}
    public Inky getInky() {return inky;}
    public Clyde getClyde() {return clyde;}
    public HashSet<GameObject> getWalls() {return walls;}
    public HashSet<GameObject> getGates() {return gates;}
    public HashSet<GameObject> getDots() {return dots;}
    public HashSet<GameObject> getPowerPellets() {return powerPellets;}
}
