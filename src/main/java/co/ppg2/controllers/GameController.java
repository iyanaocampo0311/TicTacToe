package co.ppg2.controllers;

import co.ppg2.services.GameTimer;
import co.ppg2.model.Player;
import co.ppg2.views.CellBase;
import co.ppg2.views.GameView;
import co.ppg2.views.LeaderboardPopup;

import java.util.ArrayList;

/**
 * The GameController class handles the logic and state of the Tic-Tac-Toe game,
 * including player turns, game board, win conditions, and leaderboard updates.
 */
public class GameController {
    private char whoseTurn = 'X';
    private final CellBase[][] cells = new CellBase[3][3];
    private final Player playerX;
    private final Player playerO;
    private GameTimer gameTimer;
    private GameView gameView;
    private final ArrayList<Player> players;

    /**
     * Constructor for GameController.
     *
     * @param playerX Player representing X
     * @param playerO Player representing O
     */
    public GameController(Player playerX, Player playerO) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.players = PlayerDataController.loadPlayers(); // TODO: (ag) Check if PlayerDataController exists and can load players correctly.
    }

    /**
     * Gets the current player's token.
     *
     * @return The token of the current player ('X' or 'O')
     */
    public char getWhoseTurn() {
        return whoseTurn;
    }

    /**
     * Gets the current player based on whose turn it is.
     *
     * @return The current player
     */
    public Player getCurrentPlayer() {
        return (whoseTurn == 'X') ? playerX : playerO;
    }

    /**
     * Switches the turn to the next player and resets their timer.
     */
    public void switchTurn() {
        if (gameTimer != null) {
            gameTimer.cancelTimer(); // TODO: (ag) Ensure cancelTimer() exists in GameTimer.
            whoseTurn = (whoseTurn == 'X') ? 'O' : 'X';
            gameTimer.startTimer(getCurrentPlayer().getUsername());
        }
    }

    /**
     * Checks if the game board is full.
     *
     * @return True if the board is full, otherwise false
     */
    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == null || cells[i][j].getToken() == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if the given token has won the game.
     *
     * @param token The token to check ('X' or 'O')
     * @return True if the token has won, otherwise false
     */
    public boolean isWon(char token) {
        for (int i = 0; i < 3; i++) {
            if (cells[i][0].getToken() == token && cells[i][1].getToken() == token && cells[i][2].getToken() == token) {
                return true;
            }
        }
        for (int j = 0; j < 3; j++) {
            if (cells[0][j].getToken() == token && cells[1][j].getToken() == token && cells[2][j].getToken() == token) {
                return true;
            }
        }
        if (cells[0][0].getToken() == token && cells[1][1].getToken() == token && cells[2][2].getToken() == token) {
            return true;
        }
        return cells[0][2].getToken() == token && cells[1][1].getToken() == token && cells[2][0].getToken() == token;
    }

    /**
     * Sets the cell at the given row and column with the specified CellBase instance.
     *
     * @param row  The row of the cell
     * @param col  The column of the cell
     * @param cell The cell to set
     */
    public void setCell(int row, int col, CellBase cell) {
        cells[row][col] = cell;
    }

    /**
     * Sets the GameTimer instance for the controller.
     *
     * @param gameTimer The GameTimer to set
     */
    public void setGameTimer(GameTimer gameTimer) {
        this.gameTimer = gameTimer;
    }

    /**
     * Gets the cell at the specified row and column.
     *
     * @param row The row of the cell
     * @param col The column of the cell
     * @return The CellBase instance at the given position
     */
    public CellBase getCell(int row, int col) {
        return cells[row][col];
    }

    /**
     * Sets the GameView instance for the controller.
     *
     * @param gameView The GameView to set
     */
    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Gets the current GameView instance.
     *
     * @return The GameView instance
     */
    public GameView getGameView() {
        return gameView;
    }

    /**
     * Gets the Player object for the winner based on the token.
     *
     * @param token The token of the winner ('X' or 'O')
     * @return The winning Player
     */
    public Player getWinner(char token) {
        return (token == 'X') ? playerX : playerO;
    }

    /**
     * Updates the leaderboard with the results of the game and displays the leaderboard.
     *
     * @param token The token of the winning player ('X' or 'O')
     */
    public void updateLeaderboard(char token) {
        Player winner = getWinner(token);
        Player loser = (token == 'X') ? playerO : playerX;

        for (Player player : players) {
            if (player.getUsername().equals(winner.getUsername())) {
                player.incrementWins();
            } else if (player.getUsername().equals(loser.getUsername())) {
                player.incrementLosses();
            }
        }

        PlayerDataController.savePlayers(players); // TODO: (ag) Ensure PlayerDataController is working correctly and can save players.

        // Build leaderboard details
        StringBuilder leaderboardDetails = new StringBuilder();
        for (Player player : players) {
            double avgTime = gameTimer.getAverageTimePerMove(player.getUsername());
            leaderboardDetails.append(String.format("%s - Wins: %d, Losses: %d, Avg Time: %.2f seconds\n",
                    player.getUsername(), player.getWins(), player.getLosses(), avgTime));
        }

        LeaderboardPopup.showLeaderboard(players);
    }
}
