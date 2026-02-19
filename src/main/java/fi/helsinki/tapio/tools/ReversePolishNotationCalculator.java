package fi.helsinki.tapio.tools;

import java.util.Stack;

public class ReversePolishNotationCalculator {

    /**
     * Calculates the result of a mathematical expression represented as a string
     * in Reverse Polish Notation (RPN).
     *
     * @param rpnString the RPN expression as a space-separated string.
     * @return the calculated result of the RPN expression as a double value.
     * @throws IllegalArgumentException if the input string is null, empty, has invalid tokens,
     *                                  does not use proper RPN syntax, or leads to an invalid computation.
     */
    public double calculateFromRPNString(String rpnString) throws IllegalArgumentException {
        if (rpnString == null || rpnString.isEmpty() || !rpnString.contains(" ")) {
            throw new IllegalArgumentException("Invalid RPN string");
        }
        Stack<String> operandStack = new Stack<>();
        String[] tokens = rpnString.split(" ");
        for (String token : tokens) {
            if (CommonTools.isNumber(token)) {
                operandStack.push(token);
            } else {
                if (token.equals("SIN") || token.equals("SQRT")) {
                    double operand = Double.parseDouble(operandStack.pop());
                    double result = calculate(token, operand, 0);
                    operandStack.push(String.valueOf(result));
                } else if (operandStack.size() >= 2) {
                    double operand2 = Double.parseDouble(operandStack.pop());
                    double operand1 = Double.parseDouble(operandStack.pop());
                    double result = calculate(token, operand1, operand2);
                    operandStack.push(String.valueOf(result));
                } else {
                    throw new IllegalArgumentException("Invalid RPN string with token " + token + " and stack " + operandStack.toString());
                }
            }
        }

        // If we are left with only one operand, return it. Otherwise throw exception.
        if (operandStack.size() == 1) {
            return Double.parseDouble(operandStack.pop());
        } else {
            throw new IllegalArgumentException("Invalid RPN string");
        }
    }

    /**
     * Performs a mathematical calculation based on the specified operator and operands.
     * Supports both binary and single operand operations.
     *
     * @param operator the mathematical operator to apply. Supported operators include:
     *                 "+" (addition), "-" (subtraction), "*" (multiplication),
     *                 "/" (division), "^" (exponentiation), "MAX" (maximum),
     *                 "MIN" (minimum), "SIN" (sine in degrees), "SQRT" (square root).
     * @param operand1 the first operand. For single operand operations like "SIN" and "SQRT", this is the only operand used.
     * @param operand2 the second operand. This is only used for binary operations like "+", "-", "*", "/", "^", "MAX", and "MIN".
     * @return the calculated result of applying the operator to the operand(s).
     * @throws IllegalArgumentException if the operator is invalid or not supported.
     */
    public double calculate(String operator, double operand1, double operand2) throws IllegalArgumentException {
        if (operator.equals("+")) {
            return operand1 + operand2;
        } else if (operator.equals("-")) {
            return operand1 - operand2;
        } else if (operator.equals("*")) {
            return operand1 * operand2;
        } else if (operator.equals("/")) {
            return operand1 / operand2;
        } else if (operator.equals("^")) {
            return Math.pow(operand1, operand2);
        } else if (operator.equals("MAX")) {
            return Math.max(operand1, operand2);
        } else if (operator.equals("MIN")) {
            return Math.min(operand1, operand2);
        } else if (operator.equals("SIN")) {
            double radians = Math.toRadians(operand1);
            return Math.sin(radians);
        } else if (operator.equals("SQRT")) {
            return Math.sqrt(operand1);
        } else {
            throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
