package fi.helsinki.tapio.tools;

import java.util.Stack;

public class ReversePolishNotationCalculator {

    public double calculateFromRPNString(String rpnString) throws IllegalArgumentException {
        if (rpnString == null || rpnString.isEmpty() || !rpnString.contains(" ")) {
            throw new IllegalArgumentException("Invalid RPN string");
        }
        Stack<String> operandStack = new Stack<>();
        String[] tokens = rpnString.split(" ");
        for (String token : tokens) {
            if (isDigit(token)) {
                operandStack.push(token);
            } else {
                if (operandStack.size() == 1 && (token.equals("SIN") || token.equals("SQRT"))) {
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

    public boolean isDigit(String string) {
        if (string == null || string.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
