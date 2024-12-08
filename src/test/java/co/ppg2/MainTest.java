package co.ppg2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testMainRunsWithoutExceptions() {
        String[] args = {}; // Ensure Main has a static void main(String[] args) method
        assertDoesNotThrow(() -> Main.main(args), "Main method should run without throwing exceptions");
    }
}


