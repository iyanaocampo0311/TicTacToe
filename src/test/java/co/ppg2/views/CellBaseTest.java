package co.ppg2.views;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellBaseTest {
    @Test
    void testGetToken() {
        CellBase cell = new CellBase() {
            @Override
            public void setToken(char token) {
                this.token = token;
            }
        };
        assertEquals(' ', cell.getToken());
    }
}


