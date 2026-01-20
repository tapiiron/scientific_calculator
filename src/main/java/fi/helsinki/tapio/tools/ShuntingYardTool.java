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
        if (input == null || input.length()<1) {
            throw new Exception("Invalid input");
        }
        // Remove all spaces and lowercase
        input = input.replace(" ",""); 
        input = input.toLowerCase();

        int index = 0;
        char[] tokens = input.toCharArray();

        // Output queue
        String outputQueue = "";

        // Operator stack
        Stack<String> operatorStack = new Stack<>();

        // Main loop of input characters
        while (index<tokens.length) {
            char token = tokens[index];

            if (Character.isDigit(token)) { // If token is number
                outputQueue += token;
            } else if (token == 's' || token == 'm' ) { // If token is function
                // sin, sqrt, min and max Handle skip index here
                String funtion = ""+token+tokens[index+1]+tokens[index+2];
                index += 2;
                if (funtion.equals("sqr")) {
                    funtion = "sqrt";
                    index++;
                }
                // Check that funtion is valid
                if (!funtion.equals("sin") && !funtion.equals("sqrt") && !funtion.equals("min") && !funtion.equals("max")) {
                    throw new Exception("Invalid function: " + funtion);
                }
                operatorStack.push(funtion);
            } else if (token == '+' || token == '-' || token == '*' || token == '/') { // If token is operator
                // TODO: logic here
                /**
                 * while (
                 *             there is an operator o2 at the top of the operator stack which is not a left parenthesis,
                 *             and (o2 has greater precedence than o1 or (o1 and o2 have the same precedence and o1 is left-associative))
                 *         ):
                 *             pop o2 from the operator stack into the output queue
                 *         push o1 onto the operator stack
                 */
                operatorStack.push(""+token);
            } else if (token == ',') {
                outputQueue += token;
            } else if (token == '(') {
                operatorStack.push(""+token);
            } else if (token == ')') {
                // TODO: logic here
                /**
                 *  while the operator at the top of the operator stack is not a left parenthesis:
                 *             {assert the operator stack is not empty}
                 *             pop the operator from the operator stack into the output queue
                 *         {assert there is a left parenthesis at the top of the operator stack}
                 *         pop the left parenthesis from the operator stack and discard it
                 *         if there is a function token at the top of the operator stack, then:
                 *             pop the function from the operator stack into the output queue
                 */
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
            outputQueue += operatorStack.pop();
        }

        return outputQueue;
    }
}