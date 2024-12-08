package co.ppg2.views;

import co.ppg2.controllers.GameController;
import co.ppg2.controllers.PlayerDataController;
import co.ppg2.model.Player;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Represents the graphical interface for the TicTacToe game.
 */
public class GameView {
    private final GameController gameController;
    private final Stage primaryStage;
    private LabelInstructions labelInstructions;

    /**
     * Constructs the game view.
     *
     * @param gameController the game controller managing the game logic
     * @param primaryStage   the primary stage for displaying the game
     */
    public GameView(GameController gameController, Stage primaryStage) {
        this.gameController = gameController;
        this.primaryStage = primaryStage;
    }

    /**
     * Launches the game interface and initializes the game grid.
     */
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

        labelInstructions = new LabelInstructions(gameController.getCurrentPlayer().getUsername() + "'s turn"); // TODO: Possibly ensure LabelInstructions is defined clearly elsewhere

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridPane);
        borderPane.setBottom(labelInstructions);

        Scene scene = new Scene(borderPane, 450, 170); // TODO: (ag) You can consider making the panel bigger for a better gaming experience
        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Updates the label displaying the current game status.
     *
     * @param text the new text to display
     */
    public void updateLabel(String text) {
        labelInstructions.setText(text);
    }

    /**
     * Handles a tie game scenario.
     */
    public void handleTie() {
        updateLabel("It is a tie!");
        LeaderboardPopup.showLeaderboard(PlayerDataController.loadPlayers());
    }

    /**
     * Handles the end of the game when a player wins.
     *
     * @param token the token of the winning player
     */
    public void handleGameOver(char token) {
        Player winner = gameController.getWinner(token);
        updateLabel(winner.getUsername() + " won!");
        gameController.updateLeaderboard(token);
    }
}
