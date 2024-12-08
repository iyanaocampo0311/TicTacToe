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
        if (token == ' ') {
            char currentPlayerToken = gameController.getWhoseTurn();
            gameController.setCell(row, col, this); // Update game state
            setToken(currentPlayerToken);  // Update display


            // Check for win or tie condition
            if (gameController.isWon(currentPlayerToken)) {
                gameView.handleGameOver(currentPlayerToken);
            } else if (gameController.isFull()) {
                gameView.handleTie();
            } else {
                gameController.switchTurn();
                gameView.updateLabel(gameController.getCurrentPlayer().getUsername() + "'s turn");
            }
        }
    }
}

