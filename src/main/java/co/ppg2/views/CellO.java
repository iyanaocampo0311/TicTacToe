package co.ppg2.views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class CellO extends CellBase {
    public CellO() {
        // TODO: Maybe allow color change for "O" in the future
        Ellipse ellipse = new Ellipse(50, 50, 40, 40); // Create shape for "O"
        ellipse.setStroke(Color.BLACK); // Set outline color
        ellipse.setFill(Color.WHITE); // Set fill color
        this.getChildren().add(ellipse); // Add the shape to the cell
    }

    @Override
    public void setToken(char token) {
        // TODO: Check if token is valid ('O')
        this.token = 'O'; // Set token to 'O'
    }

    // TODO: Add a reset method to clear the cell
}
