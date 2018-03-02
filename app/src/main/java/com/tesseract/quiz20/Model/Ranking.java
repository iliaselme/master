package com.tesseract.quiz20.Model;

public class Ranking {

    private int ID;
    private int score;
    private String username;

    public Ranking(int ID, int score,String username) {
        this.ID = ID;
        this.score = score;
        this.username = username;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
