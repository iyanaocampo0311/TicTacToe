package co.ppg2.model;

import java.io.Serializable;

public class Player implements Serializable {
    private String username;
    private int wins;
    private int losses;

    public Player(String username) {
        setUsername(username);
        this.username = username;
        this.wins = 0;
        this.losses = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        //added to make sure username is not empty
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username can't be empty. Please enter a valid username.");
        }
        this.username = username;
    }

    public int getWins() {
        return wins;
    }
    public int getLosses() {
        return losses;
    }

    public void incrementWins() {
        this.wins++;
    }
    public void incrementLosses() {
        this.losses++;
    }

    @Override
    public String toString() {
        return username + " - Wins: " + wins + ", Losses: " + losses;
    }
}





