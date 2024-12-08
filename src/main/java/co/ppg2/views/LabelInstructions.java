package co.ppg2.views;

/**
 * A custom label designed to display instructions with a specific style.
 * Extends {@link LabelBase} and overrides its default styling.
 */
public class LabelInstructions extends LabelBase {
    /**
     * Constructs a LabelInstructions instance with the specified text
     * and applies a distinct background color for instruction labels.
     *
     * @param text the text to display on the instruction label.
     */
    public LabelInstructions(String text) {
        super(text);
        setStyle("-fx-background-color: lightgreen;"); // TODO: If you plan on adding future UI styles, adding a separate CSS file would be best for maintainability
    }
}
