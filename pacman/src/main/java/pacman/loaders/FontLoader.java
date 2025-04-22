package pacman.loaders;

import java.awt.*;
import java.io.InputStream;

public class FontLoader {
    private static Font pressStart2PFont;
    private static Font arcadeClassicFont;
    private static Font joystixMonospaceFont;

    static {
        pressStart2PFont = loadFont("fonts/PressStart2P.ttf");
        arcadeClassicFont = loadFont("fonts/ArcadeClassic.ttf");
        joystixMonospaceFont = loadFont("fonts/JoystixMonospace.otf");
    }

    private static Font loadFont(String path) {
        try {
            InputStream is = FontLoader.class.getClassLoader().getResourceAsStream(path);

            if (is == null) {
                System.err.println("Could not find font: " + path);
                return new Font("Arial", Font.PLAIN, 12);
            }

            return Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e) {
            System.err.println("Failed to load font: " + path);
            e.printStackTrace();
            return new Font("Arial", Font.PLAIN, 12);
        }
    }

    public static Font getPressStartFont(float size) {return pressStart2PFont.deriveFont(size);}
    public static Font getArcadeClassicFont(float size) {return arcadeClassicFont.deriveFont(size);}
    public static Font getJoystixMonospaceFont(float size) {return joystixMonospaceFont.deriveFont(size);}
}
