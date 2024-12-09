package co.ppg2.views;


import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;


public class CellO extends CellBase {
    public CellO() {
        Ellipse ellipse = new Ellipse(50, 50, 40, 40);
        ellipse.setStroke(Color.BLACK);
        ellipse.setFill(Color.WHITE);
        this.getChildren().add(ellipse);
    }


    @Override
    public void setToken(char token) {
        this.token = 'O';
    }
}





