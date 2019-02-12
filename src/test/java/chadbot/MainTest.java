package chadbot;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void closeChatTrue() {
        Main main = new Main();
        assertTrue(main.closeChat("quit"));
        assertTrue(main.closeChat("Quit"));
    }

    @Test
    void closeChatFalse() {
        Main main = new Main();
        assertFalse(main.closeChat("This is a test String that shouldn't return true"));
    }
}