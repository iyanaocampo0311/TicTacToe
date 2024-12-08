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
}





