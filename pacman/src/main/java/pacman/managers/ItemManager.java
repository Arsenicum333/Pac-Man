package pacman.managers;

import pacman.Maze;
import pacman.loaders.ImageLoader;
import pacman.objects.items.Heart;
import pacman.objects.items.Item;
import pacman.objects.items.powerups.Shield;
import pacman.objects.items.powerups.SpeedBoost;

import java.util.HashSet;
import java.util.Random;

public class ItemManager {
    private static final ItemManager instance = new ItemManager();
    private HashSet<Item> items;
    private int itemSpawnX;
    private int itemSpawnY;
    private final int tileSize;
    private final Random random;

    private ItemManager() {
        this.items = new HashSet<>();
        this.tileSize = Maze.getTileSize();
        this.random = new Random();
    }

    public void setSpawnCoordinates(int x, int y) {
        this.itemSpawnX = x;
        this.itemSpawnY = y;
    }

    public void spawnRandomItem() {
        Item newItem = createRandomItem(itemSpawnX, itemSpawnY);
        items.add(newItem);
    }

    private Item createRandomItem(int x, int y) {
        int choice = random.nextInt(3);
        return switch (choice) {
            case 0 -> new Heart(ImageLoader.getImage("Heart"), x, y, tileSize, tileSize);
            case 1 -> new SpeedBoost(ImageLoader.getImage("SpeedBoost"), x, y, tileSize, tileSize, 5000);
            case 2 -> new Shield(ImageLoader.getImage("Shield"), x, y, tileSize, tileSize, 5000);
            default -> throw new IllegalStateException("Unexpected random value: " + choice);
        };
    }

    public HashSet<Item> getItems() {return items;}
    public static ItemManager getInstance() {return instance;}
}
