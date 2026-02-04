package fi.helsinki.tapio.tools;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ShuntingYardToolTest {

    @Test
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

    @Test
    public void testIsDigit() {
        ShuntingYardTool tool = new ShuntingYardTool();
        assertTrue(tool.isDigit("123"));
        assertFalse(tool.isDigit("123a"));
    }

    @Test
    public void testFirstIsGreaterInPrecedence() {
        ShuntingYardTool tool = new ShuntingYardTool();
        assertTrue(tool.firstIsGreaterInPrecedence("^", "*"));
        assertTrue(tool.firstIsGreaterInPrecedence("^", "+"));
        assertTrue(tool.firstIsGreaterInPrecedence("*", "+"));
        assertFalse(tool.firstIsGreaterInPrecedence("+", "*"));
    }

    @Test
    public void testOperatorsHaveSamePrecedence() {
        ShuntingYardTool tool = new ShuntingYardTool();
        assertTrue(tool.operatorsHaveSamePrecedence("*", "*"));
        assertFalse(tool.operatorsHaveSamePrecedence("+", "/"));
    }

    @Test
    @Tag("loadtest")
    public void testLoadByHundredThousandCalculations() {
        try {
            ShuntingYardTool tool = new ShuntingYardTool();
            for (int i = 0; i < 100000; i++) {
                tool.calculateOutputStack("3 + 4");
            }
            assertTrue(true);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    @Tag("loadtest")
    public void testLoadByMillionCalculations() {
        try {
            ShuntingYardTool tool = new ShuntingYardTool();
            for (int i = 0; i < 1000000; i++) {
                tool.calculateOutputStack("3 + 4");
            }
            assertTrue(true);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    @Tag("loadtest")
    public void testLoadByAddingCalculatedElements() {
        try {
            ShuntingYardTool tool = new ShuntingYardTool();
            String expression = "3 + 3 / ( 2 + 1 )";
            for (int i = 0; i < 1000; i++) {
                expression += " + 3 * 3";
                tool.calculateOutputStack(expression);
            }
            assertTrue(true);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}