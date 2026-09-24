package pacman.managers;

import java.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScoreManager implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScoreManager.class);
    private static final ScoreManager instance = new ScoreManager();
    private static final long serialVersionUID = 1L;
    private static final String HIGH_SCORE_FILE = "highscore.data";
    private long score;
    private long highScore;

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
            LOGGER.info("High Score saved: " + highScore);
        } catch (IOException e) {
            LOGGER.error("Error saving High Score: " + e.getMessage(), e);
        }
    }

    private long loadHighScore() {
        try (FileInputStream fileIn = new FileInputStream(HIGH_SCORE_FILE);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            ScoreManager loaded = (ScoreManager) objectIn.readObject();
            long loadedHighScore = loaded.getHighScore();
            LOGGER.info("High Score loaded: " + loadedHighScore);
            return loadedHighScore;
        } catch (FileNotFoundException e) {
            LOGGER.warn("High Score file not found, starting with 0");
            return 0;
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error("Error loading High Score: " + e.getMessage(), e);
            return 0;
        }
    }

    public void addPoints(long points) {this.score += points;}

    public long getScore() {return score;}
    public long getHighScore() {return highScore;}
    public static ScoreManager getInstance() {return instance;}

    public void setScore(long score) {this.score = score;}
    public void setHighScore(long highScore) {this.highScore = highScore;}
}
