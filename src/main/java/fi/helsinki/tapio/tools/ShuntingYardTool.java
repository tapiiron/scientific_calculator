package fi.helsinki.tapio.tools;

import java.util.Stack;

public class ShuntingYardTool {
    /**
     * pseudo from wikipedia
     * while there are tokens to be read:
    read a token
    if the token is:
    - a number:
        put it into the output queue
    - a function:
        push it onto the operator stack 
    - an operator o1:
        while (
            there is an operator o2 at the top of the operator stack which is not a left parenthesis, 
            and (o2 has greater precedence than o1 or (o1 and o2 have the same precedence and o1 is left-associative))
        ):
            pop o2 from the operator stack into the output queue
        push o1 onto the operator stack
    - a ",":
        while the operator at the top of the operator stack is not a left parenthesis:
             pop the operator from the operator stack into the output queue
    - a left parenthesis (i.e. "("):
        push it onto the operator stack
    - a right parenthesis (i.e. ")"):
        while the operator at the top of the operator stack is not a left parenthesis:
            {assert the operator stack is not empty}
            pop the operator from the operator stack into the output queue
        {assert there is a left parenthesis at the top of the operator stack}
        pop the left parenthesis from the operator stack and discard it
        if there is a function token at the top of the operator stack, then:
            pop the function from the operator stack into the output queue
while there are tokens on the operator stack:
    {assert the operator on top of the stack is not a (left) parenthesis}
    pop the operator from the operator stack onto the output queue
     */

    public String calculateOutputStack(String input) throws Exception {
        if (input == null || input.length() < 1 || !input.contains(" ")) {
            throw new Exception("Invalid input");
        }
        input = input.toUpperCase();

        int index = 0;
        String[] tokens = input.split(" ");

        // Output queue
        Stack<String> outputQueue = new Stack<>();

        // Operator stack
        Stack<String> operatorStack = new Stack<>();

        // Main loop of input characters
        while (index<tokens.length) {
            String token = tokens[index];

            if (CommonTools.isNumber(token)) { // If token is number
                outputQueue.push(token);
            } else if (token.startsWith("S") || token.startsWith("M")) { // If token is function
                // Check that funtion is valid
                if (token.equals("SIN") || token.equals("SQRT") || token.equals("MIN") || token.equals("MAX")) {
                    operatorStack.push(token);
                } else {
                    throw new Exception("Invalid function: " + token);
                }
            } else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^")) { // If token is operator
                /**
                 * while (   o1 == operator,  o2==top in stack
                 *             there is an operator o2 at the top of the operator stack which is not a left parenthesis,
                 *             and (o2 has greater precedence than o1 or (o1 and o2 have the same precedence and o1 is left-associative))
                 *         ):
                 *             pop o2 from the operator stack into the output queue
                 *         push o1 onto the operator stack
                 */
                while (!operatorStack.empty() && !operatorStack.lastElement().equals("(")
                        // ^ is evaluated right-to-left
                        && !operatorStack.lastElement().equals("^")
                        && firstIsGreaterInPrecedence(operatorStack.lastElement(), token) ||
                        (!operatorStack.empty() && operatorsHaveSamePrecedence(operatorStack.lastElement(), token) && !token.equals("^"))
                ) {
                    outputQueue.push(operatorStack.pop());
                }
                operatorStack.push(token);


            } else if (token.equals(",")) {
                /* while the operator at the top of the operator stack is not a left parenthesis:
                pop the operator from the operator stack into the output queue*/
                while (!operatorStack.empty() && !operatorStack.lastElement().equals("(")) {
                    outputQueue.push(operatorStack.pop());
                }
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                // While the operator at the top of the operator stack is not a left parenthesis
                while (!operatorStack.empty() && !operatorStack.lastElement().equals("(")) {
                    // Pop the operator from the operator stack into the output queue
                    outputQueue.push(operatorStack.pop());
                }

                // Remove left parenthesis as it SHOULD be in top of the queue now
                if (operatorStack.lastElement().equals("(")) {
                    operatorStack.pop();
                } else {
                    throw new Exception("Left parenthesis missing!");
                }

                // If there is a function token at the top of the operator stack, then pop it into output queue
                if (!operatorStack.empty() && (operatorStack.lastElement().equals("SIN") ||
                        operatorStack.lastElement().equals("SQRT") ||
                        operatorStack.lastElement().equals("MIN") ||
                        operatorStack.lastElement().equals("MAX"))) {
                    outputQueue.push(operatorStack.pop());
                }
            } else {
                throw new Exception("Invalid token: " + token);
            }
            index++;
        }
        /* while there are tokens on the operator stack:
        {assert the operator on top of the stack is not a (left) parenthesis}
        pop the operator from the operator stack onto the output queue
                */
        while (!operatorStack.empty()) {
            outputQueue.push(operatorStack.pop());
        }

        String output = "";
        if (!outputQueue.isEmpty()) {
            for (String s : outputQueue) {
                output += s + " ";
            }
        }

        if (output.contains("(")) {
            throw new Exception("Invalid input");
        }

        return output.trim();
    }

    public boolean firstIsGreaterInPrecedence(String first, String second) {
        /*
         * Operator	Precedence	Associativity
         * ^	4	Right
         * ×	3	Left
         * ÷	3	Left
         * +	2	Left
         * −	2	Left
         */
        if (first.equals("^")) {
            return true;
        } else if (first.equals("*") || first.equals("/")) {
            return !second.equals("*") && !second.equals("/") && !second.equals("^");
        } else if (first.equals("+") || first.equals("-")) {
            return second.equals("+") || second.equals("-");
        }
        return false;
    }

    public boolean operatorsHaveSamePrecedence(String first, String second) {
        if (first.equals("^") || second.equals("^")) {
            return true;
        } else if (first.equals("*") || first.equals("/")) {
            return second.equals("*") || second.equals("/");
        } else if (first.equals("+") || first.equals("-")) {
            return second.equals("+") || second.equals("-");
        }
        return false;
    }

}