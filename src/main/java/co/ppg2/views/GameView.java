package co.ppg2.views;


import co.ppg2.controllers.GameController;
import co.ppg2.controllers.PlayerDataController;
import co.ppg2.model.Player;
import javafx.scene.Scene;
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

        //Change the Dimensions of the window so that users don't have to resize it to see the full game
        Scene scene = new Scene(borderPane, 305, 325);
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
}


