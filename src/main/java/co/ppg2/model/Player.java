package co.ppg2.model;

import java.io.Serializable;

public class Player implements Serializable {
    private final String username;
    private int wins;
    private int losses;

    public Player(String username) {
        this.username = username;
        this.wins = 0;
        this.losses = 0;
    }

    public String getUsername() {
        return username;
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





