package co.ppg2.model;

import java.io.Serializable;

public class Player implements Serializable {
    private final String username;
    private int wins;
    private int losses;
    // TODO: Consider adding a serialVersionUID for better control over serialization

    public Player(String username) {
        // TODO: Add input validation for username (e.g., not null, not empty)
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
    // TODO: Consider implementing equals() and hashCode() methods for proper object comparison

    // TODO: Consider adding a method to calculate win percentage

    // TODO: Consider adding a method to reset player statistics
}





