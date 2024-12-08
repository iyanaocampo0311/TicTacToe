package co.ppg2.views;

import javafx.scene.shape.Line;

public class CellX extends CellBase {
    public CellX() {
        // TODO: Add comments explaining that we create two lines for 'X'
        Line line1 = new Line(10, 10, 90, 90);
        Line line2 = new Line(90, 10, 10, 90);
        this.getChildren().addAll(line1, line2);
    }

    @Override
    public void setToken(char token) {
        // TODO: Make sure this method is correctly setting the token for 'X'
        this.token = 'X';
    }
}
