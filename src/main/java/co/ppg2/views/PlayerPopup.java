package co.ppg2.views;


import co.ppg2.model.Player;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class PlayerPopup {
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
    }
}


