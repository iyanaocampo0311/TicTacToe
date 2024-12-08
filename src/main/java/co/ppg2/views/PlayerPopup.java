package co.ppg2.views;

import co.ppg2.model.Player;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * This class represents a popup window where the user can enter the username for a player.
 * It provides an input field for the username and a submit button.
 */
public class PlayerPopup extends Parent {

    /**
     * Displays a popup for the user to input a username for a player.
     * Once the username is entered and the submit button is clicked,
     * a new Player object is created with the entered username.
     *
     * @param playerLabel a label to display, indicating which player is being set up (e.g., "Player X" or "Player O").
     * @return a Player object with the entered username.
     */
    public static Player showPopup(String playerLabel) {
        Stage popupStage = new Stage();
        VBox vbox = new VBox();
        Label label = new Label("Enter username for " + playerLabel + ":");
        TextField usernameField = new TextField();
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            if (!usernameField.getText().isEmpty()) {
                popupStage.close();
            }
        });

        vbox.getChildren().addAll(label, usernameField, submitButton);
        Scene scene = new Scene(vbox, 300, 150);
        popupStage.setTitle(playerLabel + " Setup");
        popupStage.setScene(scene);
        popupStage.showAndWait();
        return new Player(usernameField.getText()); // Create and return player object
    } // TODO: You could consider making sure the player enters a valid username or make it so that the popup doesn't appear when a player doesn't enter anything as their username

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
