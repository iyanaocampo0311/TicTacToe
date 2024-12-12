package co.ppg2.views;

/**
 * A custom label designed to display a win message with a specific style.
 * Extends {@link LabelBase} and overrides its default styling to indicate a win.
 */
public class LabelWin extends LabelBase {
    // Message to be displayed when a player wins
    public String winMessage;

    //TODO: Labelwin is not being used so it can be deleted
    /**
     * Constructs a LabelWin instance with the specified text.
     * This label is styled with a light coral background to indicate a win.
     *
     * @param text the text to display on the win label.
     */
    public LabelWin(String text) {
        super(text);
        setStyle("-fx-background-color: lightcoral;");
    }
}