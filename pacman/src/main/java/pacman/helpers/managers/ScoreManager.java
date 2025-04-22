package pacman.helpers.managers;

import java.io.*;

public class ScoreManager implements Serializable {
    private static final ScoreManager instance = new ScoreManager();
    private static final long serialVersionUID = 1L;
    private static final String HIGH_SCORE_FILE = "highscore.data";
    private int score; // Max Value: 2147479999
    private int highScore; // Max Value: 2147479999

    public ScoreManager() {
        this.score = 0;
        this.highScore = loadHighScore();
    }

    public void updateHighScore() {
        if (score > highScore)
            highScore = score;
    }

    public void saveHighScore() {
        try (FileOutputStream fileOut = new FileOutputStream(HIGH_SCORE_FILE);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(this);
            System.out.println("High Score saved: " + highScore);
        } catch (IOException e) {
            System.err.println("Error saving High Score: " + e.getMessage());
        }
    }

    private int loadHighScore() {
        try (FileInputStream fileIn = new FileInputStream(HIGH_SCORE_FILE);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            ScoreManager loaded = (ScoreManager) objectIn.readObject();
            int loadedHighScore = loaded.getHighScore();
            System.out.println("High Score loaded: " + loadedHighScore);
            return loadedHighScore;
        } catch (FileNotFoundException e) {
            System.out.println("High Score file not found, starting with 0");
            return 0;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading High Score: " + e.getMessage());
            return 0;
        }
    }

    public void addPoints(int points) {this.score += points;}

    public int getScore() {return score;}
    public int getHighScore() {return highScore;}
    public static ScoreManager getInstance() {return instance;}

    public void setScore(int score) {this.score = score;}
    public void setHighScore(int highScore) {this.highScore = highScore;}
}
