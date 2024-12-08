package co.ppg2.views;

import co.ppg2.controllers.GameController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.BorderWidths;

public class CellEmpty extends CellBase {
    private static final int CELL_SIZE = 100;
    private final int row;
    private final int col;
    private final GameController gameController;
    private final GameView gameView;

    public CellEmpty(GameController gameController, GameView gameView, int row, int col) {
        this.gameController = gameController;
        this.gameView = gameView;
        this.row = row;
        this.col = col;

        Rectangle rect = new Rectangle(CELL_SIZE, CELL_SIZE);
        rect.setFill(Color.LIGHTGRAY);
        this.getChildren().add(rect);

        this.setBorder(new Border(
                new BorderStroke(
                        Color.BLACK,
                        BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY,
                        new BorderWidths(1)
                )
        ));

        this.setOnMouseClicked(e -> handleMouseClick());
    }

    @Override
    public void setToken(char token) {
        this.token = token;
        this.getChildren().clear();
        if (token == 'X') {
            this.getChildren().add(new CellX());
        } else if (token == 'O') {
            this.getChildren().add(new CellO());
        }
    }

    private void handleMouseClick() {
        // TODO: Check if the cell is empty before updating (to avoid overwriting)
        if (token == ' ') {
            char currentPlayerToken = gameController.getWhoseTurn();
            gameController.setCell(row, col, this); // Update game state
            setToken(currentPlayerToken);  // Show the player's move

            // TODO: Make sure the game stops if someone wins or ties, so no more moves are made

            // Check if the current player has won
            if (gameController.isWon(currentPlayerToken)) {
                gameView.handleGameOver(currentPlayerToken); // Show game over screen
            } else if (gameController.isFull()) {
                gameView.handleTie(); // Show tie screen if the board is full
            } else {
                gameController.switchTurn(); // Switch to the next player's turn
                gameView.updateLabel(gameController.getCurrentPlayer().getUsername() + "'s turn");
            }
        }
    }

    // TODO: Add a way to highlight which player's turn it is (e.g., color the border or token)
    // TODO: Add a method to reset the cell when the game restarts
    // TODO: Make sure no moves can be made after the game is over
}
