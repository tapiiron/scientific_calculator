package fi.helsinki.tapio.tools;

import junit.framework.TestCase;

public class ReversePolishNotationCalculatorTest extends TestCase {

    public void testCalculateFromRPNString() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        assertEquals(5.0, calculator.calculateFromRPNString("2 3 +"));
        assertEquals(-1.0, calculator.calculateFromRPNString("2 3 -"));
        assertEquals(6.0, calculator.calculateFromRPNString("2 3 *"));
        assertEquals(1.5, calculator.calculateFromRPNString("3 2 /"));
        assertEquals(8.0, calculator.calculateFromRPNString("2 3 ^"));
        assertEquals(5.0, calculator.calculateFromRPNString("1 5 MAX"));
        assertEquals(1.0, calculator.calculateFromRPNString("1 5 MIN"));
        assertEquals(5.0, calculator.calculateFromRPNString("25 SQRT"));
        assertEquals(0.49999999999999994, calculator.calculateFromRPNString("30 SIN"));
        // Examples from wikipedia
        assertEquals(0.5446390350150271, calculator.calculateFromRPNString("2 3 MAX 3 / 33 * SIN"));
        assertEquals(3.0001220703125, calculator.calculateFromRPNString("3 4 2 * 1 5 - 2 3 ^ ^ / +"));
        // Try to break by dividing by zero
        assertEquals(Double.POSITIVE_INFINITY, calculator.calculateFromRPNString("2 0 /"));

    }

    public void testCalculate() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        assertEquals(5.0, calculator.calculate("+", 2, 3));
        assertEquals(-1.0, calculator.calculate("-", 2, 3));
        assertEquals(6.0, calculator.calculate("*", 2, 3));
        assertEquals(2.0, calculator.calculate("/", 4, 2));
        assertEquals(8.0, calculator.calculate("^", 2, 3));
        assertEquals(5.0, calculator.calculate("MAX", 1, 5));
        assertEquals(1.0, calculator.calculate("MIN", 1, 5));
        assertEquals(0.49999999999999994, calculator.calculate("SIN", 30, 0));
        assertEquals(5.0, calculator.calculate("SQRT", 25, 0));
    }

    public void testIsDigit() {
    }
}