package co.ppg2.controllers;

import co.ppg2.model.Player;
import co.ppg2.views.CellBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {
    private GameController gameController;
    private Player playerX;
    private Player playerO;

    @BeforeEach
    void setUp() {
        playerX = new Player("PlayerX");
        playerO = new Player("PlayerO");
        gameController = new GameController(playerX, playerO);

        // Initialize cells for the test cases to prevent null issues
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameController.setCell(i, j, new CellBase() {
                    @Override
                    public void setToken(char token) {
                        this.token = token;
                    }
                });
            }
        }
    }

    @Test
    void testGetWhoseTurn() {
        assertEquals('X', gameController.getWhoseTurn());
    }

    @Test
    void testGetCurrentPlayer() {
        assertEquals(playerX, gameController.getCurrentPlayer());
    }

    @Test
    void testIsFull() {
        assertFalse(gameController.isFull());
    }

    // TODO: Add test case for when the board is full
    @Test
    void testIsWon() {
        // Set up a winning state for 'X'
        gameController.getCell(0, 0).setToken('X');
        gameController.getCell(0, 1).setToken('X');
        gameController.getCell(0, 2).setToken('X');
        assertTrue(gameController.isWon('X'));

        // Set up a non-winning state for 'O'
        assertFalse(gameController.isWon('O'));
    }
    // TODO: Add test cases for diagonal and vertical wins

    @Test
    void testSetAndGetCell() {
        CellBase cell = new CellBase() {
            @Override
            public void setToken(char token) {
                this.token = token;
            }
        };
        gameController.setCell(0, 0, cell);
        assertEquals(cell, gameController.getCell(0, 0));
    }

    @Test
    void testGetWinner() {
        // Set up a winning state for 'X'
        gameController.getCell(0, 0).setToken('X');
        gameController.getCell(0, 1).setToken('X');
        gameController.getCell(0, 2).setToken('X');
        assertEquals(playerX, gameController.getWinner('X'));

        // Set up a winning state for 'O'
        gameController.getCell(1, 0).setToken('O');
        gameController.getCell(1, 1).setToken('O');
        gameController.getCell(1, 2).setToken('O');
        assertEquals(playerO, gameController.getWinner('O'));
        // TODO: Add test case for when there is no winner
    }
    // TODO: Add test for switching turns after a move

    // TODO: Add test for invalid moves (e.g., placing a token on an occupied cell)

    // TODO: Add test for game over scenarios (win, draw)
}



