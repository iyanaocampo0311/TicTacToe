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
        // TODO: Check if wins can go below zero
    }

    public void incrementLosses() {
        this.losses++;
        // TODO: Check if losses can go below zero
    }

    @Override
    public String toString() {
        return username + " - Wins: " + wins + ", Losses: " + losses;
        // TODO: Maybe show win percentage or other details
    }
}
