package co.ppg2.controllers;

import co.ppg2.services.GameTimer;
import co.ppg2.model.Player;
import co.ppg2.views.CellBase;
import co.ppg2.views.GameView;
import co.ppg2.views.LeaderboardPopup;
import java.util.ArrayList;


public class GameController {
    public GameController(String usernameX, String usernameO) {
        if (usernameX == null || usernameX.trim().isEmpty() || usernameO == null || usernameO.trim().isEmpty()) {
            throw new IllegalArgumentException("Both players must enter a valid username.");
        } //makes sure the player must enter a username to proceed
        this.playerX = new Player(usernameX);
        this.playerO = new Player(usernameO);

    }
    private char whoseTurn = 'X';
    private final CellBase[][] cells = new CellBase[3][3];
    private final Player playerX;
    private final Player playerO;
    private GameTimer gameTimer;
    private GameView gameView;
    private ArrayList<Player> players = null;


    public GameController(Player playerX, Player playerO) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.players = PlayerDataController.loadPlayers(); // Load existing players
    }


    public char getWhoseTurn() {
        return whoseTurn;
    }


    public Player getCurrentPlayer() {
        return (whoseTurn == 'X') ? playerX : playerO;
    }


    /**
     * Switches the turn to the other player and starts their timer
     */ //javadoc comment example
    public void switchTurn() {
        if (gameTimer != null) {
            gameTimer.cancelTimer(); // Stop the timer for the current player
            whoseTurn = (whoseTurn == 'X') ? 'O' : 'X';
            gameTimer.startTimer(getCurrentPlayer().getUsername()); // Start the timer for the next player
        }
    }


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




    public void setCell(int row, int col, CellBase cell) {
        cells[row][col] = cell;
    }




    public void setGameTimer(GameTimer gameTimer) {
        this.gameTimer = gameTimer;
    }




    public CellBase getCell(int row, int col) {
        return cells[row][col];
    }




    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }



    public GameView getGameView() {
        return gameView;
    }



    public Player getWinner(char token) {
        return (token == 'X') ? playerX : playerO;
    }



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



        PlayerDataController.savePlayers(players);


        // Show leaderboard with average time per move
        StringBuilder leaderboardDetails = new StringBuilder();
        for (Player player : players) {
            double avgTime = gameTimer.getAverageTimePerMove(player.getUsername());
            leaderboardDetails.append(String.format("%s - Wins: %d, Losses: %d, Avg Time: %.2f seconds\n",
                    player.getUsername(), player.getWins(), player.getLosses(), avgTime));
        }


        LeaderboardPopup.showLeaderboard(players);
    }
    //TODO: could make a basic utility method to check if a cell in the 2D array is empty

    public boolean isCellEmpty(int row, int col) {
        return cells[row][col] == null || cells[row][col].getToken() == ' ';
    }
//TODO: suggestion: remove unnecessary blank lines to improve readability
}









