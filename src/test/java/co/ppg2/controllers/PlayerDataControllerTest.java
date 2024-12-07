package co.ppg2.controllers;

import co.ppg2.model.Player;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


class PlayerDataControllerTest {


    @Test
    void testSaveAndLoadPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Player1"));
        players.add(new Player("Player2"));


        PlayerDataController.savePlayers(players);


        ArrayList<Player> loadedPlayers = PlayerDataController.loadPlayers();
        assertEquals(2, loadedPlayers.size());
        assertEquals("Player1", loadedPlayers.get(0).getUsername());
        assertEquals("Player2", loadedPlayers.get(1).getUsername());
    }
}





