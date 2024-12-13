package co.ppg2.services;

import javafx.application.Platform;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages the timing for a game, tracking each player's total time spent
 * and number of moves made. Provides functionality to calculate average time per move.
 * Implements {@link Runnable} to allow the timer to run in a separate thread.
 */
public class GameTimer implements Runnable {

    /**
     * Stores the total time spent by each player in milliseconds.
     */
    private final Map<String, Long> playerTotalTime;

    /**
     * Stores the number of moves made by each player.
     */
    private final Map<String, Integer> playerMoves;

    /**
     * The name of the current player whose time is being tracked.
     */
    private String currentPlayer;

    /**
     * The start time of the current player's move in milliseconds.
     */
    private long startTime;

    /**
     * Indicates whether the timer is currently running.
     */
    private boolean running;

    /**
     * Initializes a new {@link GameTimer} instance with no active players or timing data.
     */
    public GameTimer() {
        playerTotalTime = new HashMap<>();
        playerMoves = new HashMap<>();
        running = false;
    }

    /**
     * Starts the timer for a specified player. If a timer is already running, it will be stopped.
     *
     * @param playerName the name of the player whose time should be tracked
     */
    public synchronized void startTimer(String playerName) {
        if (running) cancelTimer();
        currentPlayer = playerName;
        startTime = System.currentTimeMillis();
        running = true;
        new Thread(this).start();
    }

    /**
     * Stops the timer for the current player and records the elapsed time.
     */
    public synchronized void cancelTimer() {
        if (!running) return;
        long elapsedTime = System.currentTimeMillis() - startTime;
        playerTotalTime.put(currentPlayer, playerTotalTime.getOrDefault(currentPlayer, 0L) + elapsedTime);
        playerMoves.put(currentPlayer, playerMoves.getOrDefault(currentPlayer, 0) + 1);
        running = false;
    }

    /**
     * Calculates the average time per move for a specified player.
     *
     * @param playerName the name of the player whose average move time is to be calculated
     * @return the average time per move in seconds, or 0.0 if the player has made no moves
     */
    //TODO: include when a player has no possible moves
    public synchronized double getAverageTimePerMove(String playerName) {
        long totalTime = playerTotalTime.getOrDefault(playerName, 0L);
        int moves = playerMoves.getOrDefault(playerName, 0);
        return moves == 0 ? 0.0 : (totalTime / (double) moves) / 1000.0; // Convert to seconds
    }

    /**
     * Continuously runs while the timer is active, allowing for potential GUI updates
     * or other functionality in future implementations.
     */
    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(500); // Sleep for half a second
                Platform.runLater(() -> {
                    // Update GUI components if needed in the future
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}