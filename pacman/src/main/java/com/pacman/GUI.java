package com.pacman;

public class GUI {
    private static final GUI instance = new GUI();
    private int currentScore = 0;
    private int highestScore = 0;

    public int getCurrentScore() {return currentScore;}
    public int getHighestScore() {return highestScore;}
    public static GUI getInstance() {return instance;}

    public void setCurrentScore(int currentScore) {this.currentScore = currentScore;}
    public void setHighestScore(int highestScore) {this.highestScore = highestScore;}
}
