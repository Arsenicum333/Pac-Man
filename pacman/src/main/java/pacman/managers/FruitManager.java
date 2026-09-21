package pacman.managers;

import pacman.Maze;
import pacman.objects.items.Fruit;

public class FruitManager {
    private static final FruitManager instance = new FruitManager();
    private Fruit currentFruit;
    private boolean cycleStarted;
    private long fruitTimer;
    private long lastUpdateTime;
    private int fruitSpawnX;
    private int fruitSpawnY;
    private final int tileSize;
    private static final long FRUIT_SPAWN_DELAY = 10000;
    private static final long FRUIT_DESPAWN_DELAY = 10000;

    private FruitManager() {
        this.tileSize = Maze.getTileSize();
    }

    public void setSpawnCoordinates(int x, int y) {
        this.fruitSpawnX = x;
        this.fruitSpawnY = y;
    }

    public void startFruitCycle() {
        if (cycleStarted)
            return;

        cycleStarted = true;
    }

    public void update() {
        if (!cycleStarted)
            return;

        long currentTime = System.nanoTime();

        if (lastUpdateTime == 0) {
            lastUpdateTime = currentTime;
            return;
        }

        fruitTimer += (currentTime - lastUpdateTime) / 1_000_000;
        lastUpdateTime = currentTime;

        if (currentFruit == null && fruitTimer >= FRUIT_SPAWN_DELAY) {
            spawnFruit();
        } else if (currentFruit != null && fruitTimer >= FRUIT_DESPAWN_DELAY) {
            despawnFruit();
        }
    }

    private void spawnFruit() {
        if (currentFruit != null)
            return;

        currentFruit = Fruit.createRandomFruit(fruitSpawnX, fruitSpawnY, tileSize - 6, tileSize - 6);
        fruitTimer = 0;
    }

    private void despawnFruit() {
        currentFruit = null;
        fruitTimer = 0;
    }

    public void collectFruit() {
        if (currentFruit == null)
            return;

        currentFruit = null;
        fruitTimer = 0;
    }

    public void resetClock() {lastUpdateTime = 0;}

    public Fruit getCurrentFruit() {return currentFruit;}
    public static FruitManager getInstance() {return instance;}

    public void setCurrentFruit(Fruit fruit) {this.currentFruit = fruit;}
}
