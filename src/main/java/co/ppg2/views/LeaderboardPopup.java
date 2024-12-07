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
        Stage popupStage = new Stage();
        VBox vbox = new VBox();
        Label label = new Label("Leaderboard:");




        ListView<String> listView = new ListView<>();

        // TODO: use a table view instead of list view to separate each of the stat information

        players.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getWins(), p1.getWins()))
                .limit(10)
                .forEach(player -> {
                    double avgTime = Main.gameTimer.getAverageTimePerMove(player.getUsername()); // Get average time
                    listView.getItems().add(player + String.format(", Avg Time: %.2f seconds", avgTime));
                });

        // TODO: add options from the leaderboard to quit the game or start a new game with the same users

        vbox.getChildren().addAll(label, listView);
        Scene scene = new Scene(vbox, 300, 300);
        popupStage.setTitle("Leaderboard");
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }
}



