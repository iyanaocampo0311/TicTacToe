package co.ppg2.views;

import co.ppg2.model.Player;
import co.ppg2.Main;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class LeaderboardPopup {
    public static void showLeaderboard(List<Player> players) {
        // TODO: Add null check for players list
        Stage popupStage = new Stage();
        VBox vbox = new VBox();
        Label label = new Label("Leaderboard:");




        ListView<String> listView = new ListView<>();




        players.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getWins(), p1.getWins()))
                .limit(10)
                .forEach(player -> {
                    double avgTime = Main.gameTimer.getAverageTimePerMove(player.getUsername()); // Get average time
                    listView.getItems().add(player + String.format(", Avg Time: %.2f seconds", avgTime));
                });




        vbox.getChildren().addAll(label, listView);
        Scene scene = new Scene(vbox, 300, 300);
        popupStage.setTitle("Leaderboard");
        popupStage.setScene(scene);
        popupStage.showAndWait();

        // TODO: Consider adding a close button to dismiss the leaderboard
        // TODO: Consider adding styling to make the leaderboard more visually appealing
    }

    // TODO: Consider adding a method to update the leaderboard without creating a new window
    // TODO: Consider adding pagination if the number of players grows large
}



