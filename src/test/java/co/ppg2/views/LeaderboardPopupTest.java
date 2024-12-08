package co.ppg2.views;


import co.ppg2.model.Player;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;


class LeaderboardPopupTest {
    @BeforeAll
    static void initJavaFX() {
        // Initialize JavaFX toolkit
        Platform.startup(() -> {});
    }


    @Test
    void testShowLeaderboard() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Player1"));
        players.add(new Player("Player2"));


        // Simulate showing the leaderboard
        Platform.runLater(() -> {
            Stage stage = new Stage();
            LeaderboardPopup.showLeaderboard(players);
            stage.close(); // Close after testing to prevent hanging GUI
        });


        // Add assertions or visual verification notes if needed
    }
}



