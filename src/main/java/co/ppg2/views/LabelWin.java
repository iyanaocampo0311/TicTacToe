package co.ppg2.views;


public class LabelWin extends LabelBase {
    public String winMessage;

    public LabelWin(String text) {
        super(text);
        setStyle("-fx-background-color: lightcoral;");
    }
}

// TODO: LableWin is not being used so I would delete it
