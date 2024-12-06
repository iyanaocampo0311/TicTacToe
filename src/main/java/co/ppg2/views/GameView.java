package co.ppg2.views;

import co.ppg2.controllers.GameController;
import co.ppg2.controllers.PlayerDataController;
import co.ppg2.model.Player;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameView {
    private final GameController gameController;
    private final Stage primaryStage;
    private LabelInstructions labelInstructions;
    private BorderPane borderPane;

    public GameView(GameController gameController, Stage primaryStage) {
        this.gameController = gameController;
        this.primaryStage = primaryStage;
    }

    public void launchGame() {
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.CENTER);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                CellEmpty cell = new CellEmpty(gameController, this, i, j);
                gameController.setCell(i, j, cell);
                gridPane.add(cell, j, i);
            }
        }

        labelInstructions = new LabelInstructions(gameController.getCurrentPlayer().getUsername() + "'s turn");
        labelInstructions.setAlignment(Pos.CENTER);
        labelInstructions.setStyle("-fx-font-size: 24px;");

        borderPane = new BorderPane();
        borderPane.setCenter(gridPane);
        borderPane.setTop(labelInstructions);
        BorderPane.setAlignment(labelInstructions, Pos.CENTER); // Center the label at the top

        Scene scene = new Scene(borderPane, 500, 500);
        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void updateLabel(String text) {
        labelInstructions.setText(text);
    }

    public void handleTie() {
        updateLabel("It is a tie!");
        LeaderboardPopup.showLeaderboard(PlayerDataController.loadPlayers());
    }

    public void handleGameOver(char token) {
        Player winner = gameController.getWinner(token);
        updateLabel(winner.getUsername() + " won!");
        gameController.updateLeaderboard(token);
    }

    // Method to change background color for X turn
    public void xTurn() {
        borderPane.setStyle("-fx-background-color: #ADD8E6;");
    }

    // Method to change background color for O turn
    public void oTurn() {
        borderPane.setStyle("-fx-background-color: #F08080;");
    }
}