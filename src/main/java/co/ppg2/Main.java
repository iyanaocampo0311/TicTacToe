package co.ppg2;

import co.ppg2.controllers.GameController;
import co.ppg2.controllers.PlayerDataController;
import co.ppg2.model.Player;
import co.ppg2.services.GameTimer;
import co.ppg2.views.GameView;
import co.ppg2.views.PlayerPopup;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * Adapted from <a href="https://liveexample.pearsoncmg.com/html/TicTacToe.html">...</a>
 * Main class for launching the Tic Tac Toe game application.
 * This class is responsible for setting up the game, including the players and game timer.
 * It also initializes the main game view and controls the flow of the game.
 */
public class Main extends Application {
    private static ArrayList<Player> players;
    public static GameController gameController;
    public static GameView gameView;
    public static GameTimer gameTimer; // Shared GameTimer instance

    /**
     * The entry point for the application. Loads saved players and launches the game.
     *
     * @param args the command-line arguments (unused).
     */

    //The PlayerDataController.loadPlayers() method could fail due to issues like a corrupted file or missing data.
    // Added exception handling to manage these scenarios gracefully.
    public static void main(String[] args) {
        try {
            players = PlayerDataController.loadPlayers(); // Load players on start
            if (players == null) {
                players = new ArrayList<>();
            }
            } catch(Exception e) {
                System.err.println("Error loading players: " + e.getMessage());
                players = new ArrayList<>(); // Initialize an empty list if loading fails
            }
            launch(args);
        }



    /**
     * Starts the application by displaying the player setup and initializing the game.
     * Prompts for the usernames of Player X and Player O, sets up the game,
     * and starts the game timer for Player X.
     *
     * @param primaryStage the main stage for the application.
     */
    @Override

    public void start(Stage primaryStage) {
        // Prompt for Player X's username and Player O's username
        Player playerX = PlayerPopup.showPopup("Player X");
        Player playerO = PlayerPopup.showPopup("Player O");

        // Find or add Player X and Player O in the players list
        playerX = findOrAddPlayer(playerX.getUsername());
        playerO = findOrAddPlayer(playerO.getUsername());

        // Initialize shared GameTimer
        gameTimer = new GameTimer();

        // Initialize GameController and GameView
        gameController = new GameController(playerX, playerO);
        gameController.setGameTimer(gameTimer); // Pass GameTimer to GameController
        gameView = new GameView(gameController, primaryStage);
        gameController.setGameView(gameView);

        // Launch the game view
        gameView.launchGame();

        // Start the timer for Player X
        gameTimer.startTimer(playerX.getUsername());
    }

    /**
     * Finds an existing player by username or creates a new player if not found.
     *
     * @param username the username of the player to find or add.
     * @return the found or newly created Player.
     */

    //Added try-catch blocks with meaningful error messages to handle potential issues.
    private Player findOrAddPlayer(String username) {
        try {
            if (username == null || username.trim().isEmpty()) {
                throw new IllegalArgumentException("Username cannot be null or empty.");
            }

            for (Player player : players) {
                if (player.getUsername().equals(username)) {
                    return player;
                }
            }

            Player newPlayer = new Player(username);
            players.add(newPlayer);

            try {
                PlayerDataController.savePlayers(players);
            } catch (Exception e) {
                System.err.println("Error saving players: " + e.getMessage());
            }

            return newPlayer;
        } catch (Exception e) {
            System.err.println("Error finding or adding player: " + e.getMessage());
            return new Player("Unknown"); // Return a fallback player to avoid crashes
        }
    }

    //This approach ensures your application exits cleanly in the event of an error.
    // GracefulExitHandler can be used to handle exceptions effectively by centralizing the error handling and application exit logic.
    public class GracefulExitHandler {
        /**
         * Handles critical errors by logging the error, notifying the user,
         * and exiting the application gracefully.
         *
         */
        public static void handleCriticalError(String message, Throwable throwable) {
            // Log the error (console or logging framework)
            System.err.println("Critical error: " + message);
            if (throwable != null) {
                throwable.printStackTrace(); // Log stack trace for debugging
            }

            // Notify the user (optional, if the UI is still active)
            Platform.runLater(() -> {
                // Replace this with your UI framework's error dialog or message
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                        javafx.scene.control.Alert.AlertType.ERROR
                );
                alert.setTitle("Critical Error");
                alert.setHeaderText("An unexpected error occurred.");
                alert.setContentText(message);
                alert.showAndWait();

                // Exit the application gracefully
                exitApplication();
            });
        }

        /**
         * Cleans up resources and exits the application with a non-zero code.
         */
        private static void exitApplication() {
            // Perform any necessary cleanup here (close files, stop threads, etc.)
            System.err.println("Cleaning up resources before exiting...");

            // Ensure the JavaFX application exits properly
            Platform.exit();

            // Exit the JVM with a non-zero code to indicate an error
            System.exit(1);
        }
    }


}
