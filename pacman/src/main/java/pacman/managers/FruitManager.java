package pacman.managers;

import pacman.Maze;
import pacman.objects.items.Fruit;

import java.util.Timer;
import java.util.TimerTask;

public class FruitManager {
    private static final FruitManager instance = new FruitManager();
    private Fruit currentFruit;
    private Timer fruitSpawnTimer;
    private Timer fruitDespawnTimer;
    private int fruitSpawnX;
    private int fruitSpawnY;
    private final int tileSize;
    private static final long FRUIT_SPAWN_DELAY = 10000;
    private static final long FRUIT_DESPAWN_DELAY = 10000;

    private FruitManager() {
        fruitSpawnTimer = new Timer();
        fruitDespawnTimer = new Timer();
        this.tileSize = Maze.getTileSize();
    }

    public void setSpawnCoordinates(int x, int y) {
        this.fruitSpawnX = x;
        this.fruitSpawnY = y;
    }

    public void startFruitCycle() {
        fruitSpawnTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                spawnFruit();
            }
        }, FRUIT_SPAWN_DELAY);
    }

    private void spawnFruit() {
        if (currentFruit == null || currentFruit.isCollected()) {
            currentFruit = Fruit.createRandomFruit(fruitSpawnX, fruitSpawnY, tileSize - 6, tileSize - 6);

            fruitDespawnTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    despawnFruit();
                }
            }, FRUIT_DESPAWN_DELAY);
        }
    }

    private void despawnFruit() {
        currentFruit = null;

        fruitSpawnTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                spawnFruit();
            }
        }, FRUIT_SPAWN_DELAY);
    }

    public Fruit getCurrentFruit() {return currentFruit;}
    public static FruitManager getInstance() {return instance;}

    public void setCurrentFruit(Fruit fruit) {this.currentFruit = fruit;}
}
