package fi.helsinki.tapio.tools;

import junit.framework.TestCase;

public class ShuntingYardToolTest extends TestCase {

    public void testCalculateOutputStack() {
        ShuntingYardTool tool = new ShuntingYardTool();
        try {
            String result = tool.calculateOutputStack("3 + 4");
            assertEquals("34+", result);
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    public void testFirstIsGreaterInPrecedence() {
    }
}