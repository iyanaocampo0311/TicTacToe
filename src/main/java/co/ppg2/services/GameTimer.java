package co.ppg2.services;

import javafx.application.Platform;
import java.util.HashMap;
import java.util.Map;


public class GameTimer implements Runnable {
    private final Map<String, Long> playerTotalTime; // Total time spent by each player
    private final Map<String, Integer> playerMoves; // Number of moves made by each player
    private String currentPlayer; // Current player whose time is being tracked
    private long startTime; // Start time of the current player's move
    private boolean running; // Timer state
    public GameTimer() {
        playerTotalTime = new HashMap<>();
        playerMoves = new HashMap<>();
        running = false;
    }

    // Starts the timer for the current player
    public synchronized void startTimer(String playerName) {
        if (running) cancelTimer();
        currentPlayer = playerName;
        startTime = System.currentTimeMillis();
        running = true;
        new Thread(this).start(); // Start the timer thread
    }

    // Stops the timer and records the elapsed time
    public synchronized void cancelTimer() {
        if (!running) return;
        long elapsedTime = System.currentTimeMillis() - startTime;
        playerTotalTime.put(currentPlayer, playerTotalTime.getOrDefault(currentPlayer, 0L) + elapsedTime);
        playerMoves.put(currentPlayer, playerMoves.getOrDefault(currentPlayer, 0) + 1);
        running = false;
    }

    // Computes the average time per move for a player
    public synchronized double getAverageTimePerMove(String playerName) {
        long totalTime = playerTotalTime.getOrDefault(playerName, 0L);
        int moves = playerMoves.getOrDefault(playerName, 0);
        return moves == 0 ? 0.0 : (totalTime / (double) moves) / 1000.0; // Convert to seconds
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(500); // Update every half second if needed
                Platform.runLater(() -> {
                    // Update GUI components if needed in future
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}



            // TODO: maybe add a in game clock for more multithreading use





