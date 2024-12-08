package co.ppg2.views;


import javafx.scene.control.Label;


public class LabelBase extends Label {
    public LabelBase(String text) {
        super(text);
        setStyle("-fx-font-size: 16px; -fx-background-color: lightblue;");
    }
}


