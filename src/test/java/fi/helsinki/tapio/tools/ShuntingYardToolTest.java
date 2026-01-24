package fi.helsinki.tapio.tools;

import junit.framework.TestCase;

public class ShuntingYardToolTest extends TestCase {

    public void testCalculateOutputStack() {
        ShuntingYardTool tool = new ShuntingYardTool();
        try {
            assertEquals("3 4 +", tool.calculateOutputStack("3 + 4"));
            assertEquals("3 4 7 / +", tool.calculateOutputStack("3 + 4 / 7"));
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    public void testIsDigit() {
        ShuntingYardTool tool = new ShuntingYardTool();
        assertTrue(tool.isDigit("123"));
        assertFalse(tool.isDigit("123a"));
    }

    public void testFirstIsGreaterInPrecedence() {
        ShuntingYardTool tool = new ShuntingYardTool();
        assertTrue(tool.firstIsGreaterInPrecedence("^", "*"));
        assertTrue(tool.firstIsGreaterInPrecedence("^", "+"));
        assertTrue(tool.firstIsGreaterInPrecedence("*", "+"));
        assertFalse(tool.firstIsGreaterInPrecedence("+", "*"));
    }
}