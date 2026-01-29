package fi.helsinki.tapio;

import fi.helsinki.tapio.tools.ReversePolishNotationCalculator;
import fi.helsinki.tapio.tools.ShuntingYardTool;

/**
 * A simple app to take either argument or user input and calculate result
 *
 */
public class App {

    public static void main( String[] args ) {
        ShuntingYardTool syTool = new ShuntingYardTool();
        ReversePolishNotationCalculator rpnCalc = new ReversePolishNotationCalculator();
        String calculationString = null;
        if (args.length < 1) {
            // Ask user for input
            System.out.println("Enter calculation:");
            calculationString = System.console().readLine();
        } else {
            calculationString = args[0];
        }
        if (calculationString == null || calculationString.isEmpty()) {
            System.out.println("ERROR in input");
            return;
        }
        try {
            String syOutput = syTool.calculateOutputStack(calculationString);
            double result = rpnCalc.calculateFromRPNString(syOutput);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
