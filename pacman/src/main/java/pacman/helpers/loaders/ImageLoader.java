package pacman.helpers.loaders;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ImageLoader {
    private static final String PATH = "/images/";
    private static HashMap<String, Image> images = new HashMap<>();

    static {loadImages();}

    private static void loadImages() {
        images.put("Pause", loadImage("Pause.png"));
        images.put("Play", loadImage("Play.png"));

        images.put("Wall", loadImage("Wall.png"));
        images.put("Gate", loadImage("Gate.png"));
        images.put("Dot", loadImage("Dot.png"));
        images.put("PowerPellet", loadImage("PowerPellet.gif"));

        images.put("Cherry", loadImage("Cherry.png"));
        images.put("Strawberry", loadImage("Strawberry.png"));
        images.put("Orange", loadImage("Orange.png"));
        images.put("Apple", loadImage("Apple.png"));
        images.put("Melon", loadImage("Melon.png"));

        images.put("PacManIcon", loadImage("PacManIcon.png"));
        images.put("PacManIconBorder", loadImage("PacManIconBorder.png"));
        images.put("PacManDown", loadImage("PacManDown.gif"));
        images.put("PacManLeft", loadImage("PacManLeft.gif"));
        images.put("PacManRight", loadImage("PacManRight.gif"));
        images.put("PacManUp", loadImage("PacManUp.gif"));

        images.put("BlinkyDown", loadImage("BlinkyDown.gif"));
        images.put("BlinkyLeft", loadImage("BlinkyLeft.gif"));
        images.put("BlinkyRight", loadImage("BlinkyRight.gif"));
        images.put("BlinkyUp", loadImage("BlinkyUp.gif"));

        images.put("PinkyDown", loadImage("PinkyDown.gif"));
        images.put("PinkyLeft", loadImage("PinkyLeft.gif"));
        images.put("PinkyRight", loadImage("PinkyRight.gif"));
        images.put("PinkyUp", loadImage("PinkyUp.gif"));

        images.put("InkyDown", loadImage("InkyDown.gif"));
        images.put("InkyLeft", loadImage("InkyLeft.gif"));
        images.put("InkyRight", loadImage("InkyRight.gif"));
        images.put("InkyUp", loadImage("InkyUp.gif"));

        images.put("ClydeDown", loadImage("ClydeDown.gif"));
        images.put("ClydeLeft", loadImage("ClydeLeft.gif"));
        images.put("ClydeRight", loadImage("ClydeRight.gif"));
        images.put("ClydeUp", loadImage("ClydeUp.gif"));
    }

    private static Image loadImage(String fileName) {return new ImageIcon(ImageLoader.class.getResource(PATH + fileName)).getImage();}

    public static Image getImage(String name) {return images.get(name);}
}
