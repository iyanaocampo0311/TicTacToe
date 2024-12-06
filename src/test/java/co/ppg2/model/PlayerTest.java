package co.ppg2.model;

import co.ppg2.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


class PlayerTest {
    private Player player;


    @BeforeEach
    void setUp() {
        player = new Player("PlayerX");
    }


    @Test
    void testGetUsername() {
        assertEquals("PlayerX", player.getUsername());
    }


    @Test
    void testGetWins() {
        assertEquals(0, player.getWins());
    }


    @Test
    void testGetLosses() {
        assertEquals(0, player.getLosses());
    }


    @Test
    void testIncrementWins() {
        player.incrementWins();
        assertEquals(1, player.getWins());
    }


    @Test
    void testIncrementLosses() {
        player.incrementLosses();
        assertEquals(1, player.getLosses());
    }


    @Test
    void testToString() {
        assertEquals("PlayerX - Wins: 0, Losses: 0", player.toString());
    }
    // TODO: Add test for creating a player with an empty username

    // TODO: Add test for creating a player with a very long username

    // TODO: Add test for multiple increments of wins and losses

    // TODO: Add test for resetting player statistics

    // TODO: Add test for comparing two Player objects

    // TODO: Add test for player with maximum possible wins and losses

    // TODO: Add test for player name with special characters
}





