package fi.helsinki.tapio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Just a fancy test case
     */
    @Test
    public void testApp() {
        assertTrue( true );
    }

    @Test
    void calculate() {
        // Simple test
        assertEquals(3.0, App.calculate("2 + 1"));
        // Test with variables
        App.addOrChangeVariable("a", 2.0);
        assertEquals(3.0, App.calculate("a + 1"));
        assertEquals(8.0, App.calculate("a + 3 * a"));
    }
}
