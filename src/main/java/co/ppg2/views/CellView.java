package co.ppg2.views;

import co.ppg2.controllers.GameController;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class CellView extends StackPane {
    private final Button button;
    private final int row;
    private final int col;
    private final GameController gameController;

    public CellView(int row, int col, GameController gameController) {
        this.row = row;
        this.col = col;
        this.gameController = gameController;

        // Create the button for the cell
        button = new Button();
        button.setPrefSize(100, 100);
        getChildren().add(button);

        // TODO: Show which player's turn it is
        button.setOnAction(e -> handleClick());
    }

    private void handleClick() {
        // Only click if the cell is empty
        if (gameController.getCell(row, col) == null || gameController.getCell(row, col).getToken() == ' ') {
            char currentPlayerToken = gameController.getWhoseTurn();

            // Set the player's token
            gameController.setCell(row, col, new CellBase() {
                @Override
                public void setToken(char token) {
                    this.token = currentPlayerToken;
                    button.setText(String.valueOf(currentPlayerToken));  // Set token on button
                }
            });

            // TODO: Change the button's style when clicked
            if (gameController.isWon(currentPlayerToken)) {
                gameController.getGameView().handleGameOver(currentPlayerToken);  // Show winner
            } else if (gameController.isFull()) {
                gameController.getGameView().handleTie();  // Show tie
            } else {
                // Switch to next player's turn
                gameController.switchTurn();
            }
        }
    }
}
