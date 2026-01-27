package fi.helsinki.tapio.tools;

import junit.framework.TestCase;

public class ShuntingYardToolTest extends TestCase {

    public void testCalculateOutputStack() {
        ShuntingYardTool tool = new ShuntingYardTool();
        try {
            // Own simple tests
            assertEquals("3 4 +", tool.calculateOutputStack("3 + 4"));
            assertEquals("1 2 + 2 MAX 3 +", tool.calculateOutputStack("MAX ( 1 + 2 , 2 ) + 3"));

            // From wikipedia example calculation
            assertEquals("2 3 MAX 3 / 33 * SIN", tool.calculateOutputStack("SIN ( MAX ( 2 , 3 ) / 3 * 33 )"));
            assertEquals("3 4 2 * 1 5 - 2 3 ^ ^ / +", tool.calculateOutputStack("3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3"));

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

    public void testOperatorsHaveSamePrecedence() {
        ShuntingYardTool tool = new ShuntingYardTool();
        assertTrue(tool.operatorsHaveSamePrecedence("*", "*"));
        assertFalse(tool.operatorsHaveSamePrecedence("+", "/"));
    }
}