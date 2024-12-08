package co.ppg2.views;


import co.ppg2.controllers.GameController;
import co.ppg2.controllers.PlayerDataController;
import co.ppg2.model.Player;
import javafx.scene.Scene;
import javafx.scene.control.Alert;  // Importing Alert class
import javafx.scene.control.Alert.AlertType;  // Importing AlertType class
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class GameView {
    private final GameController gameController;
    private final Stage primaryStage;
    private LabelInstructions labelInstructions;
    public GameView(GameController gameController, Stage primaryStage) {
        this.gameController = gameController;
        this.primaryStage = primaryStage;
    }
    public void launchGame() {
        // Validate player usernames before launching the game
        if (gameController.getCurrentPlayer().getUsername().isEmpty() ||
                gameController.getWinner('O').getUsername().isEmpty()) {
            showErrorPopup("Both players must enter a username to start the game.");
            return; // Stop further execution if validation fails; popup to make sure plyer enters a name
        }
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);



        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                CellEmpty cell = new CellEmpty(gameController, this, i, j);
                gameController.setCell(i, j, cell);
                gridPane.add(cell, j, i);
            }
        }


        labelInstructions = new LabelInstructions(gameController.getCurrentPlayer().getUsername() + "'s turn");


        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridPane);
        borderPane.setBottom(labelInstructions);


        Scene scene = new Scene(borderPane, 800, 600); //changed dimensions of window
        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to show the error popup
    private void showErrorPopup(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void updateLabel(String text) {
        labelInstructions.setText(text);
    }

//TODO: Possibly make it so this message pops up in the middle of the screen or is larger
    public void handleTie() {
        updateLabel("It is a tie!");
        LeaderboardPopup.showLeaderboard(PlayerDataController.loadPlayers());
    }


    public void handleGameOver(char token) {
        Player winner = gameController.getWinner(token);
        updateLabel(winner.getUsername() + " won!");


        gameController.updateLeaderboard(token);
    }
}



