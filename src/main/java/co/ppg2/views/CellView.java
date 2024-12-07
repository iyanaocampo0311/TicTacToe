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
        // TODO: Add null check for gameController
        this.row = row;
        this.col = col;
        this.gameController = gameController;


        // Initialize the button and add it to the cell view
        button = new Button();
        button.setPrefSize(100, 100);
        getChildren().add(button);


        // Set action for button click
        button.setOnAction(e -> handleClick());

        // TODO: Consider adding a method to reset the cell for a new game
    }


    private void handleClick() {
        // Only proceed if the cell is empty
        if (gameController.getCell(row, col) == null || gameController.getCell(row, col).getToken() == ' ') {
            char currentPlayerToken = gameController.getWhoseTurn();


            // Set the token in GameController and update button text
            gameController.setCell(row, col, new CellBase() {
                @Override
                public void setToken(char token) {
                    this.token = currentPlayerToken;
                    button.setText(String.valueOf(currentPlayerToken));
                }
            });


            // Check if there's a winner or if the board is full
            if (gameController.isWon(currentPlayerToken)) {
                gameController.getGameView().handleGameOver(currentPlayerToken);  // Handle win
            } else if (gameController.isFull()) {
                gameController.getGameView().handleTie();  // Handle tie
            } else {
                // Switch player turn and prepare for the next move
                gameController.switchTurn();
                // TODO: Update the UI to indicate the next player's turn
            }
        }
        // TODO: Consider adding visual feedback when clicking an already occupied cell
    }
    // TODO: Consider adding a method to disable the cell after the game ends
}

