package co.ppg2.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class GameTimerTest {
    private GameTimer gameTimer;


    @BeforeEach
    void setUp() {
        gameTimer = new GameTimer();
    }


    @Test
    void testStartAndCancelTimer() throws InterruptedException {
        // Start timer for PlayerX
        gameTimer.startTimer("PlayerX");


        // Simulate a delay to test timing functionality
        Thread.sleep(1000); // 1 second


        // Cancel the timer
        gameTimer.cancelTimer();


        // Check if the average time per move is approximately 1 second (1000ms)
        double expectedTime = 1.0; // In seconds
        double actualTime = gameTimer.getAverageTimePerMove("PlayerX");
        assertEquals(expectedTime, actualTime, 0.1,
                "Average time per move should be approximately 1 second");
    }


    @Test
    void testGetAverageTimePerMoveBeforeStart() {
        // Ensure average time per move is 0.0 before starting the timer
        assertEquals(0.0, gameTimer.getAverageTimePerMove("PlayerX"),
                "Average time per move should be 0.0 before the timer starts");
    }
}

