package co.ppg2.views;

import javafx.scene.layout.Pane;

public abstract class CellBase extends Pane {
    protected char token = ' ';
    public char getToken() {
        return token;
    }

    public abstract void setToken(char token);
}





