package co.ppg2.controllers;

import co.ppg2.model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.util.ArrayList;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class PlayerDataControllerTest {

    private ArrayList<Player> players;
    private static final String TEST_FILE_PATH = "test_players.dat";

    @BeforeEach
    void setUp() {
        players = new ArrayList<>();
        players.add(new Player("Player1"));
        players.add(new Player("Player2"));

        // Set test file path for PlayerDataController
        PlayerDataController.setFilePath(TEST_FILE_PATH);
    }

    @AfterEach
    void tearDown() {
        // Clean up test file after each test
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testSaveAndLoadPlayers() {
        PlayerDataController.savePlayers(players);

        ArrayList<Player> loadedPlayers = PlayerDataController.loadPlayers();
        assertEquals(2, loadedPlayers.size());
        assertEquals("Player1", loadedPlayers.get(0).getUsername());
        assertEquals("Player2", loadedPlayers.get(1).getUsername());
    }

    // TODO: Add test for saving and loading an empty list of players

    // TODO: Add test for saving and loading a large number of players

    // TODO: Add test for saving and loading players with special characters in their names

    // TODO: Add test for handling file I/O exceptions during save and load operations

    // TODO: Add test for loading players when the file doesn't exist

    // TODO: Add test for updating existing players' data
}