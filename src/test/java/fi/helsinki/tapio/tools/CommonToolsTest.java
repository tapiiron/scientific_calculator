package fi.helsinki.tapio.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommonToolsTest {

    @Test
    void isNumber() {
        assertTrue(CommonTools.isNumber("123"));
        assertTrue(CommonTools.isNumber("123.2"));
        assertFalse(CommonTools.isNumber("123a"));
    }

}