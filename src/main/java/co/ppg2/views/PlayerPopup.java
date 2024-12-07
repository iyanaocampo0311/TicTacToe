package co.ppg2.views;


import co.ppg2.model.Player;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class PlayerPopup {
    public static Player showPopup(String playerLabel) {
        // TODO: Add null check for playerLabel
        Stage popupStage = new Stage();
        VBox vbox = new VBox();
        Label label = new Label("Enter username for " + playerLabel + ":");
        TextField usernameField = new TextField();
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            if (!usernameField.getText().isEmpty()) {
                popupStage.close();
            } else {
                // TODO: Show an error message if the username is empty
            }
        });


        vbox.getChildren().addAll(label, usernameField, submitButton);
        Scene scene = new Scene(vbox, 300, 150);
        popupStage.setTitle(playerLabel + " Setup");
        popupStage.setScene(scene);
        popupStage.showAndWait();

        // TODO: Consider validating the username (e.g., no special characters, minimum length)
        return new Player(usernameField.getText()); // Create and return player object
    }

    // TODO: Consider adding a method to check if a username already exists
    // TODO: Consider adding styling to make the popup more visually appealing
}


